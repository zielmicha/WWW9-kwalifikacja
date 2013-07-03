
public class CycleFinder {
    /** This implements Floyd's cycle-finding algorithm.
     Returns size of some cycle in sequence, if exists, -1 if not. */
    public static int getCycleLength(int start, Function t) {
        int p1 = start, p2 = start;
        do {
            p1 = t.call(p1);
            if(p1 == -1) break;
            p2 = t.call(p2);
            if(p2 == -1) break;
            p2 = t.call(p2);
            if(p2 == -1) break;
        } while(p1 != p2);
        if(p1 == -1 || p2 == -1)
            return -1;
        int size = 0;
        do {
            p2 = t.call(p2);
            size ++;
        } while(p1 != p2);
        return size;
    }

    public static interface Function {
        int call(int arg);
    }
}
