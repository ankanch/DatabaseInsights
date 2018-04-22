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

import java.util.ArrayList;

/**
 *
 * @author kanch
 */
public class Chart {

    public static final int CHART_PIRCHART = 1;
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
        switch (type) {
            case CHART_PIRCHART:
                chartops = ChartOption.OPTION_PIE.replace("@TITLE", title)
                        .replace("@SUBTITLE", sub_title)
                        .replace("@XDATA", "['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']")
                        .replace("@YDATA", "[820, 932, 901, 934, 1290, 1330, 1320]");
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

}
