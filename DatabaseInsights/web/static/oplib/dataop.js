/* 
 * The MIT License
 *
 * **** Copyright © Long Zhang(kanch).
 * **** Email: kanchisme@gmail.com
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
var error_identifier = "!@ERR!"

function SubmitForm(url, formid, error_msg, success_msg) {
    // function from https://stackoverflow.com/questions/25983603/how-to-submit-html-form-without-redirection
    $.ajax({
        url: url,
        type: 'post',
        data: $('#' + formid).serialize(),
        success: function (data) {
            if (data.indexOf(error_identifier) > -1) {
                showMsg(error_msg);
            } else {
                showMsg(success_msg);
            }
        }
    });
}
function SubmitFormF(url, formid, error_msg, success_func) {
    // function from https://stackoverflow.com/questions/25983603/how-to-submit-html-form-without-redirection
    $.ajax({
        url: url,
        type: 'post',
        data: $('#' + formid).serialize(),
        success: function (data) {
            if (data.indexOf(error_identifier) > -1) {
                showMsg(error_msg);
            } else {
                success_func();
            }
        }
    });
}

function SubmitFormF(url, formid, error_func, success_func) {
    // function from https://stackoverflow.com/questions/25983603/how-to-submit-html-form-without-redirection
    $.ajax({
        url: url,
        type: 'post',
        data: $('#' + formid).serialize(),
        success: function (data) {
            if (data.indexOf(error_identifier) > -1) {
                error_func();
            } else {
                success_func();
            }
        }
    });
}

function SubmitFormF(url, keyvalue, error_func, success_func) {
    // function from https://stackoverflow.com/questions/25983603/how-to-submit-html-form-without-redirection
    $.ajax({
        url: url,
        type: 'post',
        data: keyvalue,
        success: function (data) {
            if (data.indexOf(error_identifier) > -1) {
                error_func();
            } else {
                success_func();
            }
        }
    });
}