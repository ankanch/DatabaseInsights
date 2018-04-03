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

import dbi.db.adaptor.DatabaseConfig;
import dbi.db.adaptor.DatabaseHelper;
import dbi.mgr.user.UserManager;
import dbi.utils.GlobeVar;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Miss Zhang
 */
public class CredentialManager {
     private Connection conn;
    private final DatabaseConfig dbconfig = GlobeVar.VAR_DATABASE_CONFIG;
    private final DatabaseHelper dbhelper = new DatabaseHelper(dbconfig);
    public Boolean addCredential(String dbhost,String dbname,String dbport,String dbuser,String dbpod,String id){
        if (dbhelper.Connect()) {
            dbhost=dbhost+":"+dbport;
            try {
                Object rv = dbhelper.executeOracleFunction("F_CREATE_CREDENTIAL(?,?,?,?,?)", dbhost, dbname,dbuser,dbpod, id);
                if((int)rv==1){
                    System.out.println("成功创建credential");
                }else if((int)rv==-1){
                    System.out.println("创建失败");
                    return false;
                }
            } catch (Exception e) {
                 System.out.println(e);
                 return false;
            }
        }
        return true;
    }
    
    
    public Boolean deleteCredential(String crdid){
        return true;
    }
    public Boolean alterCredential(String crdid,String charged_key_value){
        return true;
    }
    public Boolean validiateCreditial(String crd){
        return true;
    }
    
    public static void main(String[] args) {
        CredentialManager manager = new CredentialManager();
        //addCredential(String dbhost,String dbname,String dbport,String dbuser,String dbpod,int id){
        System.out.println(manager.addCredential("123host","123name","123port","123user","www123","123123"));
    }
 
}
