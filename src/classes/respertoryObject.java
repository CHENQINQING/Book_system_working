/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author chenq
 */
public class respertoryObject {
    private respertory[] respertory;
    private int nElems;
    
    public respertoryObject(int max) {
       respertory = new respertory[max];
       nElems = 0;
    }


    /**
     * 插入对象
     * @param firstName
     * @param lastName
     * @param age
     */
    public void insert(String bookName, String Date, int amount) {
       respertory[nElems] = new respertory(bookName, Date, amount);
       nElems++;
    }
   
    /**
     * 删除对象（假设lastName不重复）
     * @param lastName
     * @return
     */
    /*public boolean delete(String lastName) {
       int i;
       for(i=0; i<nElems; i++) {
           if(salesInf[i].getBookName().equals(lastName))
              break;
       }
       if(i == nElems) {
           System.out.println("can't find: " + lastName);
           return false;
       } else {
           for(int j=i; j<nElems; j++) {
              salesInf[j] = salesInf[j+1];
           }
           nElems--;
           System.out.println("delete success");
           return true;
       }
    }*/
    public void delete(int i){
        i = i-1;
        respertory = ArrayUtils.remove(respertory, i);
        nElems=nElems-1;
                
    }
   
    /**
     * 查找对象（假设lastName不重复）
     * @param lastName
     * @return
     */
    public boolean find(String bookName) {
       int i;
       for(i=0; i<nElems; i++) {
           if(respertory[i].getBookName().equals(bookName))
              break;
       }
       if(i == nElems) {
           System.out.println("can't find: " + bookName);
           return false;
       } else {
           System.out.println("find it");
           return true;
       }
    }
   
    /**
     * 显示所有对象
     */
    public String display() {
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String time=format.format(date);
        String record = null;
        if(nElems!=0){
       for(int i=0; i<nElems; i++) {
           System.out.println(nElems);
           System.out.println("book name: "+respertory[i].getBookName()+"  ***date: "+respertory[i].getDate()+"  ***amount: "+respertory[i].getAmount()+"\r\n");
           record ="book name: "+respertory[i].getBookName()+"  ***date: "+respertory[i].getDate()+"  ***amount: "+respertory[i].getAmount()+"\r\n";
           //System.out.println(record);
       }
        }
        else{
            JOptionPane.showMessageDialog(null, "No data in list", "ERROR", JOptionPane.ERROR_MESSAGE);
            record = "";
        }
       return record;
    }
}
