package pl.edu.pw.ee;

import java.util.*;

import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {
    private List<T> heap = new ArrayList<>();

    @Override
    public void put(T item) {
        heap.add(item);
        heapUp(item);
    }

    @Override
    public T pop() {
        if (heap.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("Heap is empty!");
        }

        int lastIndex = heap.size() - 1;
        heapDown();
        T lastElement = heap.get(lastIndex);
        heap.remove(lastIndex);

        return lastElement;
    }

    private void heapUp(T item) {
        int p, itemIndex;
        T parentVal;

        itemIndex = heap.size() - 1;
        p = (itemIndex - 1) / 2;

        parentVal = heap.get(p);

        while (p >= 0 && parentVal.compareTo(item) < 0) {
            Collections.swap(heap, itemIndex, p);

            itemIndex = p;
            p = (itemIndex - 1) / 2;
            parentVal = heap.get(p);
        }

    }

    private void heapDown() {
        int lastIndex = heap.size() - 1;
        int rootIndex = 0;
        int l, p;
        T pVal, lVal;

        Collections.swap(heap, lastIndex, rootIndex);

        if (lastIndex-- > 0) {
            p = 0;

            while ((l = 2 * p + 1) <= lastIndex) {
                lVal = heap.get(l);
                pVal = heap.get(p);

                if (l + 1 <= lastIndex && lVal.compareTo(heap.get(l + 1)) < 0) {
                    l++;
                    lVal = heap.get(l);
                }

                if (pVal.compareTo(lVal) >= 0)
                    return;

                Collections.swap(heap, l, p);
                p = l;
            }
        }

    }

    public T getItem(int index) {
        return heap.get(index);
    }
}
