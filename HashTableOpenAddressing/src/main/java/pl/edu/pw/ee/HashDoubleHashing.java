package pl.edu.pw.ee;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);
        validateHashSize(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();
        int fFunction = key % m;
        int gFunction = 1 + (key % (m - 3));

        int hash = (fFunction + i * gFunction) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;
    }

    private void validateHashSize(int size) {
        if(size == 3) {
            throw new IllegalArgumentException("Size of hash cannot be equal to 3!");
        }
    }

}
