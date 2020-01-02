package 单例模式;

public class HolderTest
{
    /*
    * 声明类得时候，成员变量不声明实例变量，而是放在内部静态类中
    * */

    private HolderTest()
    {

    }

    private static class Holder{
        private static HolderTest  instance = new HolderTest();
    }

    //内部类 懒加载
    public static HolderTest getInstance()
    {
        return Holder.instance;
    }



}
