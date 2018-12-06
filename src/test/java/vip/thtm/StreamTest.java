package vip.thtm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author: liangcan
 * @version: 1.0
 * @date: 2018/12/6 10:05
 * @describtion: StreamTest
 */
public class StreamTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(UUID.randomUUID().toString());
        }
        streamFilterTest(list);
    }


    /**
     * 结合Predicate接口，Filter对流对象中的所有元素进行过滤,该操作是一个中间操作，这意味着你可以在操作返回结果的基础上进行其他操作
     *
     * @param list
     */
    public static void streamFilterTest(List<String> list) {
        list.stream().filter(s -> s.contains("a")).forEach(System.out::println);

        Predicate<String> predicate = s -> s.endsWith("d");
        list.stream().filter(predicate).forEach(System.out::println);

        Predicate<String> predicate1 = s -> s.startsWith("a");
        //串行
        list.stream().filter(predicate).filter(predicate1).forEach(System.out::println);
//        并行
        list.parallelStream().filter(predicate).filter(predicate1).forEach(System.out::println);
    }

    /**
     * 结合Comparator,该操作返回一个排序过后的流的视图，原始流的顺序不会改变。通过Comparator来指定排序规则，默认是自然排序
     *
     * @param list
     */
    public static void streamSortedTest(List<String> list) {
        list.stream().sorted().forEach(System.out::println);
//        串行
        list.stream().sorted(String::compareTo).forEach(System.out::println);
//        并行
        list.parallelStream().sorted(String::compareTo).forEach(System.out::println);
    }

    /**
     * 结合Function接口，该操作能将流对象中的每一个元素映射为另一个元素，实现元素类型的转换。
     *
     * @param list
     */
    private static void streamMapTest(List<String> list) {
        list.stream().map(String::toUpperCase).sorted((s, t1) -> t1.compareTo(s)).forEach(System.out::println);
        System.out.println("- - - - - - ");
        //自定义映射规则
        Function<String, String> function = s -> {
            return s + ".map3";
        };
        list.stream().map(function).forEach(System.out::println);
    }

    /**
     * 用来判断某个predicate是否和流对象相匹配，最终返回boolean类型的结果
     *
     * @param list
     */
    private static void streamMatchTest(List<String> list) {
        //流对象中只要有一个元素匹配就返回true
        boolean anyStartWithA = list.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println("集合中是否有以'a'来头：" + anyStartWithA);
        //流对象中每一个元素都匹配才返回true
        boolean allStartWithA = list.stream().allMatch(s -> s.startsWith("a"));
        System.out.println("集合中每一个都是以'a'开头：" + allStartWithA);
        //流对象中没有匹配时返回true
        boolean noneStartWithA = list.stream().noneMatch(s -> s.startsWith("c"));
        System.out.println("集合中没有以'c'开头：" + noneStartWithA);
    }

    /**
     * 在对经过变换后，将变换的stream元素收集，比如将这些元素存在集合中，可以使用stream提供的collect方法
     *
     * @param list
     */
    private static void streamCollectTest(List<String> list) {
//        串行
        List<String> listNew = list.stream().filter(s -> s.startsWith("b")).sorted().collect(Collectors.toList());
        System.out.println(listNew);
//        并行
        List<String> list2 = list.stream().filter(s -> s.startsWith("b")).sorted().collect(Collectors.toList());
        System.out.println(list2);
    }

    /**
     * 允许我们用自己的方式计算元素或者将一个stream中元素以某种规律关联
     *
     * @param list
     */
    private static void streamReduceTest(List<String> list) {
        Optional<String> optional = list.stream().sorted().reduce((s, s2) -> {
            System.out.println(s + "-" + s2);
            return s + "-" + s2;
        });
    }

    /**
     * 用来统计流中元素的总数
     *
     * @param list
     */
    private static void streamCountTest(List<String> list) {
        long count = list.stream().filter(s -> s.startsWith("b")).count();
        System.out.println("以'b'开头的数量：" + count);
    }


}
