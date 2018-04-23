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

import dbi.utils.Debug;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 *
 * @author kanch
 */
public class Chart {

    public static final int CHART_PIECHART = 1;
    public static final int CHART_LINECHART = 2;
    public static final int CHART_HISTOGRAM = 3;

    public int type = 0;
    public String x_label = "";
    public String y_label = "";
    public String title = "";
    public String sub_title = "";
    public ArrayList<String> legends = new ArrayList<>();
    public ArrayList<Object> x_values = new ArrayList<>();
    public ArrayList<Object> y_values = new ArrayList<>();

    @Override
    public String toString() {
        String chartops = "";
        String data = "";
        switch (type) {
            case CHART_PIECHART:
                // generate legends
                String legends = "";
                legends = x_values.stream().map((obj) -> "'" + String.valueOf(obj) + "',").reduce(legends, String::concat);
                // generate series data : like {value:335, name:'DATA1'}
                String temp_piedata = "{value:@V, name:'@N'},";
                for (int i = 0; i < y_values.size(); i++) {
                    data += temp_piedata.replace("@V", String.valueOf(y_values.get(i)))
                            .replace("@N", String.valueOf(x_values.get(i)));
                }
                Debug.log("piedata=", data);
                //generate options
                chartops = ChartOption.OPTION_PIE.replace("@TITLE", title)
                        .replace("@SUBTITLE", sub_title)
                        .replace("@XDATA", legends)
                        .replace("@YDATA", data);
                break;
            case CHART_LINECHART:
                chartops = ChartOption.OPTION_LINE.replace("@TITLE", title)
                        .replace("@SUBTITLE", sub_title)
                        .replace("@XDATA", "['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']")
                        .replace("@YDATA", "[820, 932, 901, 934, 1290, 1330, 1320]");
                break;
            case CHART_HISTOGRAM:
                chartops = ChartOption.OPTION_HISTOGRAM.replace("@TITLE", title)
                        .replace("@SUBTITLE", sub_title)
                        .replace("@XDATA", "['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']")
                        .replace("@YDATA", "[820, 932, 901, 934, 1290, 1330, 1320]");
                break;
        }
        return chartops;
    }

    private String CHART_CONTAINER = "<div class=\"card\">"
            + "	<div class=\"card-body\">"
            + "		<div id=\"@ID\" style=\"width: 600px;height:400px;\"></div>"
            + "		<script type=\"text/javascript\">"
            + "			var myChart = echarts.init(document.getElementById('@ID'));"
            + "			myChart.setOption(@OPTION);"
            + "		</script>"
            + "	</div>"
            + "</div>";

    public static void main(String[] argvs) {
        ArrayList<Object> a = new ArrayList<>(), b = new ArrayList<>();
        a.add("-1");
        a.add("1");
        a.add("0");
        b.add("90");
        b.add("56");
        b.add("12");
        Chart cht = new Chart();
        cht.type = Chart.CHART_PIECHART;
        cht.title = "chart test";
        cht.x_values = a;
        cht.y_values = b;
        Debug.log("cht.toString()=", cht);
    }
}
