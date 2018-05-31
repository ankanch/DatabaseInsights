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
import java.util.ArrayList;

/**
 *
 * @author kanch
 */
public class analyzerUtils {

    private static DatabaseConfig dbconfig = GlobeVar.VAR_DATABASE_CONFIG;
    private static DatabaseHelper dbhelper = new DatabaseHelper(dbconfig);
    
    private int uid;
    private String database;
    private String table;

    public analyzerUtils(int uid, String database, String table) {
        this.uid = uid;
        this.database = database;
        this.table = table;
    }
    

    /**
     * 从columnspecies中得到指定用户的指定表的所有列的全部信息<br/>
     * 返回值：DBIResultSet，每一行包含表的一列的全部信息
     */
    public static DBIResultSet getAllColumnSpecies(int uid, String table) {
        DBIResultSet dbire = new DBIResultSet();
        String sql = "select * from T_DATABASE_COLUMN where TID in (select tid from T_DATABASE_TABLE where tname='"
                + table + "') "
                + "and userid=" + uid;
        if (dbhelper.Connect()) {
            dbire = dbhelper.runSQLForResult(sql);
        }
        dbhelper.Disconnect();
        return dbire;
    }

    /**
     * 得到指定用户的指定表每一列不重复的值的数量<br/>
     * 返回值：DBIResultSet，多行，每行由2列组成，分别存放列名，列不重复值个数
     */
    public static DBIResultSet findDistinctValues(int uid, String table) {
        DBIResultSet ret = new DBIResultSet();
        if (dbhelper.Connect()) {
            DatabaseHelper user = dbhelper.getUserdbhelper(uid);
            if (DatabaseHelper.isAvailable(user)) {
                String column = "";
                DBIResultSet tablename = user.runSQLForResult("select distinct columnname  from t_database_column where userid=" + uid + " and "
                        + "tid in(select tid from T_DATABASE_TABLE where tname='" + table + "')");
                for (int i = 0; i < tablename.rowCount() - 1; i++) {
                    column = column + "count(distinct " + tablename.getRow(i + 1).get(0) + "),";
                }
                column = column + " count(distinct " + tablename.getRow(tablename.rowCount()).get(0) + ")";
                Debug.log(column);
                String sql = "select " + column + " from " + table;
                Debug.log(sql);
                DBIResultSet dbire = user.runSQLForResult(sql);
                //拼凑结果集 ---格式： 多行，每一行为 [列名,列不重复值个数]
                for (int i = 1; i <= tablename.rowCount(); i++) {
                    ArrayList<Object> row = new ArrayList<>();
                    row.add(tablename.getData(i, 1));
                    row.add(dbire.getData(1, i));
                    ret.addRow(row);
                }
                user.Disconnect();
            }
        }
        return ret;
    }

    /**
     * 得到指定用户的指定表每一列最大的值<br/>
     * 返回值：DBIResultSet，只有一行，按照在表中列的顺序依次展示每一列最大的值
     */
    public static DBIResultSet getMaxiumValues(int uid, String table) {
        DBIResultSet dbire = new DBIResultSet();

        if (dbhelper.Connect()) {
            DatabaseHelper user = dbhelper.getUserdbhelper(uid);
            if (DatabaseHelper.isAvailable(user)) {
                DBIResultSet result = user.getIsNumberColumns(uid, table);
                for (int i = 0; i < result.rowCount(); i++) {
                    if (result.getRow(i + 1).get(1).equals("1")) {
                        DBIResultSet max = user.runSQLForResult("select max(" + result.getRow(i + 1).get(0) + ") from " + table);
                        dbire.addToRow(max.getRow(1).get(0));
                    } else {
                        dbire.addToRow(null);
                    }
                }
                dbire.finishRow();
                user.Disconnect();
            }
        }
        return dbire;
    }

    /**
     * 得到指定用户的指定表每一列最小的值<br/>
     * 返回值：DBIResultSet，只有一行，按照在表中列的顺序依次展示每一列最小的值
     */
    public static DBIResultSet getMiniumValues(int uid, String table) {
        DBIResultSet dbire = new DBIResultSet();

        if (dbhelper.Connect()) {
            DatabaseHelper user = dbhelper.getUserdbhelper(uid);
            if (DatabaseHelper.isAvailable(user)) {
                DBIResultSet result = user.getIsNumberColumns(uid, table);
                for (int i = 0; i < result.rowCount(); i++) {
                    if (result.getRow(i + 1).get(1).equals("1")) {
                        DBIResultSet min = user.runSQLForResult("select min(" + result.getRow(i + 1).get(0) + ") from " + table);
                        dbire.addToRow(min.getRow(1).get(0));
                    } else {
                        dbire.addToRow(null);
                    }
                }
                dbire.finishRow();
                user.Disconnect();
            }
        }
        return dbire;
    }

