package vip.thtm;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author: liangcan
 * @version: 1.0
 * @date: 2018/12/5 17:17
 * @describtion: Predicate接口：输入一个参数，返回一个boolean值，内置了许多用于逻辑判断的默认方法
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> predicate = s -> s.length() > 4;
        boolean test = predicate.test("test");
        System.out.println("test = " + test);
        boolean test1 = predicate.negate().test("test");
        System.out.println("test1 = " + test1);

        Predicate<Object> predicate1 = Objects::nonNull;
        Object object = null;
        boolean test2 = predicate1.test(object);
        System.out.println("test2 = " + test2);

    }
}
