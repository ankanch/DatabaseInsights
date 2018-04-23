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
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 *
 * @author kanch
 */
public class chartsHelper {

    /**
     * 生成饼状图。<br/>
     * 参数格式：标题 与 多行数据，每行包括2列，分别为列名（显示名），值
     */
    public static Chart generateBarchart(String title, DBIResultSet xyvalues) {
        Chart cht = new Chart();
        cht.type = Chart.CHART_PIECHART;

        ArrayList<Object> x_values = new ArrayList<>();
        ArrayList<Object> y_values = new ArrayList<>();
        for (int i = 1; i <= xyvalues.rowCount(); i++) {
            x_values.add(xyvalues.getData(i, 1));
            y_values.add(xyvalues.getData(i, 2));
        }
        cht.x_values = x_values;
        cht.y_values = y_values;
        Debug.log("cht.x_values=", cht.x_values);
        Debug.log("cht.y_values", cht.y_values);

        return cht;
    }

    public static Chart generateLinechart(DBIResultSet xyvalues) {
        Chart cht = new Chart();

        return cht;
    }

    public static Chart generateHistogramchart(DBIResultSet xyvalues) {
        Chart cht = new Chart();

        return cht;
    }

}
