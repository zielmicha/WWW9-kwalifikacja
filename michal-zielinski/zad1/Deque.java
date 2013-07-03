
public class Deque {
    /** Position of first element. Always in range [0, storage.length) */
    private int begin = 0;
    /** Position of element after last element. Always in range (0, storage.length] */
    private int end = 0;
    private int itemCount = 0;
    private int[] storage = new int[2];

    /** Makes sure that begin and end are in range
        and that criteria are met. */
    private void normalizeAndAllocate() {
        if(begin == -1)
            begin = storage.length - 1;
        if(begin == storage.length)
            begin = 0;
        if(end == 0)
            end = storage.length;
        if(end == storage.length + 1)
            end = 1;
        if(itemCount == storage.length)
            reallocate(storage.length * 2);
        if(size() * 4 < storage.length)
            reallocate(storage.length / 2);
    }

    private void reallocate(int newCapacity) {
        int[] newStorage = new int[newCapacity];
        int currentSize = size();
        for(int i=0; i<currentSize; i++) {
            newStorage[i] = at(i);
        }
        begin = 0;
        end = currentSize;
        storage = newStorage;
    }

    public int size() {
        return itemCount;
    }

    public void pushLeft(int el) {
        begin--;
        itemCount ++;
        normalizeAndAllocate();
        storage[begin] = el;
    }

    public void pushRight(int el) {
        end++;
        itemCount ++;
        normalizeAndAllocate();
        storage[end - 1] = el;
    }

    public int popLeft() {
        int item = at(0);
        begin ++;
        itemCount --;
        normalizeAndAllocate();
        return item;
    }

    public int popRight() {
        int item = at(size() - 1);
        end --;
        itemCount --;
        normalizeAndAllocate();
        return item;
    }

    public int at(int pos) {
        return storage[(begin + pos) % storage.length];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int hasCycle() {
        return CycleFinder.getCycleLength(0, new CycleFinder.Function() {
                public int call(int arg) {
                    if(arg < 0 || arg >= size())
                        return -1;
                    else
                        return at(arg);
                }
            });
    }

    public static Deque make(int... source) {
        Deque d = new Deque();
        for(int item: source)
            d.pushRight(item);
        return d;
    }
}
