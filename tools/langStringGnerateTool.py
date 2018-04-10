#encoding=utf-8
# Database Insights Localization Generate Tool
# This tool used for generate localization language strings
# Run with Python 3
#
import xml.etree.ElementTree
import os
"""
 * The MIT License
 *
 * *** Copyright © Long Zhang(kanch)
 * *** Email: kanchisme@gmail.com
 * *** Code created on Apr 10 2018
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
 """
print("\n\n\tDatabase Insights Localization Generate Tool\n\t\t\thttp://akakanch.com\n\n\n")
 # target language Java class dir

dir_langPackage = "../DatabaseInsights/src/java/dbi/localization/"
dir_gen_langID = dir_langPackage + "langID.java"
dir_gen_langEN = dir_langPackage + "langEnglish.java"
dir_gen_langCN = dir_langPackage + "langChinese.java"
dir_langString = dir_langPackage + "lang.xml"
dir_langID = dir_langPackage + "langID.xml"

sym_beg = "//@DBI_LANGGEN_TOOL_BEGIN"
sym_end = "//@DBI_LANGGEN_TOOL_END"

genLangTemplate = "result.put(langID.%s, \"%s\");"
genIDTemplate = "public static int %s = %s;"

OP = "DILGT|"

def insertCode(source,target):
    """
    Insert target code into source file, based on <sym_beg> and <sym_end>
    """
    with open(source,"r+",encoding="utf-8") as f:
        origin = f.read()
        news = origin[:origin.find(sym_beg)+len(sym_beg)]
        news += "\r\n\t"
        news += target
        news += "\r\n\t"
        news += origin[origin.find(sym_end):]
        f.seek(0,0)
        f.truncate()
        f.write(news)
        return  news
    return "error"
# start generate

# read string xml
print(OP + "INFO|reading lange set from " + dir_langString)
langset = xml.etree.ElementTree.parse(dir_langString).getroot()
print(OP + "INFO|reading lange ID set from " + dir_langID)
langidset = xml.etree.ElementTree.parse(dir_langID).getroot()

# parse lang ID set
print(OP + "INFO|parsing... ")
lidset = langidset.findall('l')
lset = langset.findall('l')

# generate
print(OP + "INFO|generating... ")
langsEN = []
langsCN = []
langids = []

print(OP + "INFO|generating strings... ",end="\t")
for l in lset:
    code = genLangTemplate%( l.get("id"), l.get("value") )
    langswitch = l.get("lang")
    if langswitch == "cn":
        langsCN.append(code)
    elif langswitch == "en":
        langsEN.append(code)
print("-done")

print(OP + "INFO|generating ids... ",end="\t")
langids = [ genIDTemplate%( l.get("name"), l.get("id") ) for l in lidset ]
print("-done")

# join as strings
langCNstr = "\n\t".join(langsCN)
langENstr = "\n\t".join(langsEN)
langidstr = "\n\t".join(langids)

# insert into source code
print(OP + "INFO|generating Java code...")
insertCode(dir_gen_langID,langidstr)
insertCode(dir_gen_langEN,langENstr)
insertCode(dir_gen_langCN,langCNstr)

print(OP + "INFO|Code has been generated successfully!\n\n")




