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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;
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
    private String filecontext=".\\data\\reports\\";

    public ReportManager() {
        dbhelper = new DatabaseHelper(dbconfig);
        reportlist = new ArrayList<>();
        dbhelper.Connect();
    }

    /*
    * 向给定用户添加一个分析报告
     */
    public boolean addReport(int uid, Report rep) {
        //生成id
        //存入文件
        //插入id到数据库
        String repid = "REP" + UUID.randomUUID().toString().replace("-", "");
        saveReport(DataValidation.encodeToBase64( DBIDataExchange.makeupInternalExchangeData(rep.charts.getRows())),repid );
        if (dbhelper.Connect()) {
            String sql = "INSERT INTO T_DATABASE_REPORT(USERID,TITLE,GENERATEDATE,DESCRIPTION,FILEID) VALUES({0},''{1}'',to_date(''{2}'',''YYYY-MM-DD HH24:MI:SS''),''{3}'',''{4}'')";
            rep.userid = uid;
            Debug.log("Report to be commited:", rep);
            sql = MessageFormat.format(sql, rep.userid, rep.title, rep.generatedate, rep.des,
                    repid);
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
            String filename=ret.getData(1, 6, String.class);
            rep.charts = DBIDataExchange.parse(DataValidation.decodeFromBase64( readFile(filename)) );
        }
        return rep;
    }
    
    public String readFile(String filename){
        String encoding = "UTF-8";  
        File file = new File(filecontext+filename);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (IOException e) {  
            Debug.error(e);
            return null;  
        }  
        return new String(filecontent);
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
            File file = new File("C:\\Program Files (x86)\\Apache Software Foundation\\Apache Tomcat 8.0.27\\bin\\data\\reports\\"+id+".txt");
            Debug.log("(1):",file.getCanonicalFile());//获取标准的路径 
            Debug.log("(2)jjjjjjjjjjjjjjjjjjjjjjjjj:",file.getAbsoluteFile());
            if  (!file.exists())      
            {       
                Debug.log("不存在"); 
                file.createNewFile(); 
            } 
            
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getCanonicalFile());
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
        ReportManager report = new ReportManager();
        ReportManager rm = new ReportManager();
        Report rp = new Report();
        //Debug.log("addReport=",rm.addReport(43, rp));
        Debug.log("getReport=", rm.getReport(1));
        Debug.log("getUserReportsList=", rm.getUserReportsList(43));

        */
        
        //Report rep=new Report("no title specified<@REPSP!> <@REPSP!>1<@MSG>no notes provided.<@C>no notes provided.<@C>no notes provided.<@C>no notes provided.<@C>no notes provided.<@C>no notes provided.<@C>no notes provided.<@C><@R>option = {    title : {        text: 'PASSWORD',        subtext: ' ',        x:'center'    },    tooltip : {        trigger: 'item',        formatter: \"{a} <br>{b} : {c} ({d}%)\"    },    legend: {        orient: 'vertical',        right: 10,        top: 100,        bottom: 20,        data: ['DI2017','PWD','TEST_P','test','111',]    },    series : [        {            name: 'value:protion',            type: 'pie',            radius : '55%',            center: ['50%', '60%'],            data:[                {value:1, name:'DI2017'},{value:5, name:'PWD'},{value:2, name:'TEST_P'},{value:1, name:'test'},{value:5, name:'111'},            ],            itemStyle: {                emphasis: {                    shadowBlur: 10,                    shadowOffsetX: 0,                    shadowColor: 'rgba(0, 0, 0, 0.5)'                }            }        }    ]};<@C>option = {    title: {        text: 'STATUS',        subtext: ' ',        x: 'center',        y: 0    },    tooltip: {        formatter: '@TOOLTIP {a}: ({c})'    },    xAxis: [        {gridIndex: 0, min: 0, max: 20},    ],    yAxis: [        {gridIndex: 0, min: 0, max: 15},    ],    series: [        {            name: 'value',            type: 'scatter',            xAxisIndex: 0,            yAxisIndex: 0,            data:  [                        [1,1],[2,1],[3,1],[4,1],[5,1],[6,0],[7,9],[8,-1],[9,1],[10,1],[11,1],[12,1],[13,1],[14,1],                    ],            markLine: {                            animation: true,                            label: {                                normal: {                                    formatter: 'average',                                    textStyle: {                                        align: 'bottom'                                    }                                }                            },                            lineStyle: {                                normal: {                                    type: 'solid'                                }                            },                            tooltip: {                                formatter: 'average:1.35714285714285714285714285714285714286E00'                            },                            data: [[{                                coord: [1, 1.35714285714285714285714285714285714286E00],                                symbol: 'none'                            }, {                                coord: [14, 1.35714285714285714285714285714285714286E00],                                symbol: 'none'                            }]]                        }        }    ]};<@C>option = {    title : {        text: 'LASTLOGIN',        subtext: ' ',        x:'center'    },    tooltip : {        trigger: 'item',        formatter: \"{a} <br>{b} : {c} ({d}%)\"    },    legend: {        orient: 'vertical',        right: 10,        top: 100,        bottom: 20,        data: ['2018-04-15 10:09:31.0','null','2018-06-15 00:04:42.0','2018-04-21 14:34:46.0','2018-06-15 10:13:10.0',]    },    series : [        {            name: 'value:protion',            type: 'pie',            radius : '55%',            center: ['50%', '60%'],            data:[                {value:1, name:'2018-04-15 10:09:31.0'},{value:0, name:'null'},{value:1, name:'2018-06-15 00:04:42.0'},{value:1, name:'2018-04-21 14:34:46.0'},{value:1, name:'2018-06-15 10:13:10.0'},            ],            itemStyle: {                emphasis: {                    shadowBlur: 10,                    shadowOffsetX: 0,                    shadowColor: 'rgba(0, 0, 0, 0.5)'                }            }        }    ]};<@C>option = {    title : {        text: 'EMAIL',        subtext: ' ',        x:'center'    },    tooltip : {        trigger: 'item',        formatter: \"{a} <br>{b} : {c} ({d}%)\"    },    legend: {        orient: 'vertical',        right: 10,        top: 100,        bottom: 20,        data: ['111@qqq.com','724350512@qq.com','TEST_P','zzz@zzz.com','asdasdasdi','1075900121@qq.com','test','qqq.qqq@qq.com','kanchisme@gmail.com',]    },    series : [        {            name: 'value:protion',            type: 'pie',            radius : '55%',            center: ['50%', '60%'],            data:[                {value:4, name:'111@qqq.com'},{value:1, name:'724350512@qq.com'},{value:1, name:'TEST_P'},{value:1, name:'zzz@zzz.com'},{value:1, name:'asdasdasdi'},{value:1, name:'1075900121@qq.com'},{value:1, name:'test'},{value:1, name:'qqq.qqq@qq.com'},{value:3, name:'kanchisme@gmail.com'},            ],            itemStyle: {                emphasis: {                    shadowBlur: 10,                    shadowOffsetX: 0,                    shadowColor: 'rgba(0, 0, 0, 0.5)'                }            }        }    ]};<@C>option = {    series: [{        type: 'treemap',        data: [{ name: 'D34RQWEAFE',value: 1,},{ name: 'null',value: 1,},{ name: 'WER3Q524R23',value: 1,},{ name: 'SDASDF23QRWEFAWEF',value: 1,},{ name: '5FF53CD720E945128945DE815D6E2047',value: 1,},{ name: 'SDFERFEF',value: 1,},{ name: '12312EQWDASDCAFERYTH56H',value: 1,},{ name: 'EWR43634Y65EYGTRGD',value: 1,},]    }]};<@C>option = {        title : {        text: 'USERID',        subtext: ' ',        x:'center'    },    xAxis: {        type: 'category',        boundaryGap: false,        data: [1,2,3,4,5,6,7,8,9,10,11,12,13,14]    },    yAxis: {        type: 'value'    },    series: [{        data: [5.0,30.0,73.0,117.0,162.0,210.0,271.0,333.0,396.0,460.0,12805.0,135928.0,2.015187039E9,4.030238191E9],        type: 'line',        areaStyle: {}    }]};<@C>option = {    series: [{        type: 'treemap',        data: [{ name: 'AS',value: 1,},{ name: 'Thmpso',value: 1,},{ name: 'qqqzzzz',value: 1,},{ name: 'test5',value: 1,},{ name: 'test1',value: 1,},{ name: 'TEST_P',value: 1,},{ name: 'test3',value: 1,},{ name: 'test4',value: 1,},{ name: 'Kanch Zhang',value: 1,},{ name: 'long zhang',value: 1,},{ name: 'test',value: 1,},{ name: 'Scoot',value: 1,},{ name: 'test_user',value: 1,},{ name: 'zzz',value: 1,},]    }]};<@C><@R><@REPSP!>DatabaseInsights->T_DI_USER;");
        ReportManager report = new ReportManager();
        Debug.log("saveReport:",report.saveReport("test","REP00c2926da70244d8b811949326866b1a" ));
        
    }

}
