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
    
    public static class columnNumber{
        static public int username=100;
        static public int password=200;
        static public int status=300;
        static public int email=400;
    }
            
    public Boolean validateUser(String username,String password){
        int columnNumber=-1;
        DBIResultSet result=new DBIResultSet();
        try{    
            DatabaseConfig dbconfig = GlobeVar.VAR_DATABASE_CONFIG;
            DatabaseHelper dbhepler = new DatabaseHelper(dbconfig);
            
            if(dbhepler.Connect()){
                result=dbhepler.runSelect("username,password", "T_DI_USER", "username='"+username+"' and password='"+password+"'");
            }
            if(result.rowCount()!=1){
                return false;
            }
            
            dbhepler.Disconnect();
        }catch(Exception e){
            System.out.println(e);

        }
       return true;
    }
    
    public Boolean registerUser(String UserName,String passWord,String email){
        DBIResultSet result=new DBIResultSet();
        try{    
            DatabaseConfig dbconfig = GlobeVar.VAR_DATABASE_CONFIG;
            DatabaseHelper dbhelper = new DatabaseHelper(dbconfig);
            
            if(dbhelper.Connect()){
                result=dbhelper.runSelect("username", "T_DI_USER", "username='"+UserName+"'");
                if(result.rowCount()==0){
                    dbhelper.runSQL("INSERT INTO T_DI_USER (USERNAME, PASSWORD, STATUS, EMAIL, LASTLOGIN) VALUES ('"+UserName+"', '"+passWord+"',0,'"+email+"',sysdate);");
                }else{
                    System.out.println("重复的用户名！");
                    return false;
                }
            }else{
                return false;
            }
           
            
            dbhelper.Disconnect();
        }catch(Exception e){
            System.out.println(e);

        }
       return true;
    }
    public Boolean disableUser(int userID){
        try{    
            DatabaseConfig dbconfig = GlobeVar.VAR_DATABASE_CONFIG;
            DatabaseHelper dbhelper = new DatabaseHelper(dbconfig);
            
            if(dbhelper.Connect()){
                dbhelper.runSQL("update T_DI_USER set STATUS=0 where USERID='"+userID+"'");
            }                       
            dbhelper.Disconnect();
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
       return true;
    }
    public Boolean alterUser(int userID,HashMap charged_key_value){

    }
    
    public static void main(String[] args){
        UserManager manager=new UserManager();
        System.out.println(manager.validateUser("vicky", "123"));
    }
    
}
