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
public class OracleAdaptor extends DataBaseAdaptor{
    public String getColumnNamesByTable(String TableName){
        return "select column_name\n" +
                "from all_tab_columns \n" +
                "where table_name = '" + TableName + "'";
    }
    
    public String getColumnNames(String[] Tables){
        return "select column_name from all_tab_columns where table_name in ('"+  String.join("','", Tables) +"')";
    }
    
    public String getTableList(){
        return "select table_name from user_tables";
    }
    public String getColumnSpecies(String Table){
        return "SELECT * FROM user_tab_columns WHERE TABLE_NAME='"+Table+"'";
    }
    
    public String getColumnSpeciesByName(String Column,String table){
        return "SELECT * \n" +
                "FROM all_tab_columns\n" +
                " WHERE column_name  =  '"+ Column +"' and table_name = '"+table+"'";
    }
    
    public String generateSelect(String querys,String table,String condition){
        return "select "+ querys+" from "+table+" where "+condition;
    }
}
