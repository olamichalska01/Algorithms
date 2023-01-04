package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {
    private double a;
    private double b;

    public HashQuadraticProbing() {
        super();
        a = 26;
        b = 7;
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);
        this.a = a;
        this.b = b;

        validateCoefficients(a, b);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int hash = (key % m + (int) a * i + (int) b * i * i) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;
    }

    private void validateCoefficients(double a, double b) {
        if (a == 0 || b == 0) {
            throw new IllegalArgumentException("At least one coefficient (a or b) cannot be equal to zero!");
        }
    }
}
