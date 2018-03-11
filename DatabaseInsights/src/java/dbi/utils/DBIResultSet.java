/*
 * The MIT License
 *
 * *** Copyright © ChengShiyi (Miss Zhang)
 * *** Code created on  三月 11 2018
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package dbi.utils;

import java.util.ArrayList;

/**
 *
 * @author Miss Zhang
 */
public class DBIResultSet {
   private ArrayList<ArrayList<Object>> rs;
   private static ArrayList<Object> srow = new ArrayList<>();

    public DBIResultSet() {
        rs = new  ArrayList<>();
    }
    
    public ArrayList<Object> makeRow(Object... row){
        srow.clear();
        for(Object obj : row){
            srow.add(obj);
        }
        return (ArrayList<Object>)srow.clone();
    }
    
    public ArrayList<ArrayList<Object>> addRow(ArrayList<Object> row){
        rs.add(row);
        return rs;
    }
    
    public ArrayList<Object> getRow(int i){
        return rs.get(i);
    }
    
    public int rowCount(){
        return rs.size();
    }
    
    
   // public int columnCount(){
     //   return  ( rs.size() == 0 || rs.get(0).size() == rs.get(0).size() );
    //}
    
    
    /*
    public static void main(String[] args){
        String s[]=new String[]{"abc","sss","aaa","zzz"};
        DBIResultSet a=new DBIResultSet();
        System.out.println(a.makeRow(s));
    }
   */
}
