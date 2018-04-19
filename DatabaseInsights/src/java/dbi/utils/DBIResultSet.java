/*
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch) , ChengShiyi (Miss Zhang) 
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Miss Zhang
 */
public class DBIResultSet {

    private ArrayList<ArrayList<Object>> rs;
    private ArrayList<Object> srow = new ArrayList<>();

    public DBIResultSet() {
        rs = new ArrayList<>();
    }

    /*
    * Convert ResultSet to DBIResultSet
     */
    public DBIResultSet(ResultSet rs) {
        this.rs = new ArrayList<>();
        try {
            while (rs.next()) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    addToRow(rs.getString(i + 1));
                }
                finishRow();
            }
        } catch (SQLException e) {
            Debug.error(e.getMessage());
        }
    }

    /*
    * add current data to one temporery row
     */
    public final void addToRow(Object data) {
        srow.add(data);
    }

    /*
    * finish the row created by addToRow function.
    * then add it to the table rs
     */
    public final ArrayList<Object> finishRow() {
        ArrayList<Object> newrow = new ArrayList<>(srow);
        rs.add(newrow);
        srow.clear();
        return newrow;
    }

    /*
    * get designed row by row number which row must no less than 1
     */
    public final ArrayList<Object> getRow(int i) {
        assert (i > 0);
        return rs.get(i - 1);
    }
    
    // get all rows of current DBIResultSet
    public final ArrayList<ArrayList<Object>> getRows(){
        return rs;
    }

    public final int rowCount() {
        return rs.size();
    }

    public static void main(String[] args) {
        /*
        DBIResultSet a = new DBIResultSet();
        for(int i=0;i<5;i++){
            a.addToRow(i);
            a.addToRow(i+1);
            a.addToRow(i+2);
            a.addToRow(i+3);
            a.finishRow();
        }
        for(int i=1;i<6;i++){
            System.out.println(a.getRow(i));
        }
        for(int i=1;i<6;i++){
            a.getRow(i).add(99);
            a.finishRow();
        }
        for(int i=1;i<6;i++){
            System.out.println(a.getRow(i));
        }
*/
        DBIResultSet b = new DBIResultSet();

        b.finishRow();
        System.out.println(b.getRow(1));
    }
}
