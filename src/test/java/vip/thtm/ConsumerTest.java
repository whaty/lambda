package vip.thtm;

import java.util.function.Consumer;

/**
 * @author: liangcan
 * @version: 1.0
 * @date: 2018/12/5 17:36
 * @describtion: 对输入的参数进行操作。有输入没输出
 */
public class ConsumerTest {
    public static int m = 10;

    public static void main(String[] args) {
        Consumer<Integer> consumer = p -> {
            System.out.println("p = " + p);
            p = p + 10;
            System.out.println("p = " + p);

        };
        consumer.accept(m);
    }
}
