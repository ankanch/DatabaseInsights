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
import java.util.HashMap;

/**
 *
 * @author kanch
 */
public class AutoAnalyzer {

    private int uid = -1;
    private String table = "";

    public AutoAnalyzer(int uid, String table) {
        this.uid = uid;
        this.table = table;
    }

    public ArrayList<Chart> getAutoPieCharts() {
        ArrayList<Chart> charts_list = new ArrayList<Chart>();
        DBIResultSet distinctVals = analyzerUtils.findDistinctValues(uid, table);
        //check columns to draw piecharts
        // here is how we dectect:
        //        less than 5 distinct values column will be rendered to piecharts
        Debug.log("Generate Piechart...--------cur chart_list.length=", charts_list.size());
        ArrayList<String> cols = new ArrayList<>();
        for (int i = 1; i <= distinctVals.rowCount(); i++) {
            int ditvalcount = Integer.valueOf((String) distinctVals.getData(i, 2));
            if (ditvalcount < 5) {
                cols.add((String) distinctVals.getData(i, 1));
            }
        }
        // now render parameters for piecharts
        DBIResultSet dbirsbuf = analyzerUtils.getDistinctValuesCount(uid, table, cols.toArray()); // return name ,value ,counts
        if (dbirsbuf.rowCount() > 0) {
            HashMap<String, DBIResultSet> piecharts = new HashMap<>();
            for (ArrayList<Object> row : dbirsbuf.getRows()) {
                DBIResultSet piedata = new DBIResultSet();
                String colname = (String) row.get(0);
                if (piecharts.containsKey(colname)) {
                    piedata = piecharts.get(colname);
                }
                piedata.addToRow((String) row.get(1));
                piedata.addToRow((String) row.get(2));
                piedata.finishRow();
                piecharts.put(colname, piedata);
            }
            for (String key : piecharts.keySet()) {
                Chart cht = chartsHelper.generateBarchart(key, piecharts.get(key));
                charts_list.add(cht);
            }
        }
        return charts_list;
    }

    public ArrayList<Chart> getAutoLineCharts() {
        ArrayList<Chart> charts_list = new ArrayList<Chart>();
        DBIResultSet colSpecies = analyzerUtils.getAllColumnSpecies(uid, table);
        //check columns to draw linecharts
        //methodlogy: draw linecharts for every numberic columns excluding primary keys
        //            then draw statistics of the values for every column which is not numberic
        Debug.log("Generate linecharts...--------cur chart_list.length=", charts_list.size());
        for (int i = 1; i <= colSpecies.rowCount(); i++) {
            if (colSpecies.getRow(i).get(6).equals("NUMBER")) {
                Chart cht = chartsHelper.generateBarchart("", new DBIResultSet());
                charts_list.add(cht);
            }
        }
        return charts_list;
    }

    public ArrayList<Chart> getAutoHistogramCharts() {
        ArrayList<Chart> charts_list = new ArrayList<Chart>();
        //check columns to draw histogramcharts
        //methodlogy: draw linecharts for every numberic columns excluding primary keys
        Debug.log("Generate histogram charts...--------cur chart_list.length=", charts_list.size());
        return charts_list;
    }

    public static void main(String[] argvs) {
        AutoAnalyzer aa = new AutoAnalyzer(GlobeVar.OBJ_MANAGER_USER.getUIDbySessionID("B46E25F9F749B3F8F013E92E8AE0FC1E"), "T_DI_USER");
    }

}
