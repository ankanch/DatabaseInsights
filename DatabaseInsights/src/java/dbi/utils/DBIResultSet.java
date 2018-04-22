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

    public DBIResultSet(ArrayList<Object> row) {
        rs = new ArrayList<>();
        rs.add(row);
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

    /**
     * add current row to result set to makeup new result set.
     */
    public final void addRow(ArrayList<Object> row) {
        rs.add(row);
    }

    /**
     * add current data to one temporery row
     */
    public final void addToRow(Object data) {
        srow.add(data);
    }

    /**
     * finish the row created by addToRow function. then add it to the table rs
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

    /**
     * get data at row x, col y. x,y both start from 1. NOT 0;
     */
    public final Object getData(int x, int y) {
        return rs.get(x - 1).get(y - 1);
    }

    // get all rows of current DBIResultSet
    public final ArrayList<ArrayList<Object>> getRows() {
        return rs;
    }

    /**
     * return row count
     */
    public final int rowCount() {
        return rs.size();
    }

    /**
     * return column count per row. if no rows,return 0.
     */
    public final int colCount() {
        if (rs.size() > 0) {
            return rs.get(0).size();
        }
        return 0;
    }

    @Override
    public String toString() {
        String ret = rs.size() + "x";
        if (rs.size() > 0) {
            ret += rs.get(0).size() + " ";
        } else {
            ret += "0 ";
        }
        return "DBIResultSet[ " + ret + "table]{data= " + rs + " }";
    }

    //public final DBIResultSet map(func)
    public static void main(String[] args) {
        DBIResultSet b = new DBIResultSet();
        b.addToRow(1);
        b.addToRow(2);
        b.finishRow();
        Debug.log("b=", b);
        Debug.log("b.getData(1,1)=", b.getData(1, 1));
    }
}
