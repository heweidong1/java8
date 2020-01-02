package ForkJoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoin
{
    /*
    * ForkJoin 框架  用时289毫秒  数字越大  用这个越好  cpu利用率高
    *
    * jdk1.7  用起来超级繁琐
    * */

    @Test
    public void test()
    {
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        //计算0到10000000L得和
        ForkJoinTask<Long> task = new ForkJoinCalculate(0,1000000000L);
        Long sum = pool.invoke(task);
        Instant end = Instant.now();
        System.out.println(sum);
        System.out.println("耗费时间为"+ Duration.between(start,end).toMillis());
    }

    /*
    * 普通for循环  302 当计算值 小时 用这个好
    * */

    @Test
    public void test2()
    {
        Instant start = Instant.now();
       long sum =0;
       for(long i=0;i<=1000000000L;i++)
       {
           sum+=i;
       }
        Instant end = Instant.now();
        System.out.println(sum);
        System.out.println("耗费时间为"+ Duration.between(start,end).toMillis());
    }


    /*
    * jdk1.8并行流  相当简洁
    * 利用stream流
    * LongStream.rangeClosed(x,x)
    *
    *
    * */

    @Test
    public void test3()
    {
        //串行流[顺序流] 一个线程
        Instant start = Instant.now();
        long reduce = LongStream.rangeClosed(0, 1000000000L)
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
        Instant end = Instant.now();
        System.out.println("耗费时间为"+ Duration.between(start,end).toMillis());

        System.out.println("===================================");
        //并行流

        Instant start1 = Instant.now();
        long reduce1 = LongStream.rangeClosed(0, 1000000000L)
                .parallel()
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce1);
        Instant end1 = Instant.now();
        System.out.println("耗费时间为"+ Duration.between(start1,end1).toMillis());
    }


















}
