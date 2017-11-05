package ru.pearx.purmag.common.expressions.operations;

import org.apache.commons.lang3.tuple.Pair;
import ru.pearx.purmag.common.expressions.NumberGenerators;

public class SimpleOperations
{
    public static final Operation.Binary ADD = new OperationBuilder<>(new Operation.Binary((x, y) -> Pair.of(x + y, null))).setFormat("%d + %d").getOp();
    public static final Operation.Binary SUB = new OperationBuilder<>(
            new Operation.Binary((x, y) -> y < x ? Pair.of(x - y, new Integer[]{x, y}) : Pair.of(y - x, new Integer[]{y, x}))).setFormat("%d - %d").getOp();
    public static final Operation.Binary MUL = new OperationBuilder<>(new Operation.Binary((x, y) -> Pair.of(x * y, null)))
            .setFormat("%d * %d").setGenerator(NumberGenerators::nextPrimeDivisorsNum).getOp();
    public static final Operation.Binary DIV = new OperationBuilder<>(new Operation.Binary((x, y) ->
    {
        int mod = x % y;
        x -= mod;
        int res = x / y;
        return Pair.of(res, new Integer[]{x, y});
    })).setFormat("%d / %d").setGenerator(NumberGenerators::nextPrimeDivisorsNum).getOp();

    private static final Operation.Binary[] OPS = {
            ADD,
            SUB,
            MUL,
            DIV
    };

    public static Operation.Binary[] getOps()
    {
        return OPS;
    }
}
