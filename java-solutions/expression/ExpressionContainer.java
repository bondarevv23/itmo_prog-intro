package expression;

public interface ExpressionContainer extends Expression, TripleExpression, BigIntegerExpression {

    default void fillStringBuilder(StringBuilder box) {
        box.append(toString());
    }

    default void fillMiniStringBuilder(StringBuilder box, int parentPriority, boolean secondRun) {
        box.append(toString());
    }
}
