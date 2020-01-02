package StreamApi;

import Lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 种终止操作
 */
public class TestStreamApi3
{

    List<Employee> employees = Arrays.asList(
            //数组转集合
            new Employee("zhangsan",15,9999.99, Employee.Status.FREE),
            new Employee("lisi",11,300.22, Employee.Status.BUSY),
            new Employee("wangwu",16,999.99, Employee.Status.VOCATION),
            new Employee("zhaoliu",17,9999.99, Employee.Status.FREE)
    );

    /*
    * 查找与匹配
    * allMatch --检查是否匹配所有元素
    * anyMatch --检查是否至少匹配一个元素
    * noneMatch --检查是否没有匹配所有元素
    * findFirst --返回一个元素
    * findAny --返回当前流中得任意元素
    * count --返回流中元素得总个数
    * max --返回流中最大值
    * min --返回流中最小值
    * */

    @Test
    public void test()
    {
        // allMatch 检查是否匹配所有元素  此实例中是 判断该集合中得状态是否 都是BUSY
        boolean b = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);

        //anyMatch 是否至少匹配一个元素
        boolean b1 = employees.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);
        //noneMatch --检查是否没有匹配所有元素  当集合中都没有这个BUSY  返回true
        boolean b2 = employees.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);
        //findFirst --返回一个元素
        //Optional 有可能为null 得值放入到Optional
        Optional<Employee> first = employees.stream()
                .sorted((e1, e2) -> {
                    if (e1.getSalary() == e1.getSalary()) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return Double.compare(e1.getSalary(), e2.getSalary());
                    }
                })
                .findFirst();
        //如果当中得数据为null 可以放一个默认得值
        //first.orElse()
        System.out.println(first.get());

        //findAny --返回当前流中得任意元素
        Optional<Employee> any = employees.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.BUSY))
                .findAny();
        System.out.println(any.get());

        //count --返回流中元素得总个数
        long count = employees.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.BUSY))
                .count();
        System.out.println(count);

        //max  返回流中最大值
        Optional<Employee> max = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());

        //获取最小得工资是多少
        Optional<Double> min = employees.stream()
                .map((e) -> {
                    return e.getSalary();
                })
                .min(Double::compare);
        System.out.println(min.get());

    }

    /*
    * 归约
    * reduce(T identity,BinaryOperator/ reduce(BinaryOperator)--可以将流中元素反复结合起来，得到一个值)
    * */

    @Test
    public void test2()
    {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //计算和  先将0作为x 再从集合中拿到一个数据 进行计算
        Integer reduce = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
        System.out.println("================================");
        Double reduce1 = employees.stream()
                .map(Employee::getSalary)
                .reduce(0D, (x, y) -> x + y);
        System.out.println(reduce1);
    }
    /*
    * 收集
    * collect --将流转换为其他形式。收集一个Collector接口得实现，用于给Stream中元素做汇总得方法
    * */

    @Test
    public void test3()
    {
        //把集合中人的名字 放入到一个集合中
        List<String> collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

        HashSet<String> collect1 = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        collect1.forEach(System.out::println);

        //工资平均值
        Double collect2 = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect2);

        //总和
        Double collect3 = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collect3);

        //最大值 得员工
        Optional<Employee> collect4 = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(collect4.get());

        //最小值得工资
        Optional<Double> collect5 = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(collect5.get());

    }

    //分组

    @Test
    public void test4()
    {
        Map<Employee.Status, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);
    }

    //多级分组

    @Test
    public void test5()
    {
        Map<Employee.Status, Map<String, List<Employee>>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (((Employee) e).getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(collect);
    }


    //分片 分区 满足条件得一个区  不满足条件得一个区

    @Test
    public void test6()
    {
        Map<Boolean, List<Employee>> collect = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        System.out.println(collect);
        /*
        * 满足条件得
        * 不满足条件得
        *
        * */
    }

    @Test
    public void test7()
    {
        DoubleSummaryStatistics collect = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect.getSum());
        System.out.println(collect.getMax());
        System.out.println(collect.getCount());
        System.out.println(collect.getMin());
        System.out.println(collect.getAverage());
        /*
        *   21300.19
            9999.99
            4
            300.22
            5325.0475
        * */
    }

    @Test
    public void test8()
    {
        String collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(collect);
        /*
        * zhangsan,lisi,wangwu,zhaoliu
        * */
        String s = "asdasdasd";
        char[] chars = s.toCharArray();
        String s2 =new String(chars);
        System.out.println(s);
    }


























}
