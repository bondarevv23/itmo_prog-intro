package expression;

import java.math.BigInteger;

public abstract class AbstractTwoVariablesExpression implements ExpressionContainer {
    
    protected ExpressionContainer firstItem;
    protected ExpressionContainer secondItem;

    private int hash = 0;

    AbstractTwoVariablesExpression(ExpressionContainer firstItem,
                                    ExpressionContainer secondItem) {
        this.firstItem = firstItem;
        this.secondItem = secondItem;
    }

    abstract public int getPriority();

    abstract public String getStringOperation();

    abstract public int count(int x, int y);

    abstract public BigInteger count(BigInteger x, BigInteger y);

    @Override
    public BigInteger evaluate(BigInteger x) {
        return count(firstItem.evaluate(x), secondItem.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return count(firstItem.evaluate(x), secondItem.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return count(firstItem.evaluate(x, y, z), secondItem.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        StringBuilder box = new StringBuilder();
        fillStringBuilder(box);
        return box.toString();
    }

    @Override
    public void fillStringBuilder(StringBuilder box) {
        box.append("(");
        firstItem.fillStringBuilder(box);
        box.append(getStringOperation());
        secondItem.fillStringBuilder(box);
        box.append(")");
    }

    @Override
    public String toMiniString() {
        StringBuilder box = new StringBuilder();
        firstItem.fillMiniStringBuilder(box, getPriority(), false);
        box.append(getStringOperation());
        secondItem.fillMiniStringBuilder(box, getPriority(), true);
        return box.toString();
    }

    @Override
    public void fillMiniStringBuilder(StringBuilder box, int parentPriority, boolean secondRun) {
        if (Math.abs(getPriority()) > Math.abs(parentPriority) 
            || getPriority() + parentPriority == 0 && secondRun && parentPriority < 2
            || secondRun &&  getPriority() < 0 && getPriority() == parentPriority
        ) {
            box.append("(");
            fillElement(box, this);
            box.append(")");
        } else {
            fillElement(box, this);
        }
    }

    private void fillElement(StringBuilder box, AbstractTwoVariablesExpression expr) {
        expr.firstItem.fillMiniStringBuilder(box, expr.getPriority(), false);
        box.append(getStringOperation());
        expr.secondItem.fillMiniStringBuilder(box, expr.getPriority(), true);
    }

    @Override
    public int hashCode() {
        if (hash == 0) {
            int stringHash = (getStringOperation().hashCode() % 37) * 447;
            hash = firstItem.hashCode() * stringHash  + secondItem.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractTwoVariablesExpression that) {
            return (this.firstItem.equals(that.firstItem) &&
                    this.secondItem.equals(that.secondItem) &&
                    this.getPriority() == that.getPriority());
        }
        return false;
    }
}
