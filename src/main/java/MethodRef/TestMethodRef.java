package MethodRef;

import Lambda.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用：若Lambda 体中的内容有方法已经实现了，那么可以使用方法引用
 *          【可以理解为方法引用是Lambda 表达式的另外一种表现形式】
 *
 *  主要有三种语法格式
 *  对象::实例方法名
 *  类::静态方法名
 *  类::实例方法名
 *
 *  注意：
 *    - Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽像方法的函数列表和返回值类型保持一致！
 */
public class TestMethodRef
{
    //对象::实例方法名

    @Test
    public void test1()
    {
        Consumer<String> con = (x)-> System.out.println(x);

        PrintStream ps = System.out;
        Consumer<String> con1=ps::println;

        con1.accept("as");
    }

    @Test
    public void test2()
    {
        Employee employee = new Employee();
        Supplier<String> sup = ()-> employee.getName();
        //方法引用
        Supplier<String> sup2 = employee::getName;
        System.out.println(sup2.get());
    }


    //类::静态方法名

    @Test
    public void test3()
    {
        List<Employee> employees = Arrays.asList(
                //数组转集合
                new Employee("zhangsan",15,9999.99),
                new Employee("lisi",11,300.22),
                new Employee("wangwu",16,999.99),
                new Employee("zhaoliu",17,9999.99)
        );

        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        //方法引用  引用的方法 的返回值 参数 必须和这个类的一样
        Comparator<Integer> com1 = Integer::compare;

        Comparator<Employee> com2 = (x,y)->{
            if(x.getAge()==y.getAge())
            {
                return x.getName().compareTo(y.getName());
            }else
            {
                return Integer.compare(x.getAge(),y.getAge());
            }
        };
        TreeSet<Employee> ser= new TreeSet<>(com2);
        ser.add(employees.get(0));
        ser.add(employees.get(1));
        ser.add(employees.get(2));
        ser.add(employees.get(3));
        System.out.println(ser);
    }
















}
