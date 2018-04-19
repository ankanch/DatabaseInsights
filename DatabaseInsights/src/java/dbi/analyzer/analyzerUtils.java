/*
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code created on Apr 17 2018
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
package dbi.analyzer;

import dbi.utils.DBIResultSet;

/**
 *
 * @author kanch
 */
public class analyzerUtils {

    /*
    * get all column species from T_DI_COLUMNS 
     */
    public static DBIResultSet getAllColumnSpecies(String table) {
        DBIResultSet dbire = new DBIResultSet();

        return dbire;
    }


    /*
    * find distinct values of every columns in a given table.
    * the order of the result keeps the order of columns
     */
    public static DBIResultSet findDistinctValues(String table) {
        DBIResultSet dbire = new DBIResultSet();

        return dbire;
    }

    /*
    * get maxium value of every column (if comparable). 
    * keep the original column order of the table
     */
    public static DBIResultSet getMaxiumValues(String table) {
        DBIResultSet dbire = new DBIResultSet();

        return dbire;
    }

    /*
    * get miniium value of every column (if comparable). 
    * keep the original column order of the table
     */
    public static DBIResultSet getMiniumValues(String table) {
        DBIResultSet dbire = new DBIResultSet();

        return dbire;
    }

    /*
    * get average of every columns, ignored if not number
     */
    public static DBIResultSet getAverangeValues(String table) {
        DBIResultSet dbire = new DBIResultSet();

        return dbire;
    }

    /*
    * get sum of every column in the order of column
     */
    public static DBIResultSet getAllColumnSum(String table) {
        DBIResultSet dbire = new DBIResultSet();

        return dbire;
    }

}
