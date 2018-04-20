/*
 * The MIT License
 *
 * *** Copyright © ChengShiyi (Miss Zhang)
 * *** Code created on  三月 08 2018
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

/**
 *
 * @author Miss Zhang
 */
public class DatabaseAdaptor {
    public String[] numberType(){
        return new String[]{};
    }

    public String getColumnNamesByTable(String Table) {
        return "";
    }

    public String getColumnNames(String[] Tables) {
        return "";
    }

    public String getAllColumnsNameByName(String TableName) {
        return "";
    }

    public String getTableList() {
        return "";
    }

    public String getColumnSpecies(String Table) {
        return "";
    }

    public String getColumnSpeciesByName(String Column, String table) {
        return "";
    }

    public String generateSelect(String querys, String table, String condition) {
        return "";
    }

    public String findPrimaryKey(String table) {
        return "";
    }

    public String findForeignKey(String table) {
        return "";
    }
    
    public String findTablecolspe(String table){
        return "";
    }
    
    public String findDid(String dbname){
        return "";
    }
    
    public String findRefid(String columnname){
        return "";
    }
    
    public static DatabaseAdaptor detectDatabase(int databaseCode) {
        if (databaseCode == DatabaseConfig.DatabaseCode.DATABASE_MYSQL) {
            return new MySQLAdaptor();
        } else if (databaseCode == DatabaseConfig.DatabaseCode.DATABASE_ORACLE_12C) {
            return new OracleAdaptor();
        }
        return null;
    }

}
