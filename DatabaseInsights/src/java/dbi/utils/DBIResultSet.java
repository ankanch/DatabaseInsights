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
    private Boolean rowCompleted = false;

    public DBIResultSet() {
        rs = new ArrayList<>();
    }

    /*
    * add current data to one temporery row
     */
    public void addToRow(Object data) {
        if(rowCompleted){
            srow.clear();
            rowCompleted = false;
        }
        srow.add(data);
    }
    
    /*
    * finish the row created by addToRow function.
    * then add it to the table rs
    */
    public ArrayList<Object> finishRow(){
        rowCompleted = true;
        rs.add(srow);
        return (ArrayList<Object>) srow.clone();
    }

    public ArrayList<Object> makeRow(Object... row) {
        srow.clear();
        for (Object obj : row) {
            srow.add(obj);
        }
        return (ArrayList<Object>) srow.clone();
    }

    public ArrayList<ArrayList<Object>> addRow(ArrayList<Object> row) {
        rs.add(row);
        return rs;
    }

    /*
    * get designed row by row number which row must no less than 1
    */
    public ArrayList<Object> getRow(int i) {
        assert(i>0);
        return rs.get(i-1);
    }

    public int rowCount() {
        return rs.size();
    }

    public static void main(String[] args) {
        String s[] = new String[]{"abc", "sss", "aaa", "zzz"};
        DBIResultSet a = new DBIResultSet();
        a.addRow(a.makeRow(s));
        System.out.println(a.getRow(1));
    }

}
