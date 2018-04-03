/*
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code created on Mar 06 2018
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
package dbi.utils;

import dbi.db.adaptor.DatabaseConfig;

/**
 *
 * @author kanch
 */
public class GlobeVar {
    public static String SERVLET_IDENTIFIER_ERROR = "!@ERR!";
    public static String SERVLET_IDENTIFIER_SUCCESS = "!@SUCC!";
    public static String CONFIG_DATABASE_HOST ="jdbc:oracle:thin:@//cd.kcs.akakanch.com:1521/DatabaseInsights";
    public static String CONFIG_DATABASE_USER="di";
    public static String CONFIG_DATABASE_PASSWORD="DI2017";
    public static String CONFIG_DATABASE_DRIVER=DatabaseConfig.DatabaseDriver.ORACLE_12C;
    
    public static final DatabaseConfig VAR_DATABASE_CONFIG = new DatabaseConfig(DatabaseConfig.DatabaseCode.DATABASE_ORACLE_12C,
                    GlobeVar.CONFIG_DATABASE_DRIVER, GlobeVar.CONFIG_DATABASE_HOST,GlobeVar.CONFIG_DATABASE_USER, GlobeVar.CONFIG_DATABASE_PASSWORD);
    
    
    //public static URLMAP_CONSOLE
}


