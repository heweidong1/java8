package Lambda;

import org.junit.Test;

import java.util.*;

public class TestLambda
{
    //原来的匿名内部类

    public void test1()
    {
        //比较器
        Comparator<Integer> com  = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //Lambda 表达式
    @Test
    public void Test2()
    {
        Comparator<Integer> com =(x,y) -> {
            return Integer.compare(x,y);
        };
        TreeSet<Integer> ts= new TreeSet<>(com);
        ts.add(1);
        ts.add(5);
        ts.add(2);
        ts.add(4);
        System.out.println(ts.toString());
    }



    List<Employee> employees = Arrays.asList(
            //数组转集合
            new Employee("zhangsan",15,9999.99),
            new Employee("lisi",11,300.22),
            new Employee("wangwu",15,999.99),
            new Employee("zhaoliu",17,9999.99)
    );

    @Test
    public void test3()
    {
        List<Employee> employees = fileterEmployee(this.employees);
        System.out.println(employees.toString());
    }

    //获取当前公司中员工年龄大于35的员工信息

    public List<Employee> fileterEmployee(List<Employee>list)
    {
        List<Employee> employees = new ArrayList<>();
        for (Employee e : list) {
            if(e.getAge()>=15){
                employees.add(e);
            }
        }
        return employees;
    }



    //获取当前公司中员工工资大于5000的员工信息

    public List<Employee>filterEmployee2(List<Employee>list)
    {
        for (Employee e : list) {
            if(e.getSalary()>=6000){
                employees.add(e);
            }
        }
        return employees;
    }

    //优化方式1: 策略设计模式

    @Test
    public void test4()
    {
        List<Employee> employees = gilterEmployee(this.employees, new FilterEmpolyeeByAge());
        System.out.println(employees.toString());
    }



    public List<Employee> gilterEmployee(List<Employee> list,MyPredicate<Employee> mp)
    {
        List<Employee> ouput = new ArrayList<>();
        for (Employee e : list) {
            if(mp.test(e))
            {
                ouput.add(e);
            }
        }
        return ouput;
    }




    //优化二 ： 匿名内部类
    @Test
    public void test5()
    {
        List<Employee> employees = gilterEmployee(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() <= 5000;
            }
        });
        System.out.println(employees.toString());
    }


    //优化三：lambda表达式
    @Test
    public void test6()
    {
        MyPredicate<Employee> filterEmployeeByAge = (e)->e.getAge()<12;
        //按照年龄
        List<Employee> employees = gilterEmployee(this.employees, filterEmployeeByAge);
        System.out.println(employees);
        //按照工资
        List<Employee> employees1 = gilterEmployee(this.employees, (e) -> e.getSalary() < 5000);
        System.out.println(employees1);
    }

    //优化方式4：Stream Api

    @Test
    public void test7()
    {
        employees.stream()
                .filter((e)->e.getSalary()<5000)
                .forEach(System.out::println);

    }











}
