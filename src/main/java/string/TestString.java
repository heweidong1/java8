package string;

public class TestString
{
    public static void main(String[] args) {

        int n = 50000;

        long sbStartTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("." + i);
        }
        System.out.println("StringBuilder 使用的时间"
                + (System.currentTimeMillis() - sbStartTime) / 1000.0 + "s");

        long stringStartTime = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < n; i++) {
            str += "." + i;
        }
        System.out.println("String 使用的时间"
                + (System.currentTimeMillis() - stringStartTime) / 1000.0 + "s");

        long sbfStartTime = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sbf.append("." + i);
        }
        System.out.println("StringBuffer 使用的时间"
                + (System.currentTimeMillis() - sbfStartTime) / 1000.0 + "s");

    }




}
