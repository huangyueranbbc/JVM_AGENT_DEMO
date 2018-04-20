/*******************************************************************************
 * @date 2018-04-20 上午 9:56
 * @author: <a href=mailto:huangyr@bonree.com>黄跃然</a>
 * @Description: 监控类 比较两种单例类型性能
 ******************************************************************************/
public class T7 {

    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        T5 t5 = T5.getInstance();
        long end1 = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();
        T6 t6 = T6.getInstance();
        long end2 = System.currentTimeMillis();

        long time1 = end1 - start1;
        long time2 = end2 - start2;

        System.out.println(time1 + ":" + time2);
        System.out.println("t5 size of " + MySizeOf.sizeOf(t5));
        System.out.println("t6 size of " + MySizeOf.sizeOf(t6));

        System.out.println("t5 full size of " + MySizeOf.fullSizeOf(t5));
        System.out.println("t6 full size of " + MySizeOf.fullSizeOf(t6));

    }

}

class T5 {

    private volatile static T5 instance = null;

    private T5() {
    }

    public static T5 getInstance() {

        if (instance == null) {
            synchronized (T5.class) {
                if (instance == null) {
                    instance = new T5();
                }
            }
        }
        return instance;
    }
}

class T6 {

    private T6() {
    }

    private static class SingleHolder {
        private static T6 t6 = new T6();
    }

    public static T6 getInstance() {
        return SingleHolder.t6;
    }
}

