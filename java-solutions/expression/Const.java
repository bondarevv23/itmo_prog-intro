package expression;

import java.math.BigInteger;

public class Const implements ExpressionContainer {
    private int value;
    private BigInteger bigValue;

    public Const(final int x) {
        this.value = x;
        this.bigValue = null;
    }

    public Const(final BigInteger x) {
        this.bigValue = x;
    }
    
    @Override
    public BigInteger evaluate(BigInteger x) {
        return bigValue;
    }

    @Override
    public int evaluate(int x) {
        return (int) value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) value;
    }

    @Override
    public String toString() {
        if (bigValue == null) {
            return Integer.toString(value);
        }
        return bigValue.toString(10);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const that) {
            if (this.bigValue == null) {
                return (that.bigValue == null && this.value == that.value);
            }
            return this.bigValue.equals(that.bigValue);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (bigValue == null) {
            return value;
        }
        return bigValue.hashCode();
    }
}