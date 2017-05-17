/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author chenq
 */
import java.text.DecimalFormat;
import java.util.*;


public class Calculator {


    public static char compareOperator(char op1, char op2){

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('+', 0);
        map.put('-', 1);
        map.put('×', 2);
        map.put('*', 2);
        map.put('÷', 3);
        map.put('/', 3);
        map.put('(', 4);
        map.put(')', 5);
        map.put('#', 6);
        String[] compare = new String[]{">><<<>>", ">><<<>>", ">>>><>>", ">>>><>>", "<<<<<=!", "!!!!!!!", "<<<<<!="};
        return compare[map.get(op1)].charAt(map.get(op2));
    }



    private static List<Unit> parseUnits(String s) throws ExpressionIllegalException{

        List<Unit> units = new ArrayList<Unit>();


        boolean hasSign = false;


        boolean flag = true;

        for(int i = 0; i < s.length(); i++){

            if(s.charAt(i) == '.'){
                throw new ExpressionIllegalException();
            }

            else if(s.charAt(i) == '∞'){
                units.add(new Unit(hasSign?Double.NEGATIVE_INFINITY:Double.POSITIVE_INFINITY));
                hasSign = false;
                flag = false;
            }

            else if(s.charAt(i) == 'N'){
                units.add(new Unit(Double.NaN));
                hasSign = false;
                flag = false;
                i += 2;
            }

            else if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){

                double val = s.charAt(i) - '0';
                boolean hasPoint = false;   //. appear
                double level = 0.1;        //.
                int k;
                for(k = i+1; k < s.length(); k++){
                    if(s.charAt(k) >= '0' && s.charAt(k) <= '9'){
                        if(hasPoint){
                            val = val + (s.charAt(k) - '0') * level;
                            level *= 0.1;
                        }else{
                            val = val * 10 + (s.charAt(k) - '0');
                        }
                    }else if(s.charAt(k) == '.'){
                        if(hasPoint){
                            throw new ExpressionIllegalException();
                        }
                        hasPoint = true;
                    }else{
                        break;
                    }
                }
                i = k-1;
                units.add(new Unit(hasSign?-val:val));
                hasSign = false;
                flag = false;
            }

            else{
                if(s.charAt(i) == '-' && flag){
                    hasSign = true;
                    flag = false;
                }else if(s.charAt(i) == '+' && flag){
                    flag = false;
                }else{
                    char c = s.charAt(i);
                    units.add(new Unit(c));
                    flag = (c == '*' || c == '/' || c == '×' || c == '÷' || c == '(');
                }
            }
        }

        return units;
    }

    private static double _calculate(String s) throws ExpressionIllegalException{

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit('#'));
        units.addAll(parseUnits(s));
        units.add(new Unit('#'));
        debug("units.size()=" + units.size());

        Stack<Unit> operators = new Stack<Unit>();
        Stack<Unit> operands = new Stack<Unit>();

        int index = 0;
        while(index < units.size() || operators.size() != 0){
            Unit unit = units.get(index);
            if(unit.getUnitType() == Unit.UnitType.OPERAND){
                operands.push(unit);
                debug(unit + "in");
                index += 1;
            }else if(unit.getUnitType() == Unit.UnitType.OPERATOR){
                if(operators.empty() || compareOperator(operators.peek().getOp(), unit.getOp()) == '<'){
                    operators.push(unit);
                    debug(unit + "out");
                    index += 1;
                }else if(compareOperator(operators.peek().getOp(), unit.getOp()) == '='){
                    Unit tmp = operators.pop();
                    debug(tmp + "out");
                    index += 1;
                }else if(compareOperator(operators.peek().getOp(), unit.getOp()) == '>'){
                    if(operands.size() < 2){
                        throw new ExpressionIllegalException();
                    }
                    Unit operand2 = operands.pop();
                    Unit operand1 = operands.pop();
                    Unit operator = operators.pop();
                    debug(operand2 + "out");
                    debug(operand1 + "out");
                    debug(operator + "out");
                    Unit res = operator.operate(operand1, operand2);
                    operands.push(res);
                    debug(res + "in");
                }else{
                    throw new ExpressionIllegalException();
                }
            }
        }

        double res = operands.peek().getVal();
        return res;

    }

    public static String calculate(String s) throws ExpressionIllegalException{

        double res = _calculate(s);
        return Double.isNaN(res)?"NaN":new DecimalFormat("#.##############").format(res);
    }



    private static boolean DEBUG = true;

    private static void debug(String msg){
        if(DEBUG){
            System.out.println(msg);
        }
    }

    public static class ExpressionIllegalException extends Exception{
        public ExpressionIllegalException() {
            super("Error:illegal exeption");
        }
    }


    public static void main(String[] args) {

        String s = "-0.2+1.5*4*(4-2/4)";
        try{
            System.out.println(s + "=" + Calculator.calculate(s));
        }catch(ExpressionIllegalException e){
            System.out.println(e.getMessage());
        }

    }

}
