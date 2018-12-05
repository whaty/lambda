# lambda
java8 lambda 表达式详细教程



# **简介**  <br>

学习lambda表达式就要先知道函数式接口是什么？
> 函数式接口（Functional Interfaces）：如果一个接口定义个唯一一个抽象方法，那么这个接口就成为函数式接口。同时，引入了一个新的注解：@FunctionalInterface。可以把他它放在一个接口前，表示这个接口是一个函数式接口。这个注解是非必须的，只要接口只包含一个方法的接口，虚拟机会自动判断，不过最好在接口上使用注解 @FunctionalInterface 进行声明。在接口中添加了 @FunctionalInterface 的接口，只允许有一个抽象方法，否则编译器也会报错。

示例：

	 /**
	  * 函数式接口
	  */
    @FunctionalInterface
    interface Sum{
        int add(int value);
    }

> Lambda表达式：可以让你的代码更加的简洁。ambda无法单独出现，需要一个函数式接口来盛放，可以说lambda表达式方法体是函数式接口的实现，lambda实例化函数式接口，可以将函数作为方法参数，或者将代码作为数据对待。

主要优点：
>     1.代码变得更加简洁紧凑 
>     2.可读性强， 
>     3.并行操作大集合变得很方便，可以充分发挥多核cpu的优势，更加便于多核处理器编写代码等，


# **语法** <br>
Lambda语法 <br>
(parameters)->expression 或者 (parameters)->{statements;} 
>     Lambda表达式由三部分组成： 
>     1.parameters:类似方法中的形参列表，这里的参数是函数式接口里的参数。这里的参数类型可以明确的声明也可不声明而由JVM隐含的推断，当只有一个推断类型时可以省略掉圆括号。 
>     2.->可以理解为“被用于”的意思 
>     3.方法体：可以是表达式也可以是代码块，实现函数式接口中的方法。这个方法体可以有返回值也可以没有返回值


示例
>     1.不接受参数，直接返回1
>     ()->1
>     2.接受两个int类型的参数，返回这两个参数的和
>     (int x,int y )->x+y
>     3.接受x,y两个参数，JVM根据上下文推断参数的类型，返回两个参数的和
>     (x,y)->x+y
>     4.接受一个字符串，打印该字符串，没有返回值
>     (String name)->System.out.println(name)
>     5.接受一个参数，JVM根据上下文推断参数的类型，打印该参数，没有返回值,只有一个参数可以省略圆括号
>        name->System.out.prinln(name)
>     6.接受两个String类型参数，分别输出，没有返回值
>     (String name,String sex)->{System.out.println(name);System.out.println(sex)}
>     7.接受呀一个参数，返回它本身的2倍
>     x->2*x
>     



**传统写法与Lambda写法的比较**

首先定义一个函数式接口

	/**
	 * 函数式接口
	 * @param <A>
	 * @param <B>
	 */
	@FunctionalInterface
	interface Transform<A,B>{
	    B transform(A a);
	}

**两种写法的对比**

	//传统方式使用接口
    Transform<String ,Integer> transform1 = new Transform<String, Integer>() {
        @Override
        public Integer transform(String s) {
            return Integer.valueOf(s);
        }
    } ;

    //Lambda方式使用接口,就是这么简单粗暴，没脾气
    Transform<String,Integer> transform2 = (s)-> Integer.valueOf(s);


----------


# **访问权限** #

>     在Lambda表达式使用中，Lambda表达式外面的局部变量会被JVM隐式的编译成final类型，Lambda表达式内部只能访问，不能修改 
>     Lambda表达式内部对静态变量和成员变量是可读可写的 
>     Lambda不能访问函数接口的默认方法，在函数接口中可以添加default关键字定义默认的方法


**局部变量示例：**

	public static void main(String[] args) {
        int num = 6;//局部变量
        Sum sum = value -> {
		//num = 8; 这里会编译出错
            return num + value;
        };
        sum.add(8);
    }

    /**
     * 函数式接口
     */
    @FunctionalInterface
    interface Sum{
        int add(int value);
    }

**静态变量和成员变量示例：**

	public int num1 = 6;
    public static int num2 = 8;
    private int getSum(){
        Sum sum = value -> {
            num1 = 10;
            num2 = 10;
            return  num1 + num2;
        };
        return sum.add(1);
    }

	/**
	 * 函数式接口
	 */
    @FunctionalInterface
    interface Sum{
        int add(int value);
    }


----------


# **方法引用** #
>     在lambda表达式中，方法引用是一种简化写法，引用的方法就是Lambda表达式的方法体的实现 
>     语法结构：ObjectRef:: methodName 
>     左边是类名或者实例名，中间的“::”是方法引用符号，右边是相应的方法名 
>     方法引用一般分为三类： 
>     静态方法引用，实例方法引用，构造方法引用


