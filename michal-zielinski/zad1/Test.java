import java.util.*;

public class Test {
    public static void main(String[] args) {
        testCycles();
        for(int seed=0; seed < 100; seed++)
            testList(seed);
    }

    static void testCycles() {
        assert Deque.make(1, 2, 3, 4, 5, 6, 7, 8, 4).hasCycle() == 5;
        assert Deque.make(1, 2, 3, 4, 5, 6, 7, 8, 1).hasCycle() == 8;
        assert Deque.make(1, 1, 34, 12).hasCycle() == 1;
        assert Deque.make(0).hasCycle() == 1;
        assert Deque.make(1, 2, 3, 4, 5, 6, 7, 8, 100).hasCycle() == -1;
    }

    static void testList(int seed) {
        LinkedList<Integer> javas = new LinkedList<Integer>();
        Deque my = new Deque();

        Random gen = new Random(seed);

        for(int i=0; i<100000; i++) {
            int op = gen.nextInt(4);
            int item = gen.nextInt(100000);
            switch(op) {
            case 0: // push left
                my.pushLeft(item);
                javas.addFirst(item);
                break;
            case 1: // push left
                my.pushRight(item);
                javas.addLast(item);
                break;
            case 2: // pop left
                if(javas.size() != 0) {
                    my.popLeft();
                    javas.removeFirst();
                }
            case 3: // pop right
                if(javas.size() != 0) {
                    my.popRight();
                    javas.removeLast();
                }
            }
            assert my.size() == javas.size();
            for(int j=0; j<javas.size(); j++) {
                assert my.at(j) == javas.get(j);
            }
        }
    }
}
