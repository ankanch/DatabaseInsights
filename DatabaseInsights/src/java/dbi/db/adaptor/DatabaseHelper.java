/*
 * The MIT License
 *
 * **** Copyright © Long Zhang(kanch) , ChengShiyi.
 * **** Code created on Mar 01 2018
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
package dbi.db.adaptor;

import dbi.utils.DBIResultSet;
import dbi.utils.Debug;
import dbi.utils.GlobeVar;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author ChengShiyi
 */
public class DatabaseHelper {

    private DatabaseAdaptor DBAdaptor;
    private DatabaseConfig databaseConfig;
    private Connection conn;

    public DatabaseHelper(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
        int dbcode = this.databaseConfig.getDatabase();

        if (dbcode == DatabaseConfig.DatabaseCode.DATABASE_MYSQL) {
            DBAdaptor = new MySQLAdaptor();
        } else if (dbcode == DatabaseConfig.DatabaseCode.DATABASE_ORACLE_12C) {
            DBAdaptor = new OracleAdaptor();
        }
    }

    public Boolean Connect() {
        if (!checkConnection()) {
            try {
                // 创建数据库连接
                Class.forName(databaseConfig.getDriver());
                conn = DriverManager.getConnection(databaseConfig.getHost(), databaseConfig.getUsername(), databaseConfig.getPassword());
            } catch (Exception e) {
                Debug.error(e);
                return false;
            }
        }
        return true;
    }
    
    private Boolean checkConnection() {
        try {
            if (conn.isClosed()) {
                return false;
            }
        } catch (Exception e) {
            //Debug.error(e);
            return false;
        }
        return true;
    }

    public void Disconnect() {
        try {
            conn.close();
        } catch (Exception e) {
            Debug.error(e);
        }
    }

    /* get all table names of a given database */
    public DBIResultSet getTables() {
        DBIResultSet tables = new DBIResultSet();
        String table_name = "";
        try {
            Statement st = conn.createStatement();
            // 执行数据库语句
            ResultSet rs = st.executeQuery(DBAdaptor.getTableList());
            // 遍历获取结果

            while (rs.next()) {
                table_name = rs.getString("table_name");
                tables.addToRow(table_name);
            }
            tables.finishRow();
            st.close();
        } catch (Exception e) {
            Debug.error(e);
        }
        return tables;
    }

    /* get column names of a given table */
    public DBIResultSet getColumns(String table) {
        DBIResultSet columns = new DBIResultSet();
        String column_name = "";
        try {
            Statement st = conn.createStatement();
            // 执行数据库语句
            ResultSet rs = st.executeQuery(DBAdaptor.getColumnNamesByTable(table));
            // 遍历获取结果

            while (rs.next()) {
                column_name = rs.getString("column_name");
                columns.addToRow(column_name);
            }
            columns.finishRow();
            st.close();
        } catch (Exception e) {
            Debug.error(e);
        }
        return columns;
    }

