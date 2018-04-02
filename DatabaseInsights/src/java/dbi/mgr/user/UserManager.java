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
import dbi.utils.DBIResultSet;
import dbi.utils.GlobeVar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author kanch
 */
public class UserManager {

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
                System.out.println("ERROR:" + e.getMessage());
                return false;
            }
        }
        return true;
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
                System.out.println("ERROR:" + e.getMessage());
                return false;
            }
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
                System.out.println("ERROR:" + e.getMessage());
                return false;
            }
        }
        return true;
    }
    
    /*
    * This function used to get user info, for render some part of console
    */
    public HashMap<String,String> getUserInfo(){
        HashMap<String,String> info = new HashMap<>();
        
        return info;
    }

    public Boolean disableUser(int userID) {
        try {

            if (dbhelper.Connect()) {
                dbhelper.runSQL("update T_DI_USER set STATUS=0 where USERID='" + userID + "'");
            }
            dbhelper.Disconnect();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public Boolean alterUser(int userID, HashMap charged_key_value) {
        return true;
    }

    public static void main(String[] args) {
        UserManager manager = new UserManager();
        //System.out.println(manager.validateUser("vicky", "123"));
    }

}
