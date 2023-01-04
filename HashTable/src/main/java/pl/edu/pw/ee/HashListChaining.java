package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem<T> nil = null;
    private Elem<T>[] hashElems;
    private int nElem;

    private class Elem<E> {
        private E value;
        private Elem<E> next;

        Elem(E value, Elem<E> nextElem) {
            this.value = value;
            this.next = nextElem;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    public HashListChaining(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size of hash list cannot be 0 or less!");
        }
        
        @SuppressWarnings("unchecked")
        final Elem<T>[] hashElems = (Elem<T>[]) new Elem[size];
        this.hashElems = hashElems;
        initializeHash();
    }

    @Override
    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot add a null elem!");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> oldElem = hashElems[hashId];
        while (oldElem != nil && !oldElem.value.equals(value)) {
            oldElem = oldElem.next;
        }

        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            Elem<T> newElem = new Elem<T>(value, hashElems[hashId]);
            hashElems[hashId] = newElem; 
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot get a null elem!");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems[hashId];

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : null;
    }

    @Override
    public void delete(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot delete a null elem!");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems[hashId];
        Elem<T> previous = nil;

        if (elem != nil && elem.value.equals(value)) {
            if (elem.next == nil) {
                hashElems[hashId] = nil;
                nElem--;
                return;
            }

            if (previous == nil && elem.next != nil) {
                hashElems[hashId] = elem.next;
                nElem--;
                return;
            }
        }

        while (elem != nil && !elem.value.equals(value)) {
            previous = elem;
            elem = elem.next;
        }

        if (elem == nil) {
            return;
        }

        previous.next = elem.next;
        nElem--;
    }

    public int getNumOfElemsInHash() {
        return nElem;
    }

    public double countLoadFactor() {
        double size = hashElems.length;
        return nElem / size;
    }

    private void initializeHash() {
        int n = hashElems.length;

        for (int i = 0; i < n; i++) {
            hashElems[i] = nil;
        }
    }

    private int countHashId(int hashCode) {
        int n = hashElems.length;
        return Math.abs(hashCode) % n;
    }

    public void printStructure() {
        for (Elem<T> elem : hashElems) {
            System.out.print(elem);
            while (elem != nil && elem.next != nil) {
                System.out.print(" -> " + elem.next);
                elem = elem.next;
            }
            System.out.println("");
        }
    }

    public T getNextElem(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems[hashId];

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem.next != nil ? elem.next.value : null;
    }

    public T getPreviousElem(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem<T> elem = hashElems[hashId];
        Elem<T> previous = nil;

        while (elem != nil && !elem.value.equals(value)) {
            previous = elem;
            elem = elem.next;
        }

        return previous != nil ? previous.value : null;
    }
}