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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        try{    
            DatabaseConfig a = new DatabaseConfig(DatabaseConfig.DatabaseCode.DATABASE_ORACLE_12C, "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@//111.231.225.37:1521/DatabaseInsights", "di", "DI2017");
            DatabaseHelper test = new DatabaseHelper(a);
            
            Class.forName(a.getDriver());

            conn = DriverManager.getConnection(a.getHost(), a.getUsername(), a.getPassword());

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from T_DI_USER where username='"+username+"' and password='"+password+"'");
            // 遍历获取结果
            
            int columnNumber=rs.getMetaData().getColumnCount();
            conn.close();
            if(columnNumber==1){
                return true;
            }else{
                return false;
            }
            
            
        }catch(Exception e){
            System.out.println(e);

        }
       return true;
    }
    
    public Boolean registerUser(String UserName,String passWord,String email){
        try{    
            DatabaseConfig a = new DatabaseConfig(DatabaseConfig.DatabaseCode.DATABASE_ORACLE_12C, "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@//111.231.225.37:1521/DatabaseInsights", "di", "DI2017");
            DatabaseHelper test = new DatabaseHelper(a);
            
            Class.forName(a.getDriver());

            conn = DriverManager.getConnection(a.getHost(), a.getUsername(), a.getPassword());

            PreparedStatement p = conn.prepareStatement("INSERT INTO T_DI_USER (USERNAME, PASSWORD, STATUS, EMAIL,LASTLOGIN) VALUES ('"+UserName+"', '"+passWord+"',0,'"+email+"',sysdate)");
            p.executeUpdate();//表示执行PreparedStatement 中封装的sql语句
            
            conn.close();
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    public Boolean disableUser(int userID){
        try{    
            DatabaseConfig a = new DatabaseConfig(DatabaseConfig.DatabaseCode.DATABASE_ORACLE_12C, "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@//111.231.225.37:1521/DatabaseInsights", "di", "DI2017");
            DatabaseHelper test = new DatabaseHelper(a);          
            Class.forName(a.getDriver());
            conn = DriverManager.getConnection(a.getHost(), a.getUsername(), a.getPassword());
            
            Statement state=conn.createStatement();   //容器
            String sql="update T_DI_USER set STATUS=0 where USERID='"+userID+"' ";   //SQL语句
            state.executeUpdate(sql);  
            
            conn.close();
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    public Boolean alterUser(int userID,int changed_number,String charged_key_value){
        try{    
            DatabaseConfig a = new DatabaseConfig(DatabaseConfig.DatabaseCode.DATABASE_ORACLE_12C, "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@//111.231.225.37:1521/DatabaseInsights", "di", "DI2017");
            DatabaseHelper test = new DatabaseHelper(a);          
            Class.forName(a.getDriver());
            conn = DriverManager.getConnection(a.getHost(), a.getUsername(), a.getPassword());
            
            Statement state=conn.createStatement();   //容器
            String sql="";   //SQL语句
            
            if(changed_number==UserManager.columnNumber.username){
                sql="update T_DI_USER set username='"+charged_key_value+"' where USERID='"+userID+"' "; 
            }
            if(changed_number==UserManager.columnNumber.password){
                sql="update T_DI_USER set password='"+charged_key_value+"' where USERID='"+userID+"' "; 
            }
            if(changed_number==UserManager.columnNumber.status){
                sql="update T_DI_USER set status='"+Integer.parseInt(charged_key_value)+"' where USERID='"+userID+"' "; 
            }
            if(changed_number==UserManager.columnNumber.email){
                sql="update T_DI_USER set email='"+charged_key_value+"' where USERID='"+userID+"' "; 
            }
            state.executeUpdate(sql);  
            System.out.println(sql);
            conn.close();
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        
        return true;
    }
    
    public static void main(String[] args){
        UserManager manager=new UserManager();
        System.out.println(manager.validateUser("vicky", "123"));
    }
    
}
