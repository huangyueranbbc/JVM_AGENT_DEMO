import java.util.ArrayList;

public class TestSize {
    public static void main(String []args) {
        System.out.println("一个Interger对象大小为："+MySizeOf.sizeOf(new Integer(1)));
        System.out.println("一个String对象大小为："+MySizeOf.sizeOf(new String("a")));
        System.out.println("一个String对象大小（关闭指针压缩）为："+MySizeOf.fullSizeOf(new String("a")));
        System.out.println("一个char对象大小为："+MySizeOf.sizeOf(new char[1]));
        System.out.println("一个ArrayList对象大小为："+MySizeOf.sizeOf(new ArrayList<>()));
        System.out.println("一个Object对象大小为："+MySizeOf.sizeOf(new Object()));
        System.out.println("一个Long对象大小为："+MySizeOf.sizeOf(new Long(10000000000L)));
    }
}