/*
 * The MIT License
 *
 * **** Copyright © ChengShiyi.
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

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
       
        if(dbcode == DatabaseConfig.DatabaseCode.DATABASE_MYSQL){
            DBAdaptor  = new MySQLAdaptor();
        }else if(dbcode == DatabaseConfig.DatabaseCode.DATABASE_ORACLE_12C){
            DBAdaptor = new OracleAdaptor();
        }
    }

    public Boolean Connect() {
        try {
            // 创建数据库连接
            Class.forName(databaseConfig.getDriver());
            conn = DriverManager.getConnection(databaseConfig.getHost(), databaseConfig.getUsername(), databaseConfig.getPassword());
        } catch (Exception e) {
            System.err.println("错误：无法从数据库中读取数据，可能是网络问题或者驱动问题。");
            System.err.println(e.getMessage());
        }
        return true;
    }

    private Boolean checkConnection() {
        Boolean check = true;
        try {
            if (conn.isClosed()) {
                check = false;
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return check;
    }

    public void Disconnect() {
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    /* get all table names of a given database */
    public ArrayList<String> getTables() {
        ArrayList<String> tables = new ArrayList<>();
        String table_name = "";
        try {
            Statement st = conn.createStatement();
            // 执行数据库语句
            ResultSet rs = st.executeQuery(DBAdaptor.getTableList());
            // 遍历获取结果

            while (rs.next()) {
                table_name = rs.getString("table_name");
                tables.add(table_name);
            }

            st.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return tables;
    }

    /* get column names of a given table */
    public ArrayList<String> getColumns(String table) {
        ArrayList<String> columns = new ArrayList<>();
        String column_name = "";
        try {
            Statement st = conn.createStatement();
            // 执行数据库语句
            ResultSet rs = st.executeQuery(DBAdaptor.getColumnNamesByTable(table));
            // 遍历获取结果

            while (rs.next()) {
                column_name = rs.getString("column_name");
                columns.add(column_name);
            }

            st.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return columns;
    }

    /* get column name and species of a given table */
    public ArrayList<String> getAllColumnSpecies(String table) {
        ArrayList<String> species = new ArrayList<>();
        
        String columnName="column_name";
        String date_type="data_type";
        String isPrimary="";
        String isForeignKey="";
        String foreignKeyREF="";
        
        try {
            Statement st = conn.createStatement();
            // 执行数据库语句
            ResultSet rs = st.executeQuery(DBAdaptor.getColumnSpecies(table));
            // 遍历获取结果

            while (rs.next()) {
                columnName=rs.getString("");
                date_type=rs.getString("");
                isPrimary=rs.getString("");
                isForeignKey=rs.getString("");
                foreignKeyREF=rs.getString("");
            }

            st.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return species;
    }

    /* get column names of a given table */
    public ArrayList<String> getColumnSpecies(String column) {
        ArrayList<String> species = new ArrayList<>();
        
        
        
        return species;
    }

    public static void main(String[] args) {

        //DatabaseConfig a = new DatabaseConfig(DatabaseConfig.DatabaseCode.DATABASE_ORACLE_12C, "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@//111.231.225.37:1521/DatabaseInsights", "di", "DI2017");
        DatabaseConfig a=new DatabaseConfig(DatabaseConfig.DatabaseCode.DATABASE_MYSQL,"org.gjt.mm.mysql.Driver","jdbc:mysql://115.159.197.66:3306/IncidentsReport","ir", "2yvaVjSNVYHwWwcR");
        DatabaseHelper test = new DatabaseHelper(a);
        System.out.println(test.Connect());
        System.out.println(test.getTables());
        System.out.println(test.checkConnection());
        test.Disconnect();
        System.out.println(test.checkConnection());
    }
}
