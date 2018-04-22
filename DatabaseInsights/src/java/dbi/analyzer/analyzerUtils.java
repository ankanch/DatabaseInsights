/*
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code created on Apr 17 2018
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
package dbi.analyzer;

import dbi.db.adaptor.DatabaseConfig;
import dbi.db.adaptor.DatabaseHelper;
import dbi.utils.DBIResultSet;
import dbi.utils.Debug;
import dbi.utils.GlobeVar;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 *
 * @author kanch
 */
public class analyzerUtils {

    private static DatabaseConfig dbconfig = GlobeVar.VAR_DATABASE_CONFIG;
    private static DatabaseHelper dbhelper = new DatabaseHelper(dbconfig);
    /**
    * 从columnspecies中得到指定用户的指定表的所有列的全部信息<br/>
    * 返回值：DBIResultSet，每一行包含表的一列的全部信息
     */
    public static DBIResultSet getAllColumnSpecies(int uid,String table) {
        DBIResultSet dbire = new DBIResultSet();
        String sql="select * from T_DATABASE_COLUMN\n" +
        "where TID in (select tid from T_DATABASE_TABLE where tname='"+table+"')\n" +
        "and userid="+uid;
        DatabaseHelper user=null;
        if(dbhelper.Connect()){
            dbire=dbhelper.runSQLForResult(sql);
        }
        dbhelper.Disconnect();
        return dbire;
    }


    /**
    * 得到指定用户的指定表每一列不重复的值的数量<br/>
    * 返回值：DBIResultSet，只有一行，按照在表中列的顺序依次展示每一列不同值的个数
     */
    public static DBIResultSet findDistinctValues(int uid,String table) {
        DBIResultSet dbire = new DBIResultSet();
        DatabaseHelper user=null;
        if(dbhelper.Connect()){
            user=dbhelper.getUserdbhelper(uid);
        }
        dbhelper.Disconnect();
        
        if (user.Connect()){
            String column="";
            DBIResultSet tablename=user.runSQLForResult("select distinct columnname  from t_database_column where userid="+uid+" and\n" +
            "tid in(select tid from T_DATABASE_TABLE where tname='"+table+"')");
            
            for(int i=0;i<tablename.rowCount()-1;i++){
                column=column+"count(distinct "+tablename.getRow(i+1).get(0)+"),";
            }
            column=column+" count(distinct "+tablename.getRow(tablename.rowCount()).get(0)+")";
            Debug.log(column);
            String sql="select "+column+" from "+table+
                       " where userid="+uid;
            Debug.log(sql);
            dbire=user.runSQLForResult(sql);
        }
        user.Disconnect();
        return dbire;
    }

    /**
    * 得到指定用户的指定表每一列最大的值<br/>
    * 返回值：DBIResultSet，只有一行，按照在表中列的顺序依次展示每一列最大的值
     */
    public static DBIResultSet getMaxiumValues(int uid,String table) {
        DBIResultSet dbire=new DBIResultSet();
        DBIResultSet result=new DBIResultSet();
        DBIResultSet max=new DBIResultSet();
        
        DatabaseHelper user=null;
        if(dbhelper.Connect()){
            user=dbhelper.getUserdbhelper(uid);
        }
        dbhelper.Disconnect();
        
        if(user.Connect()){
            result=user.getIsNumberColumns(uid, table);
            for(int i=0;i<result.rowCount();i++){
                if(result.getRow(i+1).get(1).equals("1")){
                    max=user.runSQLForResult("select max("+result.getRow(i+1).get(0)+") from "+table);
                    dbire.addToRow(max.getRow(1).get(0));
                }else{
                    dbire.addToRow(null);
                }
            }
            dbire.finishRow();
        }
        user.Disconnect();
        return dbire;
    }
    
