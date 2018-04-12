/*
 * The MIT License
 *
 * *** Copyright © ChengShiyi (Miss Zhang)
 * *** Code created on  四月 09 2018
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
package dbi.mgr.credential;

import dbi.utils.DBIResultSet;
import dbi.utils.GlobeVar;
import java.text.MessageFormat;

/**
 *
 * @author Miss Zhang
 */
public class CredentialBean {

    private String dbname = null;
    private String dbhost = null;
    private int dbaccount ;
    private String dbpassword = null;
    private String sessionID = null;
    private String certifications = null;
    private DBIResultSet dbresult;

    public CredentialBean() {
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }
    
    public void setDbresult(DBIResultSet dbresult){
        this.dbresult=dbresult;
    }
    
    public DBIResultSet getDbresult(){
        DBIResultSet re = GlobeVar.OBJ_MANAGER_CREDENTIAL.getCredential(sessionID);
        dbresult=re;
        return dbresult;
    }

    public String getCertifications() {
        String rowtemp = "<tr id=\"credtable_row_{0}\">"
                + "                        <th scope=\"row\" style=\"vertical-align: inherit;\">{1}</th>"
                + "                        <td style=\"vertical-align: inherit;padding: 0rem;\">"
                + "                            <input id=\"credtable_row_{0}_col_1\" class=\"console-table-input\" value=\"{2}\" type=\"text\" name=\"dbname\" disabled=\"disabled\">"
                + "                        </td>"
                + "                        <td style=\"vertical-align: inherit;padding: 0rem;\">"
                + "                            <input id=\"credtable_row_{0}_col_2\" class=\"console-table-input\"  value=\"{3}\" type=\"text\" name=\"dbhost\"  disabled=\"disabled\">"
                + "                        </td>"
                + "                        <td style=\"vertical-align: inherit;padding: 0rem;\">"
                + "                            <input id=\"credtable_row_{0}_col_3\" class=\"console-table-input\"  value=\"{4}\" type=\"text\" name=\"dbaccount\" disabled=\"disabled\">"
                + "                        </td>"
                + "                        <td style=\"vertical-align: inherit;padding: 0rem;\">"
                + "                            <input id=\"credtable_row_{0}_col_4\" class=\"console-table-input\"  value=\"{5}\" type=\"password\" name=\"dbpassword\" disabled=\"disabled\">"
                + "                        </td>"
                + "                        <td>"
                + "                            <button id=\"credtable_row_{0}_edit\" type=\"button\" class=\"btn btn-secondary\" onclick=\"edit({0})\">Edit</button>"
                + "                            <button id=\"credtable_row_{0}_save\" type=\"button\" class=\"btn btn-success\" onclick=\"save({0})\" style=\"display: none;\">Save</button>"
                + "                            <button type=\"button\" class=\"btn btn-danger\" onclick=\"del({0})\">Delete</button>"
                + "                        </td>"
                + "                   </tr>";
        
        
        for(int i=1;i<=dbresult.rowCount();i++){
            certifications += MessageFormat.format(rowtemp, dbresult.getRow(i).get(3),i,dbresult.getRow(i).get(0),dbresult.getRow(i).get(1),"",dbresult.getRow(i).get(2));
        }
        return certifications;
    }

    public void setDbhost(String dbhost) {
        this.dbhost = dbhost;
    }

    public void setDbaccount(int dbaccount) {
        this.dbaccount = dbaccount;
    }
    
    public int getDbaccount() {
        return dbresult.rowCount();
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }

}
