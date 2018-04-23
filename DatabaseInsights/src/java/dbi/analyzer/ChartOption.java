/*
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code created on Apr 19 2018
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

/**
 *
 * @author kanch
 */
public class ChartOption {

    public static String OPTION_HISTOGRAM = "option = {"
            + "        title : {"
            + "        text: '@TITLE',"
            + "        subtext: '@SUBTITLE',"
            + "        x:'center'"
            + "    },"
            + "    xAxis: {"
            + "        type: 'category',"
            + "        data: @XDATA"
            + "    },"
            + "    yAxis: {"
            + "        type: 'value'"
            + "    },"
            + "    series: [{"
            + "        data: @YDATA,"
            + "        type: 'bar'"
            + "    }]"
            + "};";

    public static String OPTION_LINE = "option = {"
            + "    title : {"
            + "        text: '@TITLE',"
            + "        subtext: '@SUBTITLE',"
            + "        x:'center'"
            + "    },"
            + "    xAxis: {"
            + "        type: 'category',"
            + "        data: @XDATA"
            + "    },"
            + "    yAxis: {"
            + "        type: 'value'"
            + "    },"
            + "    series: [{"
            + "        data: @YDATA,"
            + "        type: 'line'"
            + "    }]"
            + "};";

    public static String OPTION_PIE = "option = {"
            + "    title : {"
            + "        text: '@TITLE',"
            + "        subtext: '@SUBTITLE',"
            + "        x:'center'"
            + "    },"
            + "    tooltip : {"
            + "        trigger: 'item',"
            + "        formatter: \"{a} <br/>{b} : {c} ({d}%)\""
            + "    },"
            + "    legend: {"
            + "        orient: 'vertical',"
            + "        left: 'left',"
            + "        data: [@XDATA]"
            + "    },"
            + "    series : ["
            + "        {"
            + "            name: '@INFO',"
            + "            type: 'pie',"
            + "            radius : '55%',"
            + "            center: ['50%', '60%'],"
            + "            data:["
            + "                @YDATA"
            + "            ],"
            + "            itemStyle: {"
            + "                emphasis: {"
            + "                    shadowBlur: 10,"
            + "                    shadowOffsetX: 0,"
            + "                    shadowColor: 'rgba(0, 0, 0, 0.5)'"
            + "                }"
            + "            }"
            + "        }"
            + "    ]"
            + "};";
}
