package expression;

import java.math.BigInteger;

public class Divide extends AbstractTwoVariablesExpression {

    private static int priority = -1;
    private static String stringOperation = " / ";

    public Divide(ExpressionContainer firstItem, ExpressionContainer secondItem) {
        super(firstItem, secondItem);
    }

    public int count(int x, int y) {
        return x / y;
    }

    public BigInteger count(BigInteger x, BigInteger y) {
        return x.divide(y);
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
