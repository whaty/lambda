package vip.thtm;

import java.util.function.Function;

/**
 * @author: liangcan
 * @version: 1.0
 * @date: 2018/12/5 17:41
 * @describtion: 接受一个参数，返回单一的结果。默认的方法（andThen）可将多个函数串在一起，形成复合Funtion（有输入，有输出）结果
 */
public class FunctionTest {
    public static void main(String[] args) {
        Function<String, String> function = s -> {
            return s + "1";
        };
        Function<String, Integer> function1 = function.andThen(String::length);
        System.out.println(function1.apply("222"));
    }
}