**静态方法引用示例：**

	public static void main(String[] args){
        //传统方式
        Transform<String ,Integer> transform1 = new Transform<String, Integer>() {
            @Override
            public Integer transform(String s) {
                return C_方法引用之静态方法引用.strToInt(s);
            }
        };
        int result1 = transform1.transform("100");

        //Lambda方式
        Transform<String,Integer> transform2 = C_方法引用之静态方法引用 ::strToInt;
        int result2 = transform2.transform("200");
    }

    static int strToInt(String str){
        return Integer.valueOf(str);
    }
	
	/**
	 * 函数式接口
	 * @param <A>
	 * @param <B>
	 */
    @FunctionalInterface
    interface Transform<A,B>{
        B transform(A a);
    }

**实例方法引用示例：**

	public static void main(String[] args){
        //传统方式
        Transform<String ,Integer> transform1 = new Transform<String, Integer>() {
            @Override
            public Integer transform(String s) {
                return new Obj().strToInt(s);
            }
        };
        int result1 = transform1.transform("100");
        //Lambda方式
        Obj obj = new Obj();
        Transform<String,Integer> transform2 = obj::strToInt;
        int result2 = transform2.transform("200");
    }
	/**
	 * 函数式接口
	 * @param <A>
	 * @param <B>
	 */
    interface Transform<A,B>{
        B transform(A a);
    }
    /**
     * 实例对象类
     */
    static class Obj{
        public int strToInt(String str){
            return Integer.valueOf(str);
        }
    }

**构造方法引用示例：**

 		//传统方式
        Factory factory1 = new Factory() {
            @Override
            public Obj create() {
                return new Obj();
            }
        };
        Obj obj = (Boy) factory1.create();
        obj.strToInteger("1");
        
        //Lambda构造函数引用方式,
        Factory factory = Obj::new;
        Obj obj1 = factory.create();
        Transform<String, Integer> transform3 = obj1::strToInteger;

**其他类和接口：**

	//工厂类接口
	public interface Factory {
        Obj create();
    }

----------


# **四个常用的接口** #

**Predicate接口**

	/**
	 * Predicate接口：输入一个参数，返回一个boolean值，内置了许多用于逻辑判断的默认方法
	 */
	public class F_实践之Predicate {
	    public void predicateTest(){
	        Predicate<String> predicateStr = s -> s.length()>8;
	        boolean testResult = predicateStr.test("test");//需要api 24
	        testResult = predicateStr.negate().test("test");//取反，也就是s.length<=8
	
	        Predicate<Object> predicateObj = Objects::nonNull;
	        Object obj = null;
	        testResult = predicateObj.test(obj);//判断是否为空
	    }
	}

**Consumer接口**

	/**
	 * consumer接口：对输入的参数进行操作。有输入没输出
	 */
	private static void consumerTest(){
        Consumer<Integer> add5 = (p) -> {
            System.out.println("old value:" + p);
            p = p + 5;
            System.out.println("new value:" + p);
        };
        add5.accept(10);
    }

**Function接口**

	/**
	 * Function接口：接受一个参数，返回单一的结果。默认的方法（andThen）可将多个函数串在一起，形成复合Funtion（有输入，有输出）结果
	 */
	public static void functionTest(){
         Function<String, String> function = s -> {
               return s + "1";
           };
           Function<String, Integer> function1 = function.andThen(String::length);
           System.out.println(function1.apply("222"));
    }
**Supplier接口**

	/**
	 * Supplier接口：返回一个给定类型的结果。不需要输入参数，无输入有输出
	 */
	private static void supplierTest(){
       Supplier<String> supplier = () -> "无输入有输出";
       String s = supplier.get();
       System.out.println(s);
    }

# **串行stream操作** #

> Lambda为java8带来了闭包，支持对集合对象的stream进行函数式操作， stream api被集成进了collection api ，允许对集合对象进行批量操作。 
> Stream表示数据流，它没有数据结构，本身也不存储元素，其操作也不会改变源Stream，而是生成新Stream.作为一种操作数据的接口，它提供了过滤、排序、映射、规约等多种操作方法， 
> 这些方法按照返回类型被分为两类：凡是返回Stream类型的方法，称之为中间方法（中间操作），其余的都是完结方法（完结操作）。完结方法返回一个某种类型的值，而中间方法则返回新的Stream。 
> 中间方法的调用通常是链式的，该过程会形成一个管道，当完结方法被调用时会导致立即从管道中消费值，这里我们要记住：Stream的操作尽可能以“延迟”的方式运行，也就是我们常说的“懒操作”， 
> 这样有助于减少资源占用，提高性能。对于所有的中间操作（除sorted外）都是运行在延迟模式下。
> 
> Stream不但提供了强大的数据操作能力，更重要的是Stream既支持串行也支持并行，并行使得Stream在多核处理器上有着更好的性能。

Stream的使用过程有着固定的模式：

	1.创建Stream 
	2.通过中间操作，对原始Stream进行“变化”并生成新的Stream 
	3.使用完结操作，生成最终结果




## **中间操作方法**  ##

**过滤(filter)**

