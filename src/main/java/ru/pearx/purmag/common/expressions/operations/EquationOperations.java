package ru.pearx.purmag.common.expressions.operations;

import org.apache.commons.lang3.tuple.Pair;

import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;

public class EquationOperations
{

    public static Operation.Binary ADD = new OperationBuilder<>(new Operation.Binary(createEqOp((x, y) -> x + y))).setFormat("x + %d = %d").getOp();
    public static Operation.Binary SUB_N = new OperationBuilder<>(new Operation.Binary(createEqOp((x, y) -> x - y))).setFormat("x - %d = %d").getOp();
    public static Operation.Binary SUB_X = new OperationBuilder<>(new Operation.Binary(createEqOp((x, y) -> y - x))).setFormat("%d - x = %d").getOp();
    public static Operation.Binary MUL = new OperationBuilder<>(new Operation.Binary(createEqOp((x, y) -> x * y))).setFormat("x * %d = %d").getOp();
    public static Operation.Binary DIV_N = new OperationBuilder<>(new Operation.Binary((x, y) -> divide(x, y, false))).setFormat("x / %d = %d").getOp();
    public static Operation.Binary DIV_X = new OperationBuilder<>(new Operation.Binary((x, y) -> divide(x, y, true))).setFormat("%d / x = %d").getOp();
    private static final Operation.Binary[] OPS = {
            ADD,
            SUB_N,
            SUB_X,
            MUL,
            DIV_N,
            DIV_X
    };

    /**
     * @return result, mod
     */
    private static Pair<Integer, Integer[]> divide(int x, int y, boolean reverse)
    {
        //Remove zero from pool
        x++;
        y++;
        if (reverse)
        {
            int tmp = x;
            x = y;
            y = tmp;
        }
        int mod = x % y;
        x -= mod;
        int res = x / y; //After module subtraction division is guaranteed to be integer
        if (reverse)
        {
            int tmp = x;
            x = y;
            y = tmp;
        }
        return Pair.of(x, new Integer[]{y, res});
    }

    private static BiFunction<Integer, Integer, Pair<Integer, Integer[]>> createEqOp(IntBinaryOperator simpleOp)
    {
        return (x, y) ->
        {
            int res = simpleOp.applyAsInt(x, y);
            return Pair.of(x, new Integer[]{y, res});
        };
    }

    public static Operation.Binary[] getOps()
    {
        return OPS;
    }
}