    /**
     * 得到指定用户的指定表每一列的平均值<br/>
     * 返回值：DBIResultSet，只有一行，按照在表中列的顺序依次展示每一列的平均值
     */
    public static DBIResultSet getAverangeValues(int uid, String table) {
        DBIResultSet dbire = new DBIResultSet();

        if (dbhelper.Connect()) {
            DatabaseHelper user = dbhelper.getUserdbhelper(uid);
            if (DatabaseHelper.isAvailable(user)) {
                DBIResultSet result = user.getIsNumberColumns(uid, table);
                for (int i = 0; i < result.rowCount(); i++) {
                    if (result.getRow(i + 1).get(1).equals("1")) {
                        DBIResultSet avg = user.runSQLForResult("select avg(" + result.getRow(i + 1).get(0) + ") from " + table);
                        dbire.addToRow(avg.getRow(1).get(0));
                    } else {
                        dbire.addToRow(null);
                    }
                }
                dbire.finishRow();
                user.Disconnect();
            }
        }
        return dbire;
    }

    /**
     * 得到指定用户的指定表每一列的和<br/>
     * 返回值：DBIResultSet，只有一行，按照在表中列的顺序依次展示每一列的和
     */
    public static DBIResultSet getAllColumnSum(int uid, String table) {
        DBIResultSet dbire = new DBIResultSet();

        if (dbhelper.Connect()) {
            DatabaseHelper user = dbhelper.getUserdbhelper(uid);
            if (DatabaseHelper.isAvailable(user)) {
                DBIResultSet result = user.getIsNumberColumns(uid, table);
                for (int i = 0; i < result.rowCount(); i++) {
                    if (result.getRow(i + 1).get(1).equals("1")) {
                        DBIResultSet sum = user.runSQLForResult("select sum(" + result.getRow(i + 1).get(0) + ") from " + table);
                        dbire.addToRow(sum.getRow(1).get(0));
                    } else {
                        dbire.addToRow(null);
                    }
                }
                dbire.finishRow();
                user.Disconnect();
            }
        }
        return dbire;
    }

    /**
     * 获取给定表的给定列的所有不同值以及其个数<br/>
     * 返回值：DBIResultSet，多行，每行由3列组成，分别存放列名字，不同值，值出现次数
     */
    public static DBIResultSet getDistinctValuesCount(int uid, String table, Object[] columns) {
        DBIResultSet ret = new DBIResultSet();
        if (columns.length < 1) {
            return ret;
        }
        if (dbhelper.Connect()) {
            DatabaseHelper user = dbhelper.getUserdbhelper(uid);
            if (DatabaseHelper.isAvailable(user)) {
                for (int i = 0; i < columns.length; i++) {
                    DBIResultSet tablename = user.runSQLForResult("select count(" + columns[i] + ")," + columns[i] + " from " + table + " group by " + columns[i] + "");
                    for (int j = 0; j < tablename.rowCount(); j++) {
                        ArrayList<Object> row = new ArrayList<>();
                        row.add(columns[i]);
                        row.add(tablename.getData(j + 1, 2));
                        row.add(tablename.getData(j + 1, 1));
                        ret.addRow(row);
                    }
                }
                user.Disconnect();
            }
        }
        return ret;
    }

    /**
     * 获取给定表的给定列的所有值列表<br/>
     * 返回值：DBIResultSet，多行，每行由2列组成，第一列为列名，第2列的所有值数组
     */
    public static DBIResultSet getColumnValues(int uid, String table, Object[] columns) {
        DBIResultSet ret = new DBIResultSet();
        if (columns.length < 1) {
            return ret;
        }
        if (dbhelper.Connect()) {
            DatabaseHelper user = dbhelper.getUserdbhelper(uid);
            if (DatabaseHelper.isAvailable(user)) {
                for (int i = 0; i < columns.length; i++) {
                    DBIResultSet coldata = user.runSQLForResult("select " + columns[i] + " from " + table);
                    Debug.log("coldata=", coldata);
                    ret.addToRow(columns[i]);
                    ret.addToRow(coldata);
                    ret.finishRow();
                }
                user.Disconnect();
            }
        }
        return ret;
    }

    /**
     * *********************************************************************
     * Pooling functions for customize analyze on Columns
     * *********************************************************************
     */
    /**
     * 获取给定列的平均值<br/>
     * 返回值：DBIResultSet，1行，列名，平均值
     */
    public static DBIResultSet getColumnAverage(int uid, String table, String column) {
        DBIResultSet ret = new DBIResultSet();
        if (dbhelper.Connect()) {
            DatabaseHelper user = dbhelper.getUserdbhelper(uid);
            if (DatabaseHelper.isAvailable(user)) {
                String sql = "SELECT AVG(" + column + ") FROM " + table;
                ret = user.runSQLForResult(sql);
                user.Disconnect();
            }
        }
        return ret;
    }

