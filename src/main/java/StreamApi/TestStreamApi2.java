package StreamApi;

import Lambda.Employee;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

public class TestStreamApi2
{
    //中间操作
    /*
    * 筛选与切片
    * filter --接收Lambda ，从流中排除某些元素
    * limit --断截流，使其元素不超过给定数量
    * skip（n） --跳过元素，返回一个扔掉了前n个原色的流，若流中元素不足n个，则返回一个空流，与limit（n）互补
    * distinct --筛选，通过流所生成元素的hashcode和equals去重复元素
    * */



    List<Employee> employees = Arrays.asList(
            //数组转集合
            new Employee("zhangsan",15,9999.99),
            new Employee("lisi",11,300.22),
            new Employee("wangwu",16,999.99),
            new Employee("zhaoliu",17,9999.99)
    );

    //内部迭代：迭代操作由于Stream API 完成

    @Test
    public void test1()
    {
        /*
        * 多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作
        * 不会执行任何的处理，而在终止操作时一次性全部处理，称为“惰性求值”
        * */
        //创建&中间操作  filter 断言，找出满足条件的数据 利用Lambda表达式
        Stream<Employee> s = employees.stream()
                             .filter((e) -> e.getAge() > 15);
        //终止操作
        s.forEach(System.out::println);
    }



    //外部迭代

    public void Test2()
    {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }



    @Test
    public void Test3()
    {
        /*
        * limit、
        *  分页
        * */
        employees.stream()
                 .filter((e)->e.getSalary()>1000)
                 .limit(2)
                 .forEach(System.out::println);
    }

    @Test
    public void test4()
    {
        //skip  跳过几个
        employees.stream().filter((e)->e.getSalary()>5000)
                  .skip(2)
                  .forEach(System.out::println);
    }


    /*
    * 映射
    * map--接收Lambda，将元素转换为其他形式或提取信息，接收一个函数作为参数，该函数会应用到每个元素上
    * ，并将其映射成一个新的元素
    * flatmap--接收一个函数作为参数，将流中的每个值都换成看另一个流，然会把所有的流连接成一个流
    * */

    @Test
    public void test5()
    {
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream()
                .map((str)->str.toUpperCase())
                .forEach(System.out::println);
        //map流  利用Lambda，将要操作的数据进行操作提取  到map流中
        //Lambda 表达式中 的返回值为Object map方法利用这个表达式 将数据转为流 返回  到map中
        employees.stream()
                .map((e)->e.getName())
                .forEach(System.out::println);

        Stream<Stream<Character>> streamStream = list.stream()
                .map(TestStreamApi2::filterCharacter);
        streamStream.forEach((sm)->{
            sm.forEach(System.out::println);
        });
        System.out.println("==========================================");
        Stream<Character> characterStream = list.stream()
                .flatMap(TestStreamApi2::filterCharacter);
        characterStream.forEach(System.out::println);
    }


    public static Stream<Character> filterCharacter(String str)
    {
        List<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    /*
    * 排序
    * sorted（） --自然排序（Comparable）
    * sorted(Comparator com) -- 定制排序(Comparator)
    * */

    @Test
    public void Test()
    {
        List<String> list = Arrays.asList("ddd","ccc","bbb","aaa","eee");
        //自然排序
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("-------------------------------");
        //定制排序
        employees.stream()
                .sorted((e1,e2)->{
                    if(e1.getAge()==e2.getAge())
                    {
                        return e1.getName().compareTo(e2.getName());
                    }else
                    {
                        return Integer.compare(e1.getAge(),e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }


























}
