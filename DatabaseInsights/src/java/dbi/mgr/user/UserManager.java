/*
 * The MIT License
 *
 * **** Copyright © Long Zhang(kanch).
 * **** Email: kanchisme@gmail.com
 * **** Code created on Feb 28 2018
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
package dbi.mgr.user;

import dbi.db.adaptor.DatabaseConfig;
import dbi.db.adaptor.DatabaseHelper;
import dbi.localization.lang;
import dbi.utils.DBIResultSet;
import dbi.utils.GlobeVar;
import dbi.utils.stringTranslator;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author kanch
 */
public class UserManager {

    private static String DEBUG_PREFIX = "DBI|DEBUG|:@>>UserManager>>";
    private Connection conn;
    private final DatabaseConfig dbconfig = GlobeVar.VAR_DATABASE_CONFIG;
    private final DatabaseHelper dbhelper = new DatabaseHelper(dbconfig);

    /*
    * This functio nused for check if current session is valid, by which, means if user logged in.
     */
    public Boolean validateSession(String sid) {
        if (dbhelper.Connect()) {
            try {
                int ret = dbhelper.executeOracleFunction("F_VALIDATE_SESSION(?)", sid);
                if (ret == -1) {  // ret = -1 stands for didn't login yet
                    return false;
                }
            } catch (SQLException e) {
                System.out.println(DEBUG_PREFIX + "validateSession()|::ERROR=" + e);
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /*
    * Check if password is correct for given user
     */
    public Boolean checkPassword(String sid, String oldpass) {
        try {
            String condition = " USESSION=''{0}'' AND PASSWORD=''{1}''";
            if (dbhelper.Connect()) {
                DBIResultSet re = dbhelper.runSelect("USERID",
                        "T_DI_USER",
                        MessageFormat.format(condition, sid, oldpass));
                if (re.rowCount() > 0) {
                    return true;
                }
            }
            dbhelper.Disconnect();
        } catch (Exception e) {
            System.out.println(DEBUG_PREFIX + "checkPassword()|::ERROR=" + e);
            return false;
        }
        return false;
    }

    /*
    * This function is used to log you in to the console.
    * function will add current session id to the Usession column of user table
     */
    public Boolean loginInUser(String username, String password, String session_id) {
        if (dbhelper.Connect()) {
            try {
                int ret = dbhelper.executeOracleFunction("F_LOGIN_USER(?,?,?)", username, password, session_id);
                if (ret == -1) {  // ret = -1 stands for username isn't exists or password incorrect
                    return false;
                }
            } catch (SQLException e) {
                System.out.println(DEBUG_PREFIX + "loginUser()|::ERROR=" + e);
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /*
    * Give username,password, email to registe
     */
    public Boolean registerUser(String username, String password, String email) {
        if (dbhelper.Connect()) {
            try {
                int ret = dbhelper.executeOracleFunction("F_CREATE_USER(?,?,?)", username, password, email);
                if (ret == -1) {  // ret = -1 stands for user already exist
                    return false;
                }
            } catch (SQLException e) {
                System.out.println(DEBUG_PREFIX + "registerUser()|::ERROR=" + e);
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /*
    * This function used to get user info, for render some part of console
     */
    public HashMap<String, String> getUserInfo(lang local,String session) {
        HashMap<String, String> info = new HashMap<>();
        if (dbhelper.Connect()) {
            DBIResultSet rs = dbhelper.runSelect("USERID,USERNAME,STATUS,LASTLOGIN,EMAIL", "T_DI_USER", "USESSION='" + session + "'");
            ArrayList<Object> row = rs.getRow(1);
            info.put("USERID", (String) row.get(0));
            info.put("USERNAME", (String) row.get(1));
            System.out.println(DEBUG_PREFIX + "registerUser()|::INFO>>(String) row.get(2) =" + (String) row.get(2));
            info.put("STATUS", stringTranslator.translateUserStatusCode(local,Integer.valueOf((String) row.get(2))));
            info.put("LASTLOGIN", (String) row.get(3));
            info.put("EMAIL", (String) row.get(4));
        }
        return info;
    }

    /*
    * sign out a user by clear current session id of the user.
     */
    public Boolean signoutUser(String session) {
        try {
            if (dbhelper.Connect()) {
                if (dbhelper.runSQL("UPDATE T_DI_USER SET USESSION='' WHERE USESSION='" + session + "'")) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(DEBUG_PREFIX + "signoutUser()|::ERROR=" + e);
        }
        return false;
    }

    public Boolean disableUser(int usession) {
        try {
            if (dbhelper.Connect()) {
                String condition = "USESSION='" + usession + "'";
                HashMap<String, Object> kvs = new HashMap<>();
                kvs.put("STATUS", -100);
                if (dbhelper.runUpdate("T_DI_USER", kvs, condition)) {
                    return true;
                }
            }
            dbhelper.Disconnect();
        } catch (Exception e) {
            System.out.println(DEBUG_PREFIX + "alterUser()|::ERROR=" + e);
        }
        return false;
    }

    /*
    * We only allow user to change his email and password
     */
    public Boolean alterUser(String usession, HashMap<String, Object> changed_key_value) {
        String condition = " USESSION='" + usession + "'";
        try {
            if (dbhelper.Connect()) {
                if (dbhelper.runUpdate("T_DI_USER", changed_key_value, condition)) {
                    return true;
                }
            }
            dbhelper.Disconnect();
        } catch (Exception e) {
            System.out.println(DEBUG_PREFIX + "alterUser()|::ERROR=" + e);
        }
        return false;
    }
    
    /*
    * This function can detect user local then return corresponding localization language obj
    * from GlobeVar
    */
    public lang detectLang(HttpServletRequest request){
        String local = request.getLocale().toString();
        if(local.equals("en_US")){
            return GlobeVar.OBJ_LOCALIZATION_ENGLISH;
        }else if(local.equals("zh_CN")){
            return GlobeVar.OBJ_LOCALIZATION_CHINESE;
        }
        return null;
    }

    public static void main(String[] args) {
        UserManager manager = new UserManager();
        //System.out.println(manager.validateUser("vicky", "123"));
    }

}
