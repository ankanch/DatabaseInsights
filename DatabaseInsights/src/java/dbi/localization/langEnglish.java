/*
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code generated on 2018-04-15 10:16:58.155965
 * *** Code generated by Database Insights Localization Generation Tool
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

    private static Map<Integer, String> langMap;

    public langEnglish() {

        Map<Integer, String> result = new HashMap<Integer, String>();
        
		//@DBI_LANGGEN_TOOL_BEGIN
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TITLE, "Credentials");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_CSUM_PRE, "You have ");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_CSUM_SUF, " database credentials in total.");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_BUTTON_ADD, "Add Credentials");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TIP_SEARCH, "Search");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_BUTTON_SEARCH, "Search");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_DBNAME, "Database");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_HOST, "Host");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_ACCOUNT, "Account");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_PASSWORD, "Password");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_OPERATIONS, "Operations");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_OPERATION_SAVE, "Save");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_OPERATION_EDIT, "Edit");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_OPERATION_DELETE, "Delete");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TIP_FAILED_DELETE, "Failed to delete,please try again later.");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TIP_SUCCESS_DELETE, "Credential has been deleted.");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TIP_FAILED_UPDATE, "Failed to update,please try again later.");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TIP_SUCCESS_UPDATE, "Update successfully!");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TIP_NO_DATA, "<strong>Holy guacamole!</strong> You do not have any database added yet. <br>Let's start by click ADD CREDENTIALS button on the top.");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_TITLE, "Add database credential");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_SELECT, "Select your database");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_HOST, "Database Host");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_DBNAME, "Database Name");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_USERNAME, "Username");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_PASSWORD, "Password");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_IAGREE_PRE, "I agree with the");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_IAGREE_SUF, "End User Agreement");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_BUTTON_ADD, "Add Database");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_BUTTON_CANCEL, "Cancel");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_HOST_TIP, "Enter the database host address.");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_DBNAME_TIP, "Enter the database instance name.");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_USERNAME_TIP, "Enter login account name of the database.");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_PASSWORD_TIP, "Enter the password corresponding to the account.");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_TIP_AGREEMENT, "Please agree our aggrement to continue!");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_TIP_CHECKING, "Checking information provided...");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_TIP_FAILED, "Failed to added! Please check your database information again.");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_TIP_SUCCESS, "Successfully added!");
	result.put(langID.JSP_CONSOLE_FOOTER_HOME, "Analytics Home");
	result.put(langID.JSP_CONSOLE_FOOTER_SERVICE, "Terms of Service");
	result.put(langID.JSP_CONSOLE_FOOTER_POLICY, "Privacy Policy");
	result.put(langID.JSP_SIGNIN_PAGE_TITLE, "Signin - Database Insights Console");
	result.put(langID.JSP_SIGNIN_FORM_TITLE, "Signin");
	result.put(langID.JSP_SIGNIN_LABEL_EMAIL, "Email address");
	result.put(langID.JSP_SIGNIN_LABEL_PASSWORD, "Password");
	result.put(langID.JSP_SIGNIN_LABEL_REMEMBERME, "Remember me");
	result.put(langID.JSP_SIGNIN_LABEL_LOGIN, "Login");
	result.put(langID.JSP_SIGNIN_LABEL_PROBLEMLOGIN, "Problem in login?");
	result.put(langID.JSP_SIGNIN_JS_NAVTITLE, "Console");
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

        langMap = Collections.unmodifiableMap(result);
    }

    /*
    * function to get localization language by ID
     */
    @Override
    public String getString(final int id) {
        return langMap.get(id);
    }
}
