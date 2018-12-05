package vip.thtm;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: liangcan
 * @version: 1.0
 * @date: 2018/12/5 16:25
 * @describtion: SumTest
 */
public class SumTest {
    public int n = 6;
    public static int i = 6;

    public void test() {
        //m为int、Integer时，在lambda表达式中不能赋值，但是引用类型的可以改变其中的值
        AtomicReference<Integer> m = new AtomicReference<>(6);
        Sum sum = value -> {
            m.set(8);
            n = 8;
            i = 8;
            return value + m.get();
        };
    }


}
