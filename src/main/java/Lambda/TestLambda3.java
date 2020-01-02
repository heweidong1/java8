package Lambda;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8 内置的四大核心函数式接口
 * Consumer<T> : 消费型接口
 *           void accept(T t);
 * Supplier<T> : 供给型接口
 *           T get()
 * Function<T,R> :函数型接口
 *           R apply(T t);
 * Predicate<T>:断言型接口
 *           boolean test(T t)
 */
public class TestLambda3
{
    //Consumer<T> 消费型接口：

    @Test
    public void test()
    {
        happy(10000,(d)-> System.out.println("消费了"+d));
    }

    public void happy(double money, Consumer<Double> con)
    {
        con.accept(money);
    }

    //Supplier<T> 供给型
    //产生指定个数的正数，并放入集合中

    @Test
    public void test2()
    {
        List<Integer> numList = getNumList(10, () -> (int) Math.random() * 100);
        System.out.println(numList);
    }

    public List<Integer> getNumList(int num, Supplier<Integer> sup)
    {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<num;i++)
        {
            Integer n=sup.get();
            list.add(n);
        }
        return list;
    }


    //Function<T,R> 函数型接口：

    @Test
    public void test3()
    {
        String abcd = strHandler("abcd", (str) -> str.toUpperCase());
        System.out.println(abcd);
    }

    public String strHandler(String str, Function<String,String> fun)
    {
        return fun.apply(str);
    }


    //Predicate<T> 断言型接口

    @Test
    public void test4()
    {
        List<String>list= Arrays.asList("hello","kgc","Lambda","www","ok");
        List<String> strings = filterStr(list, (s) -> s.length() > 3);
        System.out.println(strings);
    }



    //将满足条件的字符串，放入集合中去

    public List<String> filterStr(List<String> list, Predicate<String> pre)
    {
        List<String> strlist = new ArrayList<>();
        for (String s : list) {
            if(pre.test(s))
            {
                strlist.add(s);
            }
        }

        return strlist;
    }


    public static void main(String[] args) {
        Comparator<Integer> comparator =new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.compare((Integer) o1,(Integer) o2);
            }
        };

        Comparator<Integer> comparator2=(x,y)->{
          return Integer.compare(x,y);
        };

        TreeSet<Integer> treeSet = new TreeSet<>((x,y)->Integer.compare(x,y));


    }













}
