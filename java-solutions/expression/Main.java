package expression;

public class Main {
    public static void main(String[] args) {
        ExpressionContainer v = new Multiply(
            new Const(2),
            new Variable("x")
        );
        ExpressionContainer v2 = new Add(
            new Subtract(
                new Multiply(
                    new Variable("x"),
                    new Variable("x")
                ),
                new Multiply(
                    new Const(2),
                    new Variable("x"))
                ),
            new Const(1)
        );
        System.out.println(v2.toMiniString());
    }
}
