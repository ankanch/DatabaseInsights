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

/** <h3>English Language Pack</h3>
 * This class is used for localization There are two type of string here, one
 * for debug, the other for user interface
 *
 * @author kanch
 */
public class langEnglish extends lang {

    private static Map<Integer, String> langMapEN;

    public langEnglish() {

        Map<Integer, String> result = new HashMap<Integer, String>();
        //@DBI_LANGGEN_TOOL_BEGIN
	result.put(langID.JSP_NAV_SIDE_OVERVIEW, "Overview");
	result.put(langID.JSP_NAV_SIDE_REPORTS, "Reports");
	result.put(langID.JSP_NAV_SIDE_REALTIME, "Realtime");
	result.put(langID.JSP_NAV_SIDE_ANALYTICS, "Analytics");
	result.put(langID.JSP_NAV_SIDE_AUTO, "Auto");
	result.put(langID.JSP_NAV_SIDE_CROSSTABLE, "Cross-table");
	result.put(langID.JSP_NAV_SIDE_CUSTOMIZE, "Customize");
	result.put(langID.JSP_NAV_SIDE_OTHERS, "Others");
	result.put(langID.JSP_NAV_SIDE_CREDENTIALS, "Credentials");
	result.put(langID.JSP_NAV_SIDE_LOGS, "Logs");
	result.put(langID.JSP_NAV_SIDE_SETTINGS, "Settings");
	result.put(langID.JSP_NAV_SIDE_TITLE_ANALYTICS, "Analytics");
	result.put(langID.JSP_NAV_SIDE_TITLE_DATABASE, "Database");
	result.put(langID.JSP_NAV_SIDE_TITLE_ACCOUNT, "Account");
	result.put(langID.USER_STATUS_UNKNOW, "Unknow");
	result.put(langID.USER_STATUS_UNVERFIED, "Unverfied");
	result.put(langID.USER_STATUS_NORMAL, "Normal");
	result.put(langID.USER_STATUS_RESTRICTED, "Restricted");
	//@DBI_LANGGEN_TOOL_END
        langMapEN = Collections.unmodifiableMap(result);
    }

    /*
    * function to get localization language by ID
     */
    @Override
    public String getString(final int id) {
        return langMapEN.get(id);
    }
}

