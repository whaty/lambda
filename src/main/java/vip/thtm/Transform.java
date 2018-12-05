package vip.thtm;

/**
 * @author: liangcan
 * @version: 1.0
 * @date: 2018/12/5 16:09
 * @describtion: Transform
 */
@FunctionalInterface
public interface Transform<A, B> {
    /**
     * @param a
     * @return
     */
    B transform(A a);
}
