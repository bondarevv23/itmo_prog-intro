package expression;

import java.math.BigInteger;

public class Subtract extends AbstractTwoVariablesExpression {

    private static int priority = -2;
    private static String stringOperation = " - ";

    public Subtract(ExpressionContainer firstItem, ExpressionContainer secondItem) {
        super(firstItem, secondItem);
    }

    public int count(int x, int y) {
        return x - y;
    }

    public BigInteger count(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public String getStringOperation() {
        return stringOperation;
    }
}
