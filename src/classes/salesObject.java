/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author chenq
 */
public class salesObject {
    private salesInf[] salesInf;
    private int nElems;
    
    public salesObject(int max) {
       salesInf = new salesInf[max];
       nElems = 0;
    }


    /**
     * 插入对象
     * @param firstName
     * @param lastName
     * @param age
     */
    public void insert(String bookName, String Date, int amount) {
       salesInf[nElems] = new salesInf(bookName, Date, amount);
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
        salesInf = ArrayUtils.remove(salesInf, i);
        nElems--;
                
    }
   
    /**
     * 查找对象（假设lastName不重复）
     * @param lastName
     * @return
     */
    public boolean find(String bookName) {
       int i;
       for(i=0; i<nElems; i++) {
           if(salesInf[i].getBookName().equals(bookName))
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
           System.out.println("book name: "+salesInf[i].getBookName()+"  ***date: "+salesInf[i].getDate()+"  ***amount: "+salesInf[i].getAmount()+"\r\n");
           record = "book name: "+salesInf[i].getBookName()+"  ***date: "+salesInf[i].getDate()+"  ***amount: "+salesInf[i].getAmount()+"\r\n";
       }
        }
        else{
            record = "";
        }
       return record;
    }

}