    /**
    * 得到指定用户的指定表每一列最小的值<br/>
    * 返回值：DBIResultSet，只有一行，按照在表中列的顺序依次展示每一列最小的值
     */
    public static DBIResultSet getMiniumValues(int uid,String table) {
        DBIResultSet dbire=new DBIResultSet();
        DBIResultSet result=new DBIResultSet();
        DBIResultSet min=new DBIResultSet();
        
        DatabaseHelper user=null;
        if(dbhelper.Connect()){
            user=dbhelper.getUserdbhelper(uid);
        }
        dbhelper.Disconnect();
        
        if(user.Connect()){
            result=user.getIsNumberColumns(uid, table);
            for(int i=0;i<result.rowCount();i++){
                if(result.getRow(i+1).get(1).equals("1")){
                    min=user.runSQLForResult("select min("+result.getRow(i+1).get(0)+") from "+table);
                    dbire.addToRow(min.getRow(1).get(0));
                }else{
                    dbire.addToRow(null);
                }
            }
            dbire.finishRow();
        }
        user.Disconnect();
        return dbire;
    }

    /**
    * 得到指定用户的指定表每一列的平均值<br/>
    * 返回值：DBIResultSet，只有一行，按照在表中列的顺序依次展示每一列的平均值
     */
    public static DBIResultSet getAverangeValues(int uid,String table) {
        DBIResultSet dbire=new DBIResultSet();
        DBIResultSet result=new DBIResultSet();
        DBIResultSet avg=new DBIResultSet();
        
        DatabaseHelper user=null;
        if(dbhelper.Connect()){
            user=dbhelper.getUserdbhelper(uid);
        }
        dbhelper.Disconnect();
        
        if(user.Connect()){
            result=user.getIsNumberColumns(uid, table);
            for(int i=0;i<result.rowCount();i++){
                if(result.getRow(i+1).get(1).equals("1")){
                    avg=user.runSQLForResult("select avg("+result.getRow(i+1).get(0)+") from "+table);
                    dbire.addToRow(avg.getRow(1).get(0));
                }else{
                    dbire.addToRow(null);
                }
            }
            dbire.finishRow();
        }
        user.Disconnect();
        return dbire;
    }

    /**
    * 得到指定用户的指定表每一列的和<br/>
    * 返回值：DBIResultSet，只有一行，按照在表中列的顺序依次展示每一列的和
     */
    public static DBIResultSet getAllColumnSum(int uid,String table) {
        DBIResultSet dbire=new DBIResultSet();
        DBIResultSet result=new DBIResultSet();
        DBIResultSet sum=new DBIResultSet();
        
        DatabaseHelper user=null;
        if(dbhelper.Connect()){
            user=dbhelper.getUserdbhelper(uid);
        }
        dbhelper.Disconnect();
        
        if(user.Connect()){
            result=user.getIsNumberColumns(uid, table);
            for(int i=0;i<result.rowCount();i++){
                if(result.getRow(i+1).get(1).equals("1")){
                    sum=user.runSQLForResult("select sum("+result.getRow(i+1).get(0)+") from "+table);
                    dbire.addToRow(sum.getRow(1).get(0));
                }else{
                    dbire.addToRow(null);
                }
            }
            dbire.finishRow();
        }
        user.Disconnect();
        return dbire;
    }
    
    public static void main(String args[]){
        analyzerUtils test=new analyzerUtils();
        DBIResultSet result=test.getAllColumnSpecies(43,"T_DI_USER");
        DBIResultSet result1=test.findDistinctValues(43,"T_DI_USER");
        DBIResultSet result2=test.getMaxiumValues(43,"T_DI_USER");
        DBIResultSet result3=test.getMiniumValues(43,"T_DI_USER");
        DBIResultSet result4=test.getAverangeValues(43,"T_DI_USER");
        DBIResultSet result5=test.getAllColumnSum(43,"T_DI_USER"); 
        for(int i=0;i<result.rowCount();i++){
            Debug.log(result.getRow(i+1));
        }    
        Debug.log(result1.getRow(1));
        Debug.log(result2.getRow(1));
        Debug.log(result3.getRow(1));
        Debug.log(result4.getRow(1));
        Debug.log(result5.getRow(1));
    }

}
