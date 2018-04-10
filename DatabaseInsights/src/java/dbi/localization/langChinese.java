/*
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code created on Apr 09 2018
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
package dbi.localization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** <h3>Chinese Language Pack</h3>
 * This class is used for localization There are two type of string here, one
 * for debug, the other for user interface
 *
 * @author kanch
 */
public class langChinese extends lang {

    private static Map<Integer, String> langMapCN;

    public langChinese() {

        Map<Integer, String> result = new HashMap<Integer, String>();
        //@DBI_LANGGEN_TOOL_BEGIN
	result.put(langID.JSP_NAV_SIDE_OVERVIEW, "总览");
	result.put(langID.JSP_NAV_SIDE_REPORTS, "报表");
	result.put(langID.JSP_NAV_SIDE_REALTIME, "实时分析");
	result.put(langID.JSP_NAV_SIDE_ANALYTICS, "分析器");
	result.put(langID.JSP_NAV_SIDE_AUTO, "自动化分析");
	result.put(langID.JSP_NAV_SIDE_CROSSTABLE, "跨表分析");
	result.put(langID.JSP_NAV_SIDE_CUSTOMIZE, "自定义分析");
	result.put(langID.JSP_NAV_SIDE_OTHERS, "其它");
	result.put(langID.JSP_NAV_SIDE_CREDENTIALS, "数据库凭证管理");
	result.put(langID.JSP_NAV_SIDE_LOGS, "日志");
	result.put(langID.JSP_NAV_SIDE_SETTINGS, "设置");
	result.put(langID.JSP_NAV_SIDE_TITLE_ANALYTICS, "分析");
	result.put(langID.JSP_NAV_SIDE_TITLE_DATABASE, "数据库");
	result.put(langID.JSP_NAV_SIDE_TITLE_ACCOUNT, "账户");
	result.put(langID.USER_STATUS_UNKNOW, "未知账户状态");
	result.put(langID.USER_STATUS_UNVERFIED, "未验证账户");
	result.put(langID.USER_STATUS_NORMAL, "正常账户");
	result.put(langID.USER_STATUS_RESTRICTED, "受限账户");
	//@DBI_LANGGEN_TOOL_END
        langMapCN = Collections.unmodifiableMap(result);
    }

    /*
    * function to get localization language by ID
     */
    @Override
    public String getString(final int id) {
        return langMapCN.get(id);
    }
}

