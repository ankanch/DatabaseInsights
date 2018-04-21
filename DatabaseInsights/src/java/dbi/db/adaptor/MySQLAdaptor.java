/*
 * The MIT License
 *
 * *** Copyright © ChengShiyi (Miss Zhang)
 * *** Code created on  三月 04 2018
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
public class MySQLAdaptor extends DatabaseAdaptor{
    public String[] numberType(){
        return new String[]{};
    }
    
    public String getColumnNamesByTable(String Table){
        return "select column_name from information_schema.columns  where table_name='"+ Table +"';";
    }
    public String getColumnNames(String[] Tables){
        return "select column_name from information_schema.columns  where table_name in ('"+  String.join("','", Tables) +"');";
    }
     
    public String getAllColumnsNameByName(String TableName){
        return "select COLUMN_NAME\n" +
                "from information_schema.columns \n" +
                "where table_name = '" + TableName + "'";
    }    
   
    
    public String getTableList(){
        return "SELECT TABLE_NAME\n" +
                "FROM information_schema.tables";
    }
    public String getColumnSpecies(String Table){
        return "SHOW COLUMNS FROM "+Table;
    }
    
    public String getColumnSpeciesByName(String Column,String table){
        return "SELECT * \n" +
                "FROM information_schema.columns\n" +
                " WHERE column_name  =  '"+ Column +"' and table_name = '"+table+"'";
    }
    
    public String generateSelect(String querys,String table,String condition){
        String sql="select "+ querys+" from "+table+" where "+condition;
        if(condition == ""){
            sql="select "+ querys+" from "+table;
        }
        return sql;
    }
    
    public String findPrimaryKey(String table){
        return "SELECT COLUMN_NAME\n" +
        "FROM INFORMATION_SCHEMA.COLUMNS\n" +
        "WHERE table_name =  '"+table+"'\n" +
        "AND COLUMN_KEY =  'PRI'";
    }
    
    public String findForeignKey(String table){
        return "select REFERENCED_COLUMN_NAME refColumn ,REFERENCED_TABLE_NAME refTable from INFORMATION_SCHEMA.KEY_COLUMN_USAGE  where TABLE_NAME='"+table+"'";
    }
    
    public String findTablecolspe(String table){
        return "select  c.TABLE_NAME,c.COLUMN_NAME,t.DATA_TYPE\n" +
                "from user_tab_columns  t,user_col_comments  c\n" +
                " where t.table_name = c.table_name"
                + " and t.column_name = c.column_name and t.table_name = '"+table+"'";
    }
    
    public String findDid(String dbname){
        String str="select did from T_DATABASE_INFO where name='"+dbname+"'";
        return str;
    }
    
    public String findRefid(String columnname){
        String str="select colid from T_DATABASE_COLUMN "
                + "where columnname='"+columnname+"' and isPrimary=1";
        return "";
    }
}