结合Predicate接口，Filter对流对象中的所有元素进行过滤,该操作是一个中间操作，这意味着你可以在操作返回结果的基础上进行其他操作

	public static void sreamFilterTest(List<String> lists){ //要明确这list的泛型类型，否则jvm不能根据上下文确定参数类型
        lists.stream().filter((s -> s.startsWith("a"))).forEach(System.out::println);//将开头是a的过滤出来

        //等价于以上操作
        Predicate<String> predicate = (s) -> s.startsWith("a");//将开头是a的过滤出来
        lists.stream().filter(predicate).forEach(System.out::println);

        //连续过滤
        Predicate<String> predicate1 = (s -> s.endsWith("1"));//将开头是a，并且结尾是1的过滤出来
        lists.stream().filter(predicate).filter(predicate1).forEach(System.out::println);
    }

**排序(sorted)**

结合Comparator,该操作返回一个排序过后的流的视图，原始流的顺序不会改变。通过Comparator来指定排序规则，默认是自然排序

	private static void streamSortedTest(List<String> list){
        //默认排序
        list.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
        System.out.println("- - - - - - - - -");
        //自定义排序
        list.stream().sorted(((s, t1) -> t1.compareTo(s))).filter(s -> s.startsWith("a")).forEach(System.out::println);
	}

**映射(map)**

结合Function接口，该操作能将流对象中的每一个元素映射为另一个元素，实现元素类型的转换。

     private static void streamMapTest(List<String> list){
        list.stream().map(String::toUpperCase).sorted((s, t1) -> t1.compareTo(s)).forEach(System.out::println);
        System.out.println("- - - - - - ");
        //自定义映射规则
        Function<String,String> function = s -> {return  s + ".map3";};
        list.stream().map(function).forEach(System.out::println);
      }



## **完结操作方法** ##

**匹配(match)**

用来判断某个predicate是否和流对象相匹配，最终返回boolean类型的结果

	private static void streamMatchTest(List<String> list){
	    //流对象中只要有一个元素匹配就返回true
	    boolean anyStartWithA = list.stream().anyMatch(s -> s.startsWith("a"));
	    System.out.println("集合中是否有以'a'来头："+ anyStartWithA);
	    //流对象中每一个元素都匹配才返回true
	    boolean allStartWithA = list.stream().allMatch(s -> s.startsWith("a"));
	    System.out.println("集合中每一个都是以'a'开头："+ allStartWithA);
	    //流对象中没有匹配时返回true
	    boolean noneStartWithA = list.stream().noneMatch(s -> s.startsWith("c"));
	    System.out.println("集合中没有以'c'开头："+ noneStartWithA);
    }


**收集(collect)**

在对经过变换后，将变换的stream元素收集，比如将这些元素存在集合中，可以使用stream提供的collect方法

    private static void streamCollectTest(List<String> list){
	    List<String> listNew = list.stream().filter(s -> s.startsWith("b")).sorted().collect(Collectors.toList());
	    System.out.println(listNew );
    }

**规约(reduce)**

允许我们用自己的方式计算元素或者将一个stream中元素以某种规律关联

    private static void streamReduceTest(List<String> list){
	    Optional<String> optional = list.stream().sorted().reduce((s, s2) -> {
	    System.out.println(s+"-"+s2);
	    return s+"-"+s2;
	    });
    }

**计数(count)**

用来统计流中元素的总数

    private static void streamCountTest(List<String> list){
	    long count = list.stream().filter(s -> s.startsWith("b")).count();
	    System.out.println("以'b'开头的数量："+ count);
    }


----------


# **并行操作stream** #

> 并行Stream:基于Fork-join并行分解框架实现，将大数据集合切分为多个小数据结合交给不同的线程去处理，这样在多核处理情况下，性能会得到很大的提高。 
> 这和MapReduce的设计理念一致：大任务化小，小任务再分配到不同的机器执行。只不过这里的小任务是交给不同的处理器。 
> 结果是性能提高50%，单核下还是串行流性能比较好，并行流的使用场景是多核+大数据

     //创建一个大集合
    List<String> list = new ArrayList<>();
    for (int i = 0; i < 10000000; i++) {
	    UUID uuid = UUID.randomUUID();
	    list.add(uuid.toString());
    }
    
     //并行stream
    private static void parallelStreamSortedTest(List<String> list){
	    long startTime = System.nanoTime();//返回最准确的可用系统计时器的当前值，以毫微秒为单位。
	    long count = list.parallelStream().sorted().count();
	    long endTime = System.nanoTime();
	    long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
	    System.out.printf("并行排序花费时间：%d ms",millis);
    }

    //串行stream
    private static void streamSortedTest(List<String> list){
	    long startTime = System.nanoTime();//返回最准确的可用系统计时器的当前值，以毫微秒为单位。
	    long count = list.stream().sorted().count();
	    long endTime = System.nanoTime();
	    long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
	    System.out.printf("串行排序花费时间：%d ms",millis);
    }

运行结果：

    > 并行排序花费时间：4362 ms
    > 串行排序花费时间：9847 ms


参考资料：

[Java8 新特性 lambda表达式详解](https://blog.csdn.net/xiaochuanding/article/details/55516726)
