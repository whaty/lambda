package vip.thtm;

import java.util.function.Supplier;

/**
 * @author: liangcan
 * @version: 1.0
 * @date: 2018/12/5 17:46
 * @describtion: 返回一个给定类型的结果。不需要输入参数，无输入有输出
 */
public class SupplierTest {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "无输入有输出";
        String s = supplier.get();
        System.out.println(s);
    }
}
