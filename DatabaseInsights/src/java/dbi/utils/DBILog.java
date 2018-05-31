/*
 * The MIT License
 *
 * *** Copyright © ChengShiyi (Miss Zhang)
 * *** Code created on  五月 21 2018
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
package dbi.utils;

import dbi.db.adaptor.DatabaseConfig;
import dbi.db.adaptor.DatabaseHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miss Zhang
 */
public class DBILog {
    private ArrayList<ArrayList<Object>> reportlist=new ArrayList<>();
    private ArrayList<Object> srow = new ArrayList<>();
    private DBIResultSet result=new DBIResultSet();
    private Connection conn;
    private final DatabaseConfig dbconfig = GlobeVar.VAR_DATABASE_CONFIG;
    private final DatabaseHelper dbhelper = new DatabaseHelper(dbconfig);
    void log(int userid,int type,String dbname,String tbname,String message){
        Date day=new Date();    
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        String localtime=df.format(day);
        srow.add(userid);
        srow.add(type);
        srow.add(localtime);
        srow.add(dbname);
        srow.add(tbname);
        srow.add(message);
        reportlist.add(srow);
        srow.clear();
    }
    
    void commit(){
        if(dbhelper.Connect()){
            for(int i=0;i<reportlist.size();i++){
                for(int j=0;j<srow.size();j++){
                    String sql="insert into T_DI_LOG values('"+reportlist.get(i).get(j)+"','"+reportlist.get(i).get(j)+"',"
                            + "'"+reportlist.get(i).get(j)+"','"+reportlist.get(i).get(j)+"','"+reportlist.get(i).get(j)+"'"
                            + ",'"+reportlist.get(i).get(j)+"')";
                    if(dbhelper.runSQL(sql)){
                        System.out.println("success:"+sql);
                    }
                }
            }
        }
        dbhelper.Disconnect();
    }
    
    public static void main(String args[]){
        
    }
}