package Date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class TestLocalDateTime
{
    //1.LocalDate  LocalTime LocalDateTime

    @Test
    public void test1()
    {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime of = LocalDateTime.of(2015, 10, 19, 13, 22, 33);
        System.out.println(of);

        //+两年
        System.out.println(ldt.plusYears(2));

        //-两个月
        System.out.println(ldt.minusMonths(2));

        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());
    }



    //Instant  :时间戳{已Unix元年：1970年1月1日 00：00：00到某个时间之间得毫秒值}

    @Test
    public void test2()
    {
        //默认获取UTC 时区
        Instant ins1 = Instant.now();
        System.out.println(ins1);

        //设置偏移量  也就是时区
        OffsetDateTime offsetDateTime = ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        //转为毫秒值
        System.out.println(ins1.toEpochMilli());

        //在元年之上进行运算  +60秒
        Instant instant = Instant.ofEpochSecond(60);
        System.out.println(instant);
    }


    //Duration: 计算两个时间之间得间隔


    @Test
    public void test3() throws InterruptedException {
        //两个时间之间得间隔
        Instant now = Instant.now();
        Thread.sleep(1000);
        Instant now1 = Instant.now();
        Duration between = Duration.between(now, now1);
        System.out.println(between.toMillis());
        System.out.println("====================================");
        LocalTime now2 = LocalTime.now();
        System.out.println(now2);
        Thread.sleep(1000);
        LocalTime now3 = LocalTime.now();
        System.out.println(now3);

        System.out.println(Duration.between(now2,now3).toMillis());

    }


    //Period :计算两个日期之间得间隔

    @Test
    public void test4()
    {
       LocalDate ld1 = LocalDate.of(2015,1,1);
       LocalDate ld2 = LocalDate.now();
        System.out.println(Period.between(ld1,ld2).getYears());
        System.out.println(Period.between(ld1,ld2).getMonths());
        System.out.println(Period.between(ld1,ld2).getDays());
    }

    //TemporalAdjuster:时间矫正器

    @Test
    public void test5()
    {
        LocalDateTime ld1 = LocalDateTime.now();

        //指定  日期中 某个 字段得数字  此实例 是将 月中得日 改变为10号
        LocalDateTime localDateTime = ld1.withDayOfMonth(10);
        System.out.println(localDateTime);

        LocalDateTime with = ld1.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(with);
    }

    //DateTimeFormatter :格式化时间/日期

    @Test
    public void test6()
    {
        //2019-12-21
        //DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;

        //2019-12-21T11:06:11.449
        DateTimeFormatter dtf2 = DateTimeFormatter.ISO_DATE_TIME;

        LocalDateTime now = LocalDateTime.now();

        String format = now.format(dtf2);
        System.out.println(format);

        System.out.println("============自己规定得日期格式================");
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时:mm分:ss秒");
        String format1 = now.format(dtf3);
        System.out.println(format1);

        //解析字符串
        LocalDateTime parse = now.parse(format1, dtf3);
        System.out.println(parse);
    }


































}
