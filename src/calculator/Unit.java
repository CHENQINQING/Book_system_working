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
public class Unit {

    private UnitType unitType;

    private char op;

    private double val;

    public Unit() {
    }

    public Unit(char op) {
        this.unitType = UnitType.OPERATOR;
        this.op = op;
    }

    public Unit(double val) {
        this.unitType = UnitType.OPERAND;
        this.val = val;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public char getOp() {
        return op;
    }

    public void setOp(char op) {
        this.op = op;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    enum UnitType{OPERATOR, OPERAND};

    public Unit operate(Unit operand1, Unit operand2) {
        if(unitType != UnitType.OPERATOR || operand1.unitType != UnitType.OPERAND || operand2.unitType != UnitType.OPERAND){
            return null;
        }
        if(op == '+'){
            return new Unit(operand1.val + operand2.val);
        }else if(op == '-'){
            return new Unit(operand1.val - operand2.val);
        }else if(op == '×' || op == '*') {
            return new Unit(operand1.val * operand2.val);
        }else {
            return new Unit(operand1.val / operand2.val);
        }
    }

    @Override
    public String toString() {

        if(unitType == UnitType.OPERATOR){
            return "operation " + op + " ";
        }else{
            return "opNumber " + val + " ";
        }
    }
}

