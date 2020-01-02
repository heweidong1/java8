package optional;

import Lambda.Employee;
import org.junit.Test;

import java.util.Optional;

public class TestOptional
{
    /*
    * Optional:容器类得常用方法
    * Optional.of(T t):创建一个Optional得实例
    * Optional.empty():创建一个空得Optional实例
    * Optional.ofNullable(T t):若t不是null，创建Optional实例，否则创建空实例
    * isPresent():判断是否包含值
    * orElse(T t):如果调用对象包含值，返回该值，否则返回t
    * orElseGet(Supplier s):如果调用对象包含值，返回该值，否则返回s获取得值
    * map(Function f)：如果有值对其处理，并返回处理后得Optional，否则返回Optional.empty()
    * flatMap(Function mapper):与map类似，要求返回值必须是Optional
    * */

    @Test
    public void test1()
    {
        //Optional.of(T t):创建一个Optional得实例
        Optional<Employee> employee = Optional.of(new Employee());
        System.out.println(employee.get());
    }

    @Test
    public void test2()
    {
        //构建空得optional
        Optional<Employee> empty = Optional.empty();
    }

    @Test
    public void test3()
    {
        //Optional.ofNullable(T t):若t不能null，创建Optional实例，否则创建空实例
        //Optional<Employee> employee = Optional.ofNullable(new Employee());

        Optional<Employee> employee = Optional.ofNullable(null);
        //判断是否有值
        if(employee.isPresent())
        {
            System.out.println(employee.get());
        }
    }































}
