package expression;

import java.math.BigInteger;

public class Variable implements ExpressionContainer {
    private final String view;

    public Variable(String view) {
        this.view = view;
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return x;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (view) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new AssertionError("Unknown variable name");
        }
    }

    @Override
    public String toString() {
        return view;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Variable that && this.view.equals(that.view));
    }

    @Override
    public int hashCode() {
        return view.hashCode();
    }
}
