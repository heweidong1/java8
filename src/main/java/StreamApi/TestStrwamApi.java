package StreamApi;

import Lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一，Stream 的三个操作步骤
 * 1.创建Stream
 * 2.中间操作
 * 3.终止操作【终端操作】
 */
public class TestStrwamApi
{
    //创建Stream

    @Test
    public void test1()
    {
        //1.可以通过Collection 系类集合提供的stream()或 parallelStream()
        List<String> lsit = new ArrayList<>();
        //获取流
        Stream<String> stream = lsit.stream();

        //2.通过Arrays 中的静态方法stream()获取数组流
        Employee[]emps = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(emps);

        //3.通过Stream 类中静态方法of()
        Stream<String> aa = Stream.of("aa", "bb", "cc");

        //4.创建无限流、
        //迭代
        Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
        iterate.limit(10);
        iterate.forEach(System.out::println);


        //生成
        Stream.generate(()->Math.random()).forEach(System.out::println);
    }

























}