    /**
     * 获取给定列的最大值<br/>
     * 返回值：DBIResultSet，1行，列名，最大值
     */
    public static DBIResultSet getColumnMaxiumValue(int uid, String table, String column) {
        DBIResultSet ret = new DBIResultSet();
        if (dbhelper.Connect()) {
            DatabaseHelper user = dbhelper.getUserdbhelper(uid);
            if (DatabaseHelper.isAvailable(user)) {
                String sql = "SELECT MAX(" + column + ") FROM " + table;
                ret = user.runSQLForResult(sql);
                user.Disconnect();
            }
        }
        return ret;
    }

    /**
     * 获取给定列的最小值<br/>
     * 返回值：DBIResultSet，1行，列名，最小值
     */
    public static DBIResultSet getColumnMiniumValue(int uid, String table, String column) {
        DBIResultSet ret = new DBIResultSet();
        if (dbhelper.Connect()) {
            DatabaseHelper user = dbhelper.getUserdbhelper(uid);
            if (DatabaseHelper.isAvailable(user)) {
                String sql = "SELECT MIN(" + column + ") FROM " + table;;
                ret = user.runSQLForResult(sql);
                user.Disconnect();
            }
        }
        return ret;
    }

    /**
     * 获取给定列的总和<br/>
     * 返回值：DBIResultSet，1行，列名，总和
     */
    public static DBIResultSet getColumnSum(int uid, String table, String column) {
        DBIResultSet ret = new DBIResultSet();
        if (dbhelper.Connect()) {
            DatabaseHelper user = dbhelper.getUserdbhelper(uid);
            if (DatabaseHelper.isAvailable(user)) {
                String sql = "SELECT SUM(" + column + ") FROM " + table;
                ret = user.runSQLForResult(sql);
                user.Disconnect();
            }
        }
        return ret;
    }

    /**
     * 获取给定列所有值的分组统计结果<br/>
     * 返回值：DBIResultSet，多行，每行由2列组成，第一列为值，第2列为数量
     */
    public static DBIResultSet getColumnValueCounts(int uid, String table, String column) {
        DBIResultSet ret = new DBIResultSet();
        if (dbhelper.Connect()) {
            DatabaseHelper user = dbhelper.getUserdbhelper(uid);
            if (DatabaseHelper.isAvailable(user)) {
                String sql = "SELECT " + column + ",COUNT(" + column + ") FROM " + table + " GROUP BY " + column;
                ret = user.runSQLForResult(sql);
                user.Disconnect();
            }
        }
        return ret;
    }

    /**
     * 获取给定列所有不同值的个数<br/>
     * 返回值：DBIResultSet，多行，每行由1列组成，存放不同数据值
     */
    public static DBIResultSet getColumnValueCountsNoRepeat(int uid, String table, String column) {
        DBIResultSet ret = new DBIResultSet();
        if (dbhelper.Connect()) {
            DatabaseHelper user = dbhelper.getUserdbhelper(uid);
            if (DatabaseHelper.isAvailable(user)) {
                String sql = "SELECT DISTINCT " + column + " FROM " + table;
                ret = user.runSQLForResult(sql);
                user.Disconnect();
            }
        }
        return ret;
    }

    public static void main(String args[]) {
        Debug.log("getAllColumnSpecies=", analyzerUtils.getAllColumnSpecies(43, "T_DI_USER"));
        Debug.log("findDistinctValues=", analyzerUtils.findDistinctValues(43, "T_DI_USER"));
        Debug.log("getMaxiumValues=", analyzerUtils.getMaxiumValues(43, "T_DI_USER"));
        Debug.log("getMiniumValues=", analyzerUtils.getMiniumValues(43, "T_DI_USER"));
        Debug.log("getAverangeValues=", analyzerUtils.getAverangeValues(43, "T_DI_USER"));
        Debug.log("getAllColumnSum=", analyzerUtils.getAllColumnSum(43, "T_DI_USER"));
        String columns[] = {"password", "email"};
        Debug.log("getDistinctValuesCount=", analyzerUtils.getDistinctValuesCount(43, "T_DI_USER", columns));
        Debug.log("getColumnAverage=",analyzerUtils.getColumnAverage(43, "T_DI_USER", "USERID"));
        Debug.log("getColumnAverage=",analyzerUtils.getColumnMaxiumValue(43, "T_DI_USER", "STATUS"));
        Debug.log("getColumnMiniumValue=",analyzerUtils.getColumnMiniumValue(43, "T_DI_USER", "STATUS"));
        Debug.log("getColumnSum=",analyzerUtils.getColumnSum(43, "T_DI_USER", "STATUS"));
        Debug.log("getColumnValueCounts=",analyzerUtils.getColumnValueCounts(43, "T_DI_USER", "STATUS"));
        Debug.log("getColumnValueCountsNoRepeat=",analyzerUtils.getColumnValueCountsNoRepeat(43, "T_DI_USER", "STATUS"));
    }

}
