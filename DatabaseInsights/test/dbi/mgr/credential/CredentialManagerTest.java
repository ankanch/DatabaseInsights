/*
 * The MIT License
 *
 * *** Copyright © ChengShiyi (Miss Zhang)
 * *** Code created on  四月 22 2018
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miss Zhang
 */
public class CredentialManagerTest {
    
    public CredentialManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addCredential method, of class CredentialManager.
     */
    @Test
    public void testAddCredential() {
        System.out.println("addCredential");
        String dbhost = "";
        String dbname = "";
        String dbuser = "";
        String dbpawd = "";
        String userid = "";
        String dbtype = "";
        CredentialManager instance = new CredentialManager();
        Boolean expResult = null;
        Boolean result = instance.addCredential(dbhost, dbname, dbuser, dbpawd, userid, dbtype);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCredential method, of class CredentialManager.
     */
    @Test
    public void testDeleteCredential() {
        System.out.println("deleteCredential");
        String crdid = "";
        CredentialManager instance = new CredentialManager();
        Boolean expResult = null;
        Boolean result = instance.deleteCredential(crdid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alterCredential method, of class CredentialManager.
     */
    @Test
    public void testAlterCredential() {
        System.out.println("alterCredential");
        String crdid = "";
        String dbname = "";
        String dbhost = "";
        String password = "";
        CredentialManager instance = new CredentialManager();
        Boolean expResult = null;
        Boolean result = instance.alterCredential(crdid, dbname, dbhost, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCredential method, of class CredentialManager.
     */
    @Test
    public void testGetCredential() {
        System.out.println("getCredential");
        String sid = "";
        CredentialManager instance = new CredentialManager();
        DBIResultSet expResult = null;
        DBIResultSet result = instance.getCredential(sid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validiateCreditial method, of class CredentialManager.
     */
    @Test
    public void testValidiateCreditial() {
        System.out.println("validiateCreditial");
        String dbscode = "";
        String dbhost = "";
        String dbname = "";
        String dbuser = "";
        String dbpwd = "";
        int dbcode = 0;
        CredentialManager instance = new CredentialManager();
        Boolean expResult = null;
        Boolean result = instance.validiateCreditial(dbscode, dbhost, dbname, dbuser, dbpwd, dbcode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class CredentialManager.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        CredentialManager.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