    /* get column name and species of a given table */
    public DBIResultSet getAllColumnSpecies(String table, int userid, int did) {
        DBIResultSet getresult = runSQLForResult(DBAdaptor.findTablecolspe(table));
        DBIResultSet primary = runSQLForResult(DBAdaptor.findPrimaryKey(table));
        DBIResultSet foreign = runSQLForResult(DBAdaptor.findForeignKey(table));
        DBIResultSet tid=new DBIResultSet();
        DBIResultSet species=new DBIResultSet();
        tid=runSQLForResult("select tid from T_DATABASE_TABLE where TNAME='"+getresult.getRow(1).get(0)+"'");
        int isPrimary = 0;
        int isRef = 0;
        for (int i = 1; i <= getresult.rowCount(); i++) {
            if (!primary.getRow(1).isEmpty()) {
                if (getresult.getRow(i).get(1).equals(primary.getRow(1).get(0))) {
                    isPrimary = 1;
                } else {
                    isPrimary = 0;
                }
            }

            if (!foreign.getRow(1).isEmpty()) {
                for (int j = 0; j < foreign.rowCount(); j++) {
                    if (getresult.getRow(i).get(1).equals(foreign.getRow(j + 1).get(0))) {
                        isRef = 1;
                    }
                }
            }
            species.addToRow(tid.getRow(tid.rowCount()).get(0));
            species.addToRow(did);
            species.addToRow(userid);
            species.addToRow(getresult.getRow(i).get(1));
            species.addToRow(getresult.getRow(i).get(2));
            species.addToRow(isPrimary);
            species.addToRow(isRef);
            species.finishRow();
            runSQL("insert into T_DATABASE_COLUMN(TID,DID,USERID,COLUMNNAME,DATATYPE,ISPRIMARY,ISFOREIGNKEY) \n"
                    + "values(" + tid.getRow(tid.rowCount()).get(0) + "," + did 
                    + "," + userid + ",'" + getresult.getRow(i).get(1) + "','" 
                    + getresult.getRow(i).get(2) + "'," + isPrimary + "," + isRef + ")");
            isRef = 0;
        }
        return species;
    }



    /* get column names of a given table */
    public ArrayList<String> getColumnSpecies(String column) {
        ArrayList<String> species = new ArrayList<>();

        return species;
    }

    public DBIResultSet runSelect(String querys, String table, String condition) {
        DBIResultSet sqlResult = null;
        try {
            Statement st = conn.createStatement();
            String sql = DBAdaptor.generateSelect(querys, table, condition);
            // 执行数据库语句
            Debug.log("SQL=", sql);
            sqlResult = new DBIResultSet(st.executeQuery(sql));
            st.close();
        } catch (SQLException e) {
            Debug.error(e);
        }

        return sqlResult;
    }
    

