package vip.thtm;

/**
 * @author: liangcan
 * @version: 1.0
 * @date: 2018/12/5 16:40
 * @describtion: MethodReferenceTest
 */
public class MethodReferenceTest {
    public static void main(String[] args) {
        //传统方式
        Transform<String, Integer> transform = new Transform<String, Integer>() {
            @Override
            public Integer transform(String s) {
                return MethodReferenceTest.strToInteger(s);
            }
        };
        System.out.println(transform.transform("1"));

        //Lambda静态引用方式
        Transform<String, Integer> transform1 = MethodReferenceTest::strToInteger;
        System.out.println(transform1.transform("2"));

        //Lambda实例引用方式
        Obj obj = new Obj();
        Transform<String, Integer> transform2 = obj::strToInteger;
        System.out.println(transform2.transform("3"));

        //Lambda构造函数引用方式,
        Factory factory = Obj::new;
        Obj obj1 = factory.create();
        Transform<String, Integer> transform3 = obj1::strToInteger;
        System.out.println(transform3.transform("4"));


    }

    static Integer strToInteger(String s) {
        return Integer.parseInt(s);
    }
}
