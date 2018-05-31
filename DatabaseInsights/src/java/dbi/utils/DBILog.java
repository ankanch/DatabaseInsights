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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Miss Zhang
 */
public class DBILog {

    public class DBILogItem {

        public int userid;
        public int log_type;
        public String dbname;
        public String tname;
        public String message;
        public String logtime;

        @Override
        public String toString() {
            return " VALUES(" + userid + "," + log_type + ",'" + logtime + "','" + dbname + "','" + tname + "','" + message + "') ";
        }
    }

    public String dbname;
    public String tname;
    public int userid;
    private ArrayList<DBILogItem> reportlist = new ArrayList<>();
    private Connection conn;
    private final DatabaseConfig dbconfig = GlobeVar.VAR_DATABASE_CONFIG;
    private final DatabaseHelper dbhelper = new DatabaseHelper(dbconfig);
    private static final int TYPE_INFO = 1000;
    private static final int TYPE_ERROR = 1005;
    private static final int TYPE_WARNING = 1010;

    public DBILog(String dbname, String tname, int userid) {
        this.dbname = dbname;
        this.tname = tname;
        this.userid = userid;
    }

    public DBILog() {
    }
    
    void log(int userid, int type, String dbname, String tbname, String message) {
        DBILogItem logitem = new DBILogItem();
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String localtime = df.format(day);
        logitem.userid = userid;
        logitem.log_type = type;
        logitem.logtime = localtime;
        logitem.dbname = dbname;
        logitem.tname = tbname;
        logitem.message = message;
        reportlist.add(logitem);
    }

    void log(int type, String message) {
        DBILogItem logitem = new DBILogItem();
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String localtime = df.format(day);
        logitem.userid = userid;
        logitem.log_type = type;
        logitem.logtime = localtime;
        logitem.dbname = dbname;
        logitem.tname = tname;
        logitem.message = message;
        reportlist.add(logitem);
    }

    void logInfo(String message) {
        DBILogItem logitem = new DBILogItem();
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String localtime = df.format(day);
        logitem.userid = userid;
        logitem.log_type = TYPE_INFO;
        logitem.logtime = localtime;
        logitem.dbname = dbname;
        logitem.tname = tname;
        logitem.message = message;
        reportlist.add(logitem);
    }

    void logError(String message) {
        DBILogItem logitem = new DBILogItem();
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String localtime = df.format(day);
        logitem.userid = userid;
        logitem.log_type = TYPE_ERROR;
        logitem.logtime = localtime;
        logitem.dbname = dbname;
        logitem.tname = tname;
        logitem.message = message;
        reportlist.add(logitem);
    }

    void logWarning(String message) {
        DBILogItem logitem = new DBILogItem();
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String localtime = df.format(day);
        logitem.userid = userid;
        logitem.log_type = TYPE_WARNING;
        logitem.logtime = localtime;
        logitem.dbname = dbname;
        logitem.tname = tname;
        logitem.message = message;
        reportlist.add(logitem);
    }

    void log(DBILogItem logitem) {
        reportlist.add(logitem);
    }

    void commit() {
        if (dbhelper.Connect()) {
            for (DBILogItem logitem : reportlist) {
                String sql = "INSERT INTO T_DI_LOG" + logitem;
                if (dbhelper.runSQL(sql)) {
                    System.out.println("success:" + sql);
                }
            }
        }
    }

    DBIResultSet searchlog(String uid, String time) {
        String times = "";
        DBIResultSet result = null;
        switch (time) {
            case "7天":
                times = "where LDATE between sysdate - interval '7' day and sysdate and userid='" + uid + "'";
                break;
            case "1个月":
                times = "where LDATE between sysdate - interval '30' day and sysdate and userid='" + uid + "'";
                break;
            case "6个月":
                times = "where LDATE between sysdate - 150 and sysdate and userid='" + uid + "'";
                break;
            case "7个月":
                times = "where LDATE between sysdate - interval '7' month and sysdate and userid='" + uid + "'";
                break;
            case "1年":
                times = "where LDATE between sysdate - 365 and sysdate and userid='" + uid + "'";
                break;
            case "全部":
                times = "where userid='" + uid + "'";
                break;
        }
        String sql = "select * from T_DI_LOG " + times;
        if (dbhelper.Connect()) {
            result = dbhelper.runSQLForResult(sql);
        } else {
            System.out.println("wrong");
        }
        return result;
    }

    public static void main(String args[]) {
    }
}