    public DBIResultSet runJoinSelect(String querys, String[] tables, String joinconditions) {
        String table = tables[0];
        for (int i = 1; i <= tables.length - 1; i++) {
            table = table+","+tables[i];
            System.out.println(table);
        }
        String sql = "select " + querys + " from " + table + " where " + joinconditions;

        DBIResultSet sqlResult = new DBIResultSet();
        try {
            Statement st = conn.createStatement();
            // 执行数据库语句
            Debug.log("SQL=", sql);
            ResultSet rs = st.executeQuery(sql);
            // 遍历获取结果
            while (rs.next()) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    sqlResult.addToRow(rs.getString(i + 1));
                }
                sqlResult.finishRow();
            }
            st.close();
        } catch (Exception e) {
            Debug.error(e);
        }

        return sqlResult;
    }

    public boolean runUpdate(String table, HashMap<String, Object> keyValues, String condition) {
        // make up SQL UPDATE statment
        String SQL = "UPDATE {0} SET {1} WHERE {2}";
        String kvstr = "";
        for (String key : keyValues.keySet()) {
            Object val = keyValues.get(key);
            if (val instanceof String) {
                kvstr += key + "='" + (String) val + "',";
            } else if (val instanceof Integer) {
                kvstr += key + "=" + String.valueOf((Integer) val) + ",";
            } else if (val instanceof Boolean) {
                kvstr += key + "=" + String.valueOf((Boolean) val) + ",";
            }
        }
        kvstr = kvstr.substring(0, kvstr.length() - 1);
        if (condition.length() < 1) {
            // no condition provided
            condition = " true ";
        }
        SQL = MessageFormat.format(SQL, table, kvstr, condition);
        Debug.log("SQL=", SQL);
        //Execute update statment
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(SQL);
            st.close();
        } catch (Exception e) {
            Debug.error(e);
            return false;
        }
        return true;
    }

    public boolean runSQL(String sql) {
        try {
            Debug.log(sql);
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (Exception e) {
            Debug.error(e);
            return false;
        }
        return true;
    }

    public DBIResultSet runSQLForResult(String sql) {
        DBIResultSet sqlResult = new DBIResultSet();
        int p = 0;
        try {
            Statement st = conn.createStatement();
            // 执行数据库语句
            Debug.log("SQL=", sql);

            ResultSet rs = st.executeQuery(sql);
            // 遍历获取结果
            while (rs.next()) {
                p = 1;
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    sqlResult.addToRow(rs.getString(i + 1));
                }
                sqlResult.finishRow();
            }
            st.close();
        } catch (Exception e) {
            Debug.error(e);
        }
        if (p == 0) {
            sqlResult.finishRow();
        }
        return sqlResult;
    }


    /**
     * INPUT Example: (myfunction(?,?,?),param_1,param_2,param_3) RETURNS: int
     */
    public int executeOracleFunction(String funcDef, String... params) throws SQLException {

        String query = "{? = call @funcDef@}".replace("@funcDef@", funcDef);
        CallableStatement statement = conn.prepareCall(query);
        statement.registerOutParameter(1, Types.INTEGER);
        for (int i = 2; i <= params.length + 1; i++) {
            statement.setString(i, params[i - 2]);
        }
        statement.execute();
        return statement.getInt(1);
    }
    
    /**
    * 检查给定用户表的每一列是否为数字或非数字。如果是数字，为1，如果不是，为0<br/>
    * 返回值：DBIResultSet，每一行包含列名和是否为数字列
    */
    public DBIResultSet getIsNumberColumns(int uid,String table){
        DBIResultSet tables = new DBIResultSet();
        String numberType[]=DBAdaptor.numberType();
        try {
            DBIResultSet res=runSQLForResult("select distinct columnname,datatype,colid from T_DATABASE_COLUMN where userid='"+uid+"' and TID in\n" +
                                            "(select tid from T_DATABASE_TABLE where tname='"+table+"') and isprimary=0\n" +
                                            "order by colid");
            for(int i=0;i<res.rowCount();i++){
                tables.addToRow(res.getRow(i+1).get(0));
                if(Arrays.asList(numberType).contains((String)res.getRow(i+1).get(1))){
                    tables.addToRow("1");
                }else{
                    tables.addToRow("0");
                }
                tables.finishRow();
            }
        } catch (Exception e) {
            Debug.error(e);
        }
        return tables;
    }
    
    public DatabaseHelper getUserdbhelper(int uid){
        DBIResultSet result=runSQLForResult("select distinct a.userid,dbtype,host,username,b.PASSWORD from "
                + "T_DATABASE_INFO a, T_DATABASE_CERTIFICATION b where a.userid=b.userid"
                + " and a.userid="+uid);
        String dbtype=(String)result.getRow(1).get(1);
        String host=(String)result.getRow(1).get(2);
        String username=(String)result.getRow(1).get(3);
        String password=(String)result.getRow(1).get(4);
        DatabaseConfig config=new DatabaseConfig(Integer.valueOf(dbtype),GlobeVar.CONFIG_DATABASE_DRIVER,host,username,password);
        return new DatabaseHelper(config);
    }

    public static void main(String[] args) {

        DatabaseConfig a = new DatabaseConfig(DatabaseConfig.DatabaseCode.DATABASE_ORACLE_12C, "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@//111.231.225.37:1521/DatabaseInsights", "di", "DI2017");
        //DatabaseConfig a = new DatabaseConfig(DatabaseConfig.DatabaseCode.DATABASE_MYSQL, "org.gjt.mm.mysql.Driver", "jdbc:mysql://115.159.197.66:3306/IncidentsReport", "ir", "2yvaVjSNVYHwWwcR");
        DatabaseHelper test = new DatabaseHelper(a);
        DBIResultSet columns = new DBIResultSet();
        test.Connect();
        try {
            String table[]={"T_DATABASE_INFO","T_DATABASE_CERTIFICATION"};
            DBIResultSet result=test.runJoinSelect("*", table, "T_DATABASE_INFO.DID=T_DATABASE_CERTIFICATION.DID");
            for(int i=0;i<result.rowCount();i++){
                System.out.println(result.getRow(i+1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
