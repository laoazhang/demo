package com.java.escape;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 数值计算和时间计算
 */
@SuppressWarnings("all")
public class NumberAndTime {

    /**
     * scale 需要与小数匹配
     */
    private static void scaleProblem(){
        BigDecimal decimal = new BigDecimal("1.235");
        // BigDecimal result = decimal.setScale(12);
        // System.out.println(result);
        BigDecimal result = decimal.setScale(2,BigDecimal.ROUND_HALF_UP);

        System.out.println(result);
    }

    /**
     * BigDecimal 做除法时出现除不尽的情况
     */
    private static void divideProblem(){

        // System.out.println(new BigDecimal(30).divide(new BigDecimal(7)));
        System.out.println(new BigDecimal(30).divide(new BigDecimal(7),2,BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 精度问题导致比较结果和预期的不一致
     */
    private static void equalProblem() {
        BigDecimal bd1 = new BigDecimal("0");
        BigDecimal bd2 = new BigDecimal("0.0");

        System.out.println(bd1.equals(bd2));
        System.out.println(bd1.compareTo(bd2)==0);

    }

    /**
     * SimpleDateFormat 可以解析大于/等于它定义的时间精度
     * @throws Exception
     */
    private static void formatPrecision() throws Exception {

        SimpleDateFormat sdf  = new SimpleDateFormat("yyy-MM-dd");
        String time_x = "2020-03-01 00:00:00";
        String time = "2020-03";

        System.out.println(sdf.parse(time_x));
        System.out.println(sdf.parse(time));
    }

    /**
     * SimpleDateFormat 存在线程安全问题
     */
    private static void threadSafety() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(1000));
        while (true) {
            threadPoolExecutor.execute(()->{
                String dateString = "2020-03-01 00:00:00";
                try{
                    Date parseDate = sdf.parse(dateString);
                    String dateString2 = sdf.format(parseDate);
                    System.out.println(dateString.equals(dateString2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws Exception {

        // scaleProblem();
        // divideProblem();
        // equalProblem();
        // formatPrecision();
        threadSafety();
    }
}
