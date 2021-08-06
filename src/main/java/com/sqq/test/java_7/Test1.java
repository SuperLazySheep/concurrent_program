package com.sqq.test.java_7;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HtmlUtil;
import javafx.concurrent.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.core.Local;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sqq
 * @Date 2021/3/15
 */
public class Test1 {
    public static void main(String[] args) {


//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String format = formatter.format(LocalDate.now());
//        LocalDate parse = LocalDate.parse(format, formatter);
//
//        System.out.println(parse);

//        LocalDateTime localDateTime = LocalDateTime.now();
//        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        System.out.println(date);
//        Integer integer = Integer.valueOf(200);
//        Integer integer1 = Integer.valueOf(200);
//        System.out.println(integer == integer1);
//        String i= "nihao";
//        String i2 = "nihao";
//        String i3 = "ni";
//        String i4 = "hao";
//        String i5 = i3 + i4;
//        System.out.println(i == i2);
//        System.out.println(i == i5);
//        System.out.println(i.equals(i5));
//        Task<Object> objectTask = new Task<Object>() {
//            @Override
//            protected Object call() throws Exception {
//                return null;
//            }
//        };
//        LocalDate now = LocalDate.now();
//        System.out.println(now.toString()   );
//        String s = "nsan.doc";
//        if(s.endsWith(".doc")){
//            System.out.println("111");
//        }else{
//            System.out.println("222");
//        }
        String s = "<html>↵<head>↵<META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">↵<style type=\"text/css\">.b1{white-space-collapsing:preserve;}↵.b2{margin: 1.0in 1.25in 1.0in 1.25in;}↵.s1{color:#393939;}↵.p1{text-align:center;hyphenate:auto;font-family:宋体;font-size:14pt;}↵.p2{text-align:justify;hyphenate:auto;font-family:宋体;font-size:14pt;}↵</style>↵<meta content=\"Administrator\" name=\"author\">↵</head>↵<body class=\"b1 b2\">↵<p class=\"p1\">↵<span class=\"s1\">《人民法院报》头版</span>↵</p>↵<p class=\"p2\">↵<span>司法考试分数克里夫附件里是尽量减少快乐的感觉临时搭建过来的两个阶段是两个极端观看了大概看了看来大概看了大家赶快来打开两个的克里夫国际快递发给考虑到看过了打开浪费过多考虑各色了几个老师的两个老师过来是两个独立感到乐观的两个六十多公里了解爱国了阿拉贡据了解了了解了啦啦了。</span>↵</p>↵</body>↵</html>";
        String s1 = HtmlUtil.cleanHtmlTag(s.replace("\\n", "").trim());
        String replace = s1.replace("↵", "");
        String matcherRegex = getMatcherRegex(replace, "[^}]+$");
        System.out.println(matcherRegex);
    }

    public static String wipeRegex(String str, String strPattern){
        if(!StringUtils.isNotBlank(str)){
            return "";
        }
        Pattern pattern = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("");
    }

    /**
     * 返回正则匹配结果
     */
    public static String getMatcherRegex(String str, String strPattern) {
        Matcher m = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE).matcher(str);
        String result = null;
        while (m.find()) {
            result = m.group(0);
            /* 取最后一个匹配项，百度网页解析第一个匹配项不是时间 */
        }
        return result;
    }
}
