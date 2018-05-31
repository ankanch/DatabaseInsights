/*
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code created on May 22 2018
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

import java.util.ArrayList;

/**
 *
 * @author kanch
 */
public class CustomizeAnalyzer {

    private int uid = -1;
    private String table = "";
    private analyzerUtils au;

    public CustomizeAnalyzer(int uid,String database, String table) {
        this.uid = uid;
        this.table = table;
        au = new analyzerUtils(this.uid,database,table);
    }

    public ArrayList<Chart> generateCharts(ArrayList<CustomizeJob> jobs) {
        ArrayList<Chart> chtarr = new ArrayList<>();
        for (CustomizeJob job : jobs) {
            switch (job.pool_func) {
                case CustomizeJob.PF_AVERANGE:
                    break;
                case CustomizeJob.PF_COUNT:
                    break;
                case CustomizeJob.PF_COUNT_NOREPEAT:
                    break;
                case CustomizeJob.PF_MAX:
                    break;
                case CustomizeJob.PF_MIN:
                    break;
                case CustomizeJob.PF_SUM:
                    break;
                default:
                                    ;
            }
        }
        return chtarr;
    }

}
