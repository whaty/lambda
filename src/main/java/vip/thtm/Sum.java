package vip.thtm;

/**
 * @author: liangcan
 * @version: 1.0
 * @date: 2018/12/5 16:02
 * @describtion: Sum
 */
@FunctionalInterface
public interface Sum {
    /**
     * 函数式接口
     *
     * @param a
     * @return
     */
    int add(int a);

    /**
     * @param x
     * @param y
     * @return int
     * FunctionalInterface 的接口，只允许有一个抽象方法，但是default方法不是抽象方法，所以这里允许存在
     */
    default int sub(int x, int y) {
        return x * y;
    }
}
