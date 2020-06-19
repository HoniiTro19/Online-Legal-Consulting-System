package com.huidong.legalsys.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 身份证校验
 * source: https://www.cnblogs.com/pxblog/p/12038278.html
 */
public class ValidateIdnoUtil {

    final static Map<Integer, String> zoneNum = new HashMap<Integer, String>();

    static {
        zoneNum.put(11, "北京");
        zoneNum.put(12, "天津");
        zoneNum.put(13, "河北");
        zoneNum.put(14, "山西");
        zoneNum.put(15, "内蒙古");
        zoneNum.put(21, "辽宁");
        zoneNum.put(22, "吉林");
        zoneNum.put(23, "黑龙江");
        zoneNum.put(31, "上海");
        zoneNum.put(32, "江苏");
        zoneNum.put(33, "浙江");
        zoneNum.put(34, "安徽");
        zoneNum.put(35, "福建");
        zoneNum.put(36, "江西");
        zoneNum.put(37, "山东");
        zoneNum.put(41, "河南");
        zoneNum.put(42, "湖北");
        zoneNum.put(43, "湖南");
        zoneNum.put(44, "广东");
        zoneNum.put(45, "广西");
        zoneNum.put(46, "海南");
        zoneNum.put(50, "重庆");
        zoneNum.put(51, "四川");
        zoneNum.put(52, "贵州");
        zoneNum.put(53, "云南");
        zoneNum.put(54, "西藏");
        zoneNum.put(61, "陕西");
        zoneNum.put(62, "甘肃");
        zoneNum.put(63, "青海");
        zoneNum.put(64, "宁夏");
        zoneNum.put(65, "新疆");
        zoneNum.put(71, "台湾");
        zoneNum.put(81, "香港");
        zoneNum.put(82, "澳门");
        zoneNum.put(91, "外国");
    }

    final static int[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    final static int[] POWER_LIST = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
            5, 8, 4, 2};

    /**
     * @Description 身份证验证
     * @param certNo 号码内容
     * @return 是否有效 null和"" 都是false
     */
    public static boolean isIdno(String certNo) {
        if (certNo == null || (certNo.length() != 15 && certNo.length() != 18))
            return false;
        final char[] cs = certNo.toUpperCase().toCharArray();
        //校验位数
        int power = 0;
        for (int i = 0; i < cs.length; i++) {
            if (i == cs.length - 1 && cs[i] == 'X')
                break;//最后一位可以 是X或x
            if (cs[i] < '0' || cs[i] > '9')
                return false;
            if (i < cs.length - 1) {
                power += (cs[i] - '0') * POWER_LIST[i];
            }
        }

        //校验区位码
        if (!zoneNum.containsKey(Integer.valueOf(certNo.substring(0, 2)))) {
            return false;
        }

        //校验年份
        String year = null;
        year = certNo.length() == 15 ? getIdcardCalendar(certNo) : certNo.substring(6, 10);


        final int iyear = Integer.parseInt(year);
        if (iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR))
            return false;//1900年的PASS，超过今年的PASS

        //校验月份
        String month = certNo.length() == 15 ? certNo.substring(8, 10) : certNo.substring(10, 12);
        final int imonth = Integer.parseInt(month);
        if (imonth < 1 || imonth > 12) {
            return false;
        }

        //校验天数
        String day = certNo.length() == 15 ? certNo.substring(10, 12) : certNo.substring(12, 14);
        final int iday = Integer.parseInt(day);
        if (iday < 1 || iday > 31)
            return false;

        //校验"校验码"
        if (certNo.length() == 15)
            return true;
        return cs[cs.length - 1] == PARITYBIT[power % 11];
    }

    private static String getIdcardCalendar(String certNo) {
        // 获取出生年月日
        String birthday = certNo.substring(6, 12);
        SimpleDateFormat ft = new SimpleDateFormat("yyMMdd");
        Date birthdate = null;
        try {
            birthdate = ft.parse(birthday);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        Calendar cday = Calendar.getInstance();
        cday.setTime(birthdate);
        String year = String.valueOf(cday.get(Calendar.YEAR));
        return year;
    }
}