package com.zfdang.zsmth_android.helpers;

import com.zfdang.SMTHApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtils. Created by zfdang on 2016-3-28.
 */
public class StringUtils {
    private static SimpleDateFormat dateformat = null;

    public static String getFormattedString(Date date){
        if(dateformat == null)
            dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(date != null)
            return dateformat.format(date);
        return "";
    }

    public static String subStringBetween(String line, String str1, String str2) {
        if(line == null || line.length() == 0){
            return "";
        }

        int idx1 = line.indexOf(str1);
        int idx2 = line.lastIndexOf(str2);
        if(idx1 != -1 && idx2 != -1){
            return line.substring(idx1 + str1.length(), idx2);
        }{
            return "";
        }
    }


    // /nForum/board/ADAgent_TG ==> ADAgent_TG
    // /nForum/article/RealEstate/5017593 ==> 5017593
    public static String getLastStringSegment(String content) {
        if(content == null || content.length() == 0){
            return "";
        }
        String[] segments = content.split("/");
        if(segments.length > 0) {
            return segments[segments.length - 1];
        }
        return "";
    }

    public static String lookupIPLocation(String content) {
        Pattern myipPattern = Pattern.compile("FROM[: ]*(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.)[\\d\\*]+");
        Matcher myipMatcher = myipPattern.matcher(content);
        while (myipMatcher.find()) {
            String ipl = myipMatcher.group(1);
            if (ipl.length() > 5) {
                ipl = "$1\\*(" + SMTHApplication.geoDB.getLocation(ipl + "1") + ")";
            } else {
                ipl = "$1\\*";
            }
            content = myipMatcher.replaceAll(ipl);
        }
        return content;
    }
}
