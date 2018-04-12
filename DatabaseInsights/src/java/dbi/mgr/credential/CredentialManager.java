
/*
 * The MIT License
 *
 * *** Copyright © ChengShiyi (Miss Zhang)
 * *** Code created on  三月 11 2018
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
package dbi.mgr.credential;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import dbi.db.adaptor.DatabaseConfig;
import dbi.db.adaptor.DatabaseHelper;
import dbi.mgr.user.UserManager;
import dbi.utils.DBIResultSet;
import dbi.utils.GlobeVar;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Miss Zhang
 */
public class CredentialManager {
    private static String DEBUG_PREFIX = "DBI|DEBUG|:@>>CredentialManager>>";
     private Connection conn;
    private final DatabaseConfig dbconfig = GlobeVar.VAR_DATABASE_CONFIG;
    private final DatabaseHelper dbhelper = new DatabaseHelper(dbconfig);
    public Boolean addCredential(String dbhost,String dbname,String dbuser,String dbpawd,String userid,String dbtype){
        if (dbhelper.Connect()) {
            
            try {
                int rv = (int)dbhelper.executeOracleFunction("F_CREATE_CREDENTIAL(?,?,?,?,?,?)", dbhost,dbname,dbuser,dbpawd,userid,dbtype);
                if(rv==1){
                    System.out.println("成功创建credential");
                }else if(rv==-3){
                    System.out.println("未找到数据");
                    return false;
                }else if(rv==-1){
                    System.out.println("创建失败");
                }
            } catch (Exception e) {
                 System.out.println(e);
                 return false;
            }
        }
        return true;
    }
    
    
    public Boolean deleteCredential(String crdid){
        if (dbhelper.Connect()) {
            try {
                Object rv = dbhelper.executeOracleFunction("F_DELETE_CREDENTIAL(?)", crdid);
                if((int)rv==1){
                    System.out.println("成功删除凭证");
                }else if((int)rv==-1){
                    System.out.println("凭证不存在，无法删除");
                    return false;
                }
            } catch (Exception e) {
                 System.out.println(e);
                 return false;
            }
        }
        return true;
    }
    public Boolean alterCredential(String crdid,String dbname,String dbhost,String password){
        System.out.println(DEBUG_PREFIX + "alterCredential()|::INFO>>crdid:"+crdid+",dbname:"+dbname+",dbhost:"+dbhost+",password:"+password);
        if (dbhelper.Connect()) {
            try {
                Object rv = dbhelper.executeOracleFunction("F_ALTER_CREDENTIAL(?,?,?,?)", crdid,dbname,dbhost,password);
                if((int)rv==1){
                    System.out.println("成功更改凭证");
                }else if((int)rv==-1){
                    System.out.println("凭证不存在，无法修改");
                    return false;
                }
            } catch (Exception e) {
                 System.out.println(e);
                 return false;
            }
        }
        return true;
    }
    
    public DBIResultSet getCredential(String sid){
        DBIResultSet result=null;
        String id=null;
        
        if (dbhelper.Connect()) {
            try {
                result=dbhelper.runSelect("name,host,T_DATABASE_CERTIFICATION.password,cid", "T_DATABASE_INFO,T_DATABASE_CERTIFICATION,T_DI_USER ",
                        "T_DATABASE_INFO.did=T_DATABASE_CERTIFICATION.did and T_DI_USER.USERID=T_DATABASE_INFO.USERID and usession='"+sid+"'");
                System.out.println(DEBUG_PREFIX + "getCredential()|::INFO>>credential result row count="+result.rowCount()); 
            } catch (Exception e) {
                 System.out.println(DEBUG_PREFIX + "getCredential()|::ERROR>>error:"+e);
            }
        }
        
        return result;
    }
    
    public Boolean validiateCreditial(String dbscode,String dbhost,String dbname,String dbuser,String dbpwd,int dbcode){
        Boolean status=false;
                //check wether the infomation provaided is correct
        if (DatabaseConfig.DatabaseCode.check(dbcode)) {
            DatabaseConfig dbc = new DatabaseConfig(dbcode, DatabaseConfig.DatabaseDriver.chooseDriver(dbcode),
                    DatabaseConfig.JDBCHostPrefix.autoGenHost(dbcode, dbhost, dbname),
                    dbuser, dbpwd);
            DatabaseHelper test = new DatabaseHelper(dbc);
            if( test.Connect() ){
                status = true;
            }
        }
        return status;
    }
    
    public static void main(String[] args) {
        CredentialManager a=new CredentialManager();
        DBIResultSet q=a.getCredential("8844239157E89865A7F20DC17323C441");
        System.out.println(q.getRow(1));
        System.out.println(q.getRow(2));
    }
 
}
