/*
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code created on May 22 2018
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
package dbi.mgr.report;

import dbi.db.adaptor.DatabaseConfig;
import dbi.db.adaptor.DatabaseHelper;
import dbi.utils.DBIResultSet;
import dbi.utils.Debug;
import dbi.utils.GlobeVar;
import java.sql.Connection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kanch
 */
public class ReportManager {

    private Connection conn;
    private final DatabaseConfig dbconfig = GlobeVar.VAR_DATABASE_CONFIG;
    private final DatabaseHelper dbhelper;
    private ArrayList<Report> reportlist;

    public ReportManager() {
        dbhelper = new DatabaseHelper(dbconfig);
        reportlist = new ArrayList<>();
        dbhelper.Connect();
    }

    /*
    * 向给定用户添加一个分析报告
    */
    public boolean addReport(int uid, Report rep) {
        if (dbhelper.Connect()) {
            String sql = "INSERT INTO T_DATABASE_REPORT VALUES({0},{1},{2},{3},{4})";
            Date now = new Date();
            sql = MessageFormat.format(sql, rep.userid, rep.title, now, rep.des, rep.charts);
            if (dbhelper.runSQL(sql)) {
                return true;
            }
        }
        return false;
    }

    /*
    * 获得指定ID的分析报告信息
    */
    public Report getReport(int repid) {
        Report rep = new Report();
        if (dbhelper.Connect()) {
            String sql = "SELECT * FROM T_DATABASE_REPORT WHERE RID=";
            DBIResultSet ret = dbhelper.runSQLForResult(sql);
            Debug.log(ret);
        }
        return rep;
    }

    /**
     * 给定用户ID，获得该用户所有分析报告列表
     */
    public ArrayList<Report> getUserReportsList(int uid) {
        ArrayList<Report> replist = new ArrayList<>();
        if (dbhelper.Connect()) {
            String sql = "SELECT RID,USERID,TITLE,GENERATEDATE,DESCRIPTION FROM T_DATABASE_REPORT WHERE USERID=";
            DBIResultSet ret = dbhelper.runSQLForResult(sql);
            Debug.log(ret);
        }
        return replist;
    }
    
    public static void main(String[] argv){
        ReportManager rm = new ReportManager();
        Report rp = new Report();
        Debug.log("addReport=",rm.addReport(43, rp));
        Debug.log("getReport=",rm.getReport( 1));
        Debug.log("getUserReportsList=",rm.getUserReportsList(43));
    }

}
