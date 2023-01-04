package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;
    private Comparable<T> DELETED = new Deleted<T>();

    private class Deleted<T2> implements Comparable<T2> {

        @Override
        public int compareTo(T2 o) {
            return 0;
        }
    }

    HashOpenAdressing() {
        this(2039); // initial size as random prime number
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.correctLoadFactor = 0.75;

        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Comparable[this.size];
        this.hashElems = array;
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);
        int firstHashId = hashId;

        while (hashElems[hashId] != nil && !hashElems[hashId].equals(newElem) && !hashElems[hashId].equals(DELETED)) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);

            if(isLooping(firstHashId, hashId)) {
                doubleResize();
                put(newElem);
                return;
            }
        }

        if (hashElems[hashId] != nil && hashElems[hashId].equals(newElem)) {
            hashElems[hashId] = newElem;
            return;
        }

        hashElems[hashId] = newElem;
        nElems++;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while ((hashElems[hashId] != nil && !hashElems[hashId].equals(elem))) {
            i++;
            hashId = hashFunc(key, i);
        }

        elem = hashElems[hashId];

        return elem;
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && !hashElems[hashId].equals(elem)) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        if (hashElems[hashId] == nil || hashElems[hashId] == DELETED) {
            return;
        }

        hashElems[hashId] = (T) DELETED;
        nElems--;
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == nil) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        this.size *= 2;

        @SuppressWarnings("unchecked")
        T[] hashElemsDoubleSized = (T[]) new Comparable[this.size];
        T[] tmp = hashElems;
        hashElems = hashElemsDoubleSized;
        nElems = 0;

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] != null && !tmp[i].equals(DELETED)) {
                put(tmp[i]);
            }
        }
    }

    private boolean isLooping(Integer fisrtHashId, Integer hashId) {
        if (fisrtHashId == hashId) {
            return true;
        }

        return false;
    }

    public void printStructure() {
        for (int n = 0; n < hashElems.length; n++) {
            if (hashElems[n] == null) {
                System.out.println(n + ". " + null);
            }
            if (hashElems[n] != null) {
                int key = hashElems[n].hashCode();
                int hashId = hashFunc(key, 0);
                int hashId1 = hashFunc(key, 1);
                int hashId2 = hashFunc(key, 2);
                int hashId3 = hashFunc(key, 3);
                int hashId4 = hashFunc(key, 4);
                System.out.println(n + ". " + hashElems[n] + " " + hashId + " " + hashId1 + " " + hashId2 + " "
                        + hashId3 + " " + hashId4);
            }
        }
    }

}