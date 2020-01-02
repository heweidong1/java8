package Date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class TestSimpleDateFormat
{
    //jdk1.7

    @Test
    public  void test() throws ExecutionException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd");

        //创建线程执行得方法  会有线程安全问题
        Callable<Date> call = ()->sdf.parse("20161218");

        //创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            results.add(pool.submit(call));
        }


        for (Future<Date> result : results) {
            System.out.println(result.get());
        }

        pool.shutdown();
    }

    //jdk1.8

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = ()->LocalDate.parse("20161218",dtf);
        //创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> results = new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            results.add(pool.submit(task));
        }


        for (Future<LocalDate> result : results) {
            System.out.println(result.get());
        }

        pool.shutdown();


    }














}
