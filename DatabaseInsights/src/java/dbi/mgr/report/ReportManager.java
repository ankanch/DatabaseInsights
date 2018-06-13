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
import dbi.utils.DBIDataExchange;
import dbi.utils.DBIResultSet;
import dbi.utils.DataValidation;
import dbi.utils.Debug;
import dbi.utils.GlobeVar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            String sql = "INSERT INTO T_DATABASE_REPORT VALUES({0},''{1}'',''{2}'',''{3}'',''{4}'')";
            rep.userid = uid;
            Debug.log("Report to be commited:", rep);
            sql = MessageFormat.format(sql, rep.userid, rep.title, rep.generatedate, rep.des,
                    DataValidation.encodeToBase64(DBIDataExchange.makeupInternalExchangeData(rep.charts.getRows())));
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
            String sql = "SELECT * FROM T_DATABASE_REPORT WHERE RID=" + String.valueOf(repid);
            DBIResultSet ret = dbhelper.runSQLForResult(sql);
            rep.id = Integer.parseInt(ret.getData(1, 1, String.class));
            rep.userid = Integer.parseInt(ret.getData(1, 2, String.class));
            rep.title = ret.getData(1, 3, String.class);
            rep.generatedate = ret.getData(1, 4, String.class);
            rep.des = ret.getData(1, 5, String.class);
            rep.charts = DBIDataExchange.parse(DataValidation.decodeFromBase64(ret.getData(1, 6, String.class)));
        }
        return rep;
    }

    /**
     * 给定用户ID，获得该用户所有分析报告列表
     */
    public ArrayList<Report> getUserReportsList(int uid) {
        ArrayList<Report> replist = new ArrayList<>();
        if (dbhelper.Connect()) {
            String sql = "SELECT RID,USERID,TITLE,GENERATEDATE,DESCRIPTION FROM T_DATABASE_REPORT WHERE USERID=" + String.valueOf(uid);
            DBIResultSet ret = dbhelper.runSQLForResult(sql);
            for (int i = 1; i <= ret.rowCount(); i++) {
                Report rep = new Report();
                rep.id = Integer.parseInt(ret.getData(i, 1, String.class));
                rep.userid = Integer.parseInt(ret.getData(i, 2, String.class));
                rep.title = ret.getData(i, 3, String.class);
                rep.generatedate = ret.getData(i, 4, String.class);
                rep.des = ret.getData(i, 5, String.class);
                replist.add(rep);
            }
        }
        return replist;
    }

    public boolean saveReport(String content, String id) {
        int count = 1000;//写文件行数
        try {
            File file = new File("../data/reports/"+id);
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException ex) {
            Debug.error(ex);
            return false;
        }
        return true;
    }

    public static void main(String[] argv) {
        /*
        ReportManager rm = new ReportManager();
        Report rp = new Report();
        //Debug.log("addReport=",rm.addReport(43, rp));
        Debug.log("getReport=", rm.getReport(1));
        Debug.log("getUserReportsList=", rm.getUserReportsList(43));
         */
        ReportManager report = new ReportManager();
        String test = "ghsadgshadsgadjghsajg";
        Debug.log("写入文件是否成功：", report.saveReport(test, "test1"));
    }

}
