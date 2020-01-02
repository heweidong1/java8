package Lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 1,Lambda2 表达式的基础语法：java8中引入了一个新的操作符"->" 该操作符称为箭头操作符或者是Lambda操作符
 *                          箭头操作符将Lambda表达式差分为2部分
 * 左侧：Lambda 表达式的参数列表
 * 右侧：Lambda 表达式所需要执行的功能，即Lambda体
 *
 *  语法格式一：无参数 无返回值
 *         ()-> System.out.printIn("Hello Lambda");
 *  语法格式二：一个参数，并无返回值
 *         (x)->System.out.println(x)
 *  语法格式三：若只有一个参数，小括号可以省略不写
 *         x->System.out.println(x)
 *
 *  语法格式四：有两个以上的参数，有返回值，并且Lambda体中有多条语句
 *         Comparator<Integer> com = (x,y)->{
 *           System.out.println("函数接口");
 *           return Integer.compare(x,y);
 *         };
 *  语法格式5：若Lambda 体中只有一条语句，return和{}都可以省略不写
 *         Comparator<Integer> com = (x,y)->Integer.compare(x,y);
 *
 *  语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，
 *          数据类型，即“类型推断”
 *           (Integer x,Integer y)->Integer.compare(x,y);
 *  左右遇一括号省
 *  左侧推断类型省
 *
 *  Lambda 表达式需要“函数式接口”的支持
 *  函数式接口：接口 中只有一个抽象方法的接口，称为函数式接口。
 *  可以使用注解@FunctionalInterface修饰，可以检查是否是函数式接口
 */
public class TestLambda2
{
    @Test
    public void test1()
    {
        Runnable r =new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        };

        System.out.println("-------------------------------");

        Runnable r1 = ()->System.out.println("Hello Lambda");
        r1.run();
    }

    @Test
    public void test2()
    {
        Consumer<String> con = (x)->System.out.println(x);
        con.accept("123456");

        Consumer<String> con2 = x->System.out.println(x);
        con.accept("123456");
    }

    public void test3()
    {
        Comparator<Integer> com = (x,y)->{
          System.out.println("函数接口");
          return Integer.compare(x,y);
        };
    }

    public void test4()
    {
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);

    }


    //对一个数进行运算

    @Test
    public void test5()
    {
        MyFun myFun=(x)->x*x;
        Integer operation = operation(100, myFun);
        System.out.println(operation);
    }
    public Integer operation(Integer num,MyFun mf)
    {
        return mf.getvalue(num);
    }





}
