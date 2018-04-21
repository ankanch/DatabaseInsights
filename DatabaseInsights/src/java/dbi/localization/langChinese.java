/*
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code generated on 2018-04-21 18:05:39.057233
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

/** <h3>Chinese Language Pack</h3>
 * This class is used for localization There are two type of string here, one
 * for debug, the other for user interface
 *
 * @author kanch
 */
public class langChinese extends lang {

    private static Map<Integer, String> langMap;

    public langChinese() {

        Map<Integer, String> result = new HashMap<Integer, String>();
        
		//@DBI_LANGGEN_TOOL_BEGIN
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TITLE, "数据库管理");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_CSUM_PRE, "您当前有 ");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_CSUM_SUF, " 个数据库.");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_BUTTON_ADD, "添加数据库");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TIP_SEARCH, "搜索...");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_BUTTON_SEARCH, "搜索");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_DBNAME, "数据库实例名");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_HOST, "数据库服务器地址");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_ACCOUNT, "数据库帐户");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_PASSWORD, "帐户密码");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_OPERATIONS, "操作");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_OPERATION_SAVE, "保存");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_OPERATION_EDIT, "编辑");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TABLE_OPERATION_DELETE, "删除");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TIP_FAILED_DELETE, "数据库信息删除失败！请稍后再试。");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TIP_SUCCESS_DELETE, "数据库信息已删除。");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TIP_FAILED_UPDATE, "数据库信息更新失败！请稍后再试。");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TIP_SUCCESS_UPDATE, "数据库信息更新成功！");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_MGR_TIP_NO_DATA, "<strong>糟糕!</strong> 您还没有添加任何数据库. <br>您可以点击顶部的添加数据库按钮进行添加.");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_TITLE, "添加数据库");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_SELECT, "请选择您的数据库系统");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_HOST, "数据库主机名（IP地址或域名）");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_DBNAME, "数据库名称");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_USERNAME, "数据库登陆用户名");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_PASSWORD, "数据库登陆密码");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_IAGREE_PRE, "我同意");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_IAGREE_SUF, "《最终用户许可协议》");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_BUTTON_ADD, "添加数据库");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_BUTTON_CANCEL, "取消");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_HOST_TIP, "请输入数据库服务器地址。");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_DBNAME_TIP, "请输入数据库实例名。");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_USERNAME_TIP, "请输入数据库登录用户名。");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_PASSWORD_TIP, "请输入数据库登陆密码。");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_TIP_AGREEMENT, "请同意我们的用户服务协议以继续！");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_TIP_CHECKING, "测试能否连接到给定数据库中...");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_TIP_FAILED, "数据库添加失败！请检查您是否提供了正确的信息");
	result.put(langID.JSP_CONSOLE_CREDENTIAL_ADD_TIP_SUCCESS, "数据库添加成功！");
	result.put(langID.JSP_CONSOLE_FOOTER_HOME, "系统主页");
	result.put(langID.JSP_CONSOLE_FOOTER_SERVICE, "服务政策");
	result.put(langID.JSP_CONSOLE_FOOTER_POLICY, "隐私政策");
	result.put(langID.JSP_SIGNIN_PAGE_TITLE, "登陆 - Database Insights Console");
	result.put(langID.JSP_SIGNIN_FORM_TITLE, "登陆");
	result.put(langID.JSP_SIGNIN_LABEL_EMAIL, "电子邮件地址");
	result.put(langID.JSP_SIGNIN_LABEL_PASSWORD, "密码");
	result.put(langID.JSP_SIGNIN_LABEL_REMEMBERME, "记住密码");
	result.put(langID.JSP_SIGNIN_LABEL_LOGIN, "登陆");
	result.put(langID.JSP_SIGNIN_LABEL_PROBLEMLOGIN, "登陆遇到问题?");
	result.put(langID.JSP_SIGNIN_JS_NAVTITLE, "控制台");
	result.put(langID.JSP_SIGNIN_TIP_FAILED, "登陆失败！用户名或密码不正确。");
	result.put(langID.JSP_SIGNIN_TIP_SUCCESS, "登陆成功！重定向至控制台...");
	result.put(langID.JSP_SIGNUP_LABEL_USERNAME, "用户名");
	result.put(langID.JSP_SIGNUP_LABEL_EMAIL, "邮箱地址");
	result.put(langID.JSP_SIGNUP_LABEL_PASSWORD, "密码");
	result.put(langID.JSP_SIGNUP_LABEL_CONFIRMPASSWORD, "确认密码");
	result.put(langID.JSP_SIGNUP_BUTTON_SIGNUP, "注册");
	result.put(langID.JSP_SIGNUP_BUTTON_LOGIN, "登陆");
	result.put(langID.JSP_SIGNUP_INFO_USERNAME, "仅允许字母开头，由字母和数字组成");
	result.put(langID.JSP_SIGNUP_INFO_EMAIL, "我们不会像任何人泄露您的邮箱地址");
	result.put(langID.JSP_SIGNUP_INFO_PASSWORD, "请输入强密码，包含数字，大小写字母和符号");
	result.put(langID.JSP_SIGNUP_INFO_AGGREMENT, "我同意 《Database Insights 用户服务协议》");
	result.put(langID.JSP_CONSOLE_SETTINGS_TITLE, "用户设置");
	result.put(langID.JSP_CONSOLE_SETTINGS_LABEL_REGISTEDON, "帐户注册时间");
	result.put(langID.JSP_CONSOLE_SETTINGS_LABEL_STATUS, "账户状态");
	result.put(langID.JSP_CONSOLE_SETTINGS_LABEL_LASTLOGIN, "上次登录时间");
	result.put(langID.JSP_CONSOLE_SETTINGS_LABEL_USERNAME, "用户名");
	result.put(langID.JSP_CONSOLE_SETTINGS_LABEL_EMAIL, "邮箱地址");
	result.put(langID.JSP_CONSOLE_SETTINGS_BUTTON_SAVE, "保存更改");
	result.put(langID.JSP_CONSOLE_SETTINGS_LABEL_OLDPASSWORD, "旧密码");
	result.put(langID.JSP_CONSOLE_SETTINGS_LABEL_NEWPASSWORD, "新密码");
	result.put(langID.JSP_CONSOLE_SETTINGS_LABEL_COMFIRM_NEWPASSWORD, "确认新密码");
	result.put(langID.JSP_CONSOLE_SETTINGS_TIP_USERNAME, "用户名无法更改。");
	result.put(langID.JSP_CONSOLE_SETTINGS_TIP_EMAIL, "我们不会与任何人分享您的邮箱地址。");
	result.put(langID.JSP_CONSOLE_SETTINGS_LABEL_WARNING, "删除帐户</button> <small>注意，此操作不可逆转！</small>");
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
