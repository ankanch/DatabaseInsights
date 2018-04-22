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
import dbi.utils.Debug;
import dbi.utils.GlobeVar;
import java.util.ArrayList;

/**
 *
 * @author kanch
 */
public class AutoAnalyzer {

    private ArrayList<Chart> charts_list = new ArrayList<Chart>();
    private DBIResultSet colSpecies = new DBIResultSet();
    private DBIResultSet distinctVals = new DBIResultSet();
    private DBIResultSet maxVals = new DBIResultSet();
    private DBIResultSet minVals = new DBIResultSet();
    private DBIResultSet avgVals = new DBIResultSet();

    public AutoAnalyzer(int uid, String table) {
        Debug.log("Generating all possiable species...");
        colSpecies = analyzerUtils.getAllColumnSpecies(uid, table);
        distinctVals = analyzerUtils.findDistinctValues(uid, table);
        maxVals = analyzerUtils.getMaxiumValues(uid, table);
        minVals = analyzerUtils.getMiniumValues(uid, table);
        avgVals = analyzerUtils.getAverangeValues(uid, table);

        //check columns to draw piecharts
        // here is how we dectect:
        //        less than 5 distinct values column will be rendered to piecharts
        Debug.log("Generate Barchart...--------cur chart_list.length=", charts_list.size());
        DBIResultSet bufdbirs = new DBIResultSet();
        for (int i = 1; i <= distinctVals.rowCount(); i++) {
            int ditvalcount = Integer.valueOf((String) distinctVals.getData(i, 2));
            if (ditvalcount < 5) {
                Chart cht = chartsHelper.generateBarchart(new DBIResultSet(distinctVals.getRow(i)));
                charts_list.add(cht);
            }
        }

        //check columns to draw linecharts
        //methodlogy: draw linecharts for every numberic columns excluding primary keys
        //            then draw statistics of the values for every column which is not numberic
        Debug.log("Generate linecharts...--------cur chart_list.length=", charts_list.size());
        for (int i = 1; i <= colSpecies.rowCount(); i++) {
            if (colSpecies.getRow(i).get(6).equals("NUMBER")) {
                Chart cht = chartsHelper.generateBarchart(new DBIResultSet());
                charts_list.add(cht);
            }
        }

        //check columns to draw histogramcharts
        //methodlogy: draw linecharts for every numberic columns excluding primary keys
        Debug.log("Generate histogram charts...--------cur chart_list.length=", charts_list.size());
    }

    public ArrayList<Chart> getAutoCharts() {
        return charts_list;
    }

    public static void main(String[] argvs) {
        AutoAnalyzer aa = new AutoAnalyzer(GlobeVar.OBJ_MANAGER_USER.getUIDbySessionID("A34928CC4DF0029CC9226FA77124FA20"), "T_DI_USER");
    }

}
