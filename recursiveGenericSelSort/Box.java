package recursiveGenericSelSort;

/**
 *
 * @author bas
 * @param <T>
 */
public class Box<T extends Comparable<T>> implements Comparable<Box<T>> {

    private T t;

    public Box(T t) {
        this.t = t;
    }

    @Override
    public int compareTo(Box<T> o) {
        if (this.t.equals(o.t)) {
            return 0;
        } else if (this.t.compareTo(o.t) < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public String toString() {
        return this.t.toString();
    }

}
