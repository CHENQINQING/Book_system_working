/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author liushuai
 */
public class Database_Connection_LS {
//    public static void main(String arg[]) throws Exception{
//        Connection conn=null;
//        String sql;
//        String driver = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql://127.0.0.1:3306/book_system";
//        String user = "root";
//        String password = "root";
//        try {
//            Class.forName(driver);
//            conn = DriverManager.getConnection(url, user, password);
//            Statement stmt = conn.createStatement();
//            sql = "select * from book";
//            ResultSet rs = stmt.executeQuery(sql);
//            System.out.println("id"+"\t"
//                        +"name"+"\t"
//                        +"price"+"\t"
//                        +"author"+"\t"
//                        +"introducation"+"\t"
//                        +"repetory"+"\t"
//                        + "public id"+"\t"
//                        +"type id"+"\t");
//            while(rs.next()){
//                System.out.println(rs.getString(1)+"\t"
//                        +rs.getString(2)+"\t"
//                        +rs.getString(3)+"\t"
//                        +rs.getString(4)+"\t"
//                        +rs.getString(5)+"\t"
//                        +rs.getString(6)+"\t"
//                        + rs.getString(7)+"\t"
//                        +rs.getString(8)+"\t");
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Database_Connection_LS.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
        Connection conn=null;
        Statement stmt;
        ResultSet rs;
        String sql;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/book_system";
        String user = "root";
        String password = "root";
    public Database_Connection_LS(){
        try {
            conn=DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(Database_Connection_LS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void showpub(){
            try {
                sql="select * from publisher";
                rs=stmt.executeQuery(sql);
                while(rs.next()){
                    System.out.println(rs.getString("Publisher_name"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database_Connection_LS.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public String showtype(){
            try {
                sql="select * from Type";
                rs=stmt.executeQuery(sql);
                if(rs.next()){
                    return rs.getString("Type_name");
                }else{
                    return "";
                }
                
            } catch (SQLException ex) {
                
                Logger.getLogger(Database_Connection_LS.class.getName()).log(Level.SEVERE, null, ex);
                return "";
            }
    }
}
