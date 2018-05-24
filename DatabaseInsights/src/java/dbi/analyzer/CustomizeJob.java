/*
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code created on May 24 2018
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
package dbi.analyzer;

/**
 *
 * @author kanch
 */
public class CustomizeJob {

    // pooling function code
    public static int PF_NONE = 10001;
    public static int PF_AVERANGE = 10002;
    public static int PF_COUNT = 10003;
    public static int PF_COUNT_NOREPEAT = 10004;
    public static int PF_MAX = 10005;
    public static int PF_MIN = 10006;
    public static int PF_SUM = 10007;

    // type code
    public static int TYPE_NUMBER = 20001;
    public static int TYPE_TEXT = 20002;
    public static int TYPE_BOOLEAN = 20003;

    public String column_name = "";
    public String column_nickname = "";
    public int type = TYPE_NUMBER;
    public int pool_func = PF_NONE;
    public String comment = "";

    public CustomizeJob(String lastf, String newf, String type, String poolf, String comment) {
        switch (type) {
            case "type_text":
                this.type = TYPE_TEXT;
                break;
            case "type_number":
                this.type = TYPE_NUMBER;
                break;
            case "type_boolean":
                this.type = TYPE_BOOLEAN;
                break;
        }
        switch (poolf) {
            case "pf_none":
                this.pool_func = PF_NONE;
                break;
            case "pf_avg":
                this.type = PF_AVERANGE;
                break;
            case "pf_count":
                this.type = PF_COUNT;
                break;
            case "pf_count_norepeat":
                this.type = PF_COUNT_NOREPEAT;
                break;
            case "pf_max":
                this.type = PF_MAX;
                break;
            case "pf_min":
                this.type = PF_MIN;
                break;
            case "pf_sum":
                this.type = PF_SUM;
                break;
        }
        column_name = lastf;
        column_nickname = newf;
        this.comment = comment;
    }

}
