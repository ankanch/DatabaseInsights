/*
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code created on Jun 13 2018
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

import dbi.utils.Debug;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kanch
 */
public class CrossTableAnalyzer {

    private int uid = -1;
    private analyzerUtils au;

    public CrossTableAnalyzer(int uid) {
        this.uid = uid;
    }

    public ArrayList<Chart> generateCharts(ArrayList<CrossTableJob> jobs) {
        ArrayList<Chart> chtarr = new ArrayList<>();
        for (CrossTableJob job : jobs) {
            Debug.log("current process field=", job.graph_name);
            Debug.log("chtarr.size()=", chtarr.size());
            Debug.log("process");
            HashMap<String, analyzerUtils> hm = new HashMap<>();
            Debug.log("create db operator...");
            for (String key : job.hm.keySet()) {
                String[] dbcol = job.hm.get(key);
                hm.put(dbcol[1], createAnalyzerUtils(dbcol[0], key)); //create db operate instance
            }
            Debug.log("start analyze");
            switch (job.dtype) {
                case CrossTableJob.TYPE_NUMBER:
                    switch (job.poolf) {
                        case CrossTableJob.PF_TREND:
                            break;
                        case CrossTableJob.PF_MAX:
                            break;
                        case CrossTableJob.PF_MIN:
                            break;
                        default: //CrossTableJob.PF_COUNT;

                            break;
                    }
                    break;
                default:    //TYPE_TEXT

                    break;
            }
            for (String[] col : job.collist) {

            }

        }
        return chtarr;
    }

    private analyzerUtils createAnalyzerUtils(String database, String table) {
        au = new analyzerUtils(uid, database, table);
        return au;
    }

    private Chart generateCountChart(CrossTableJob job) {
        Chart cht = new Chart("", Chart.CHARTM_PIECHART);
        return cht;

    }

    private Chart generateTrendChart(CrossTableJob job) {
        Chart cht = new Chart("", Chart.CHARTM_TRENDCHART);
        return cht;
    }

    private Chart generateMaxChart(CrossTableJob job) {
        Chart cht = new Chart("", Chart.CHARTM_PIECHART);
        return cht;
    }

    private Chart generateMinChart(CrossTableJob job) {
        Chart cht = new Chart("", Chart.CHARTM_PIECHART);
        return cht;
    }

}
