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
import java.util.ArrayList;

/**
 *
 * @author kanch
 */
public class autoAnalyzer {

    private ArrayList<Chart> charts_list = new ArrayList<Chart>();
    private DBIResultSet colSpecies = new DBIResultSet();
    private DBIResultSet distinctVals = new DBIResultSet();
    private DBIResultSet maxVals = new DBIResultSet();
    private DBIResultSet minVals = new DBIResultSet();
    private DBIResultSet avgVals = new DBIResultSet();

    public autoAnalyzer(int uid, String table) {
        colSpecies = analyzerUtils.getAllColumnSpecies(uid, table);
        distinctVals = analyzerUtils.findDistinctValues(uid, table);
        maxVals = analyzerUtils.getMaxiumValues(uid, table);
        minVals = analyzerUtils.getMiniumValues(uid, table);
        avgVals = analyzerUtils.getAverangeValues(uid, table);

        //check columns to draw piecharts
        // here is how we dectect:
        //        less than 5 distinct values column will be rendered to piecharts
        for (int i = 1; i <= distinctVals.rowCount(); i++) {
            if ((Integer) distinctVals.getRow(i).get(0) < 5) {
                Chart cht = chartsHelper.generateBarchart(new DBIResultSet());
                charts_list.add(cht);
            }
        }

        //check columns to draw linecharts
        //methodlogy: draw linecharts for every numberic columns excluding primary keys
        //            then draw statistics of the values for every column which is not numberic
        for (int i = 1; i <= colSpecies.rowCount(); i++) {
            if (colSpecies.getRow(i).get(6).equals("NUMBER")) {
                Chart cht = chartsHelper.generateBarchart(new DBIResultSet());
                charts_list.add(cht);
            }
        }

        //check columns to draw histogramcharts
        //methodlogy: draw linecharts for every numberic columns excluding primary keys
    }

    public ArrayList<Chart> getAutoCharts() {
        return charts_list;
    }

}
