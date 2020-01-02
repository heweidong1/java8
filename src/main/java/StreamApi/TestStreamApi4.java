package StreamApi;

import Lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStreamApi4
{
    /*
    * 给定一个数字队列，如何返回一个由每个数得平方构成得列表呢？
    * 给定[1，2，3，4，5]，返回[1,4,9,16,25]
    * */

    @Test
    public void test()
    {
        Integer[] nums = new Integer[]{1,2,3,4,5};
        Stream<Integer> stream = Arrays.stream(nums);
        stream.map((e)->e*e)
                .forEach(System.out::println);
    }


    List<Employee> employees = Arrays.asList(
            //数组转集合
            new Employee("zhangsan",15,9999.99),
            new Employee("lisi",11,300.22),
            new Employee("wangwu",16,999.99),
            new Employee("zhaoliu",17,9999.99)
    );



    /*
    * 怎么用map 和 reduce 方法数一数流中有多少Emplyee呢
    * */

    @Test
    public void test2()
    {
        //方法一
        Integer reduce = employees.stream()
                .map((e) -> 1)
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        //方法二
        long count = employees.stream()
                .count();
        System.out.println(count);
    }





















}
