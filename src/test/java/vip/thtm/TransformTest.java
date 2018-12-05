package vip.thtm;

/**
 * @author: liangcan
 * @version: 1.0
 * @date: 2018/12/5 16:13
 * @describtion: TransformTest
 */
public class TransformTest {
    public static void main(String[] args) {
        //传统方式使用接口
        Transform<String, Integer> transform1 = new Transform<String, Integer>() {
            @Override
            public Integer transform(String s) {
                return Integer.parseInt(s);
            }
        };

        Integer i1 = transform1.transform("1");
        System.out.println("i1 = " + i1);

        //Lambda方式使用接口,就是这么简单粗暴，没脾气
        Transform<String, Integer> transform2 = (s) -> Integer.valueOf(s);
        Integer i2 = transform2.transform("2");
        System.out.println("i2 = " + i2);
    }
}
