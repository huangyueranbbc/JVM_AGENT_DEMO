import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/*******************************************************************************
 * @date 2018-04-20 上午 11:39
 * @author: <a href=mailto:huangyr@bonree.com>黄跃然</a>
 * @Description:
 ******************************************************************************/
public class TestJOL {

    private long a1,a2,a3;

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(TestJOL.class).toPrintable());
        System.out.println(ClassLayout.parseClass(TestJOL.A.class).toPrintable());
    }

    public static class A {
        long f;
    }


}
