package ru.pearx.purmag.common.expressions.operations;

import org.apache.commons.lang3.tuple.Pair;
import ru.pearx.purmag.common.expressions.IExpression;
import ru.pearx.purmag.common.expressions.NumberGenerators;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class Operation implements Cloneable
{

    Function<Random, Integer> generator = NumberGenerators::nextNum;
    String format = null;

    public abstract IExpression.ExpressionData apply(int... vals);


    //Apply operation using suggested generator
    public abstract IExpression.ExpressionData apply(Random rand);

    @Nullable
    public abstract int[] getValidArgsCount();

    @Nullable
    public Function<Random, Integer> getGenerator()
    {
        return generator;
    }

    public String getFormat()
    {
        return format;
    }

    protected abstract Operation cloneAction();

    @Override
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new IllegalStateException("Something went wrong");
        }
    }

    public static class Binary extends Operation
    {

        private final BiFunction<Integer, Integer, Pair<Integer, Integer[]>> op;

        public Binary(BiFunction<Integer, Integer, Pair<Integer, Integer[]>> op)
        {
            this.op = op;
        }

        @Override
        public IExpression.ExpressionData apply(Random rand)
        {
            return apply(generator.apply(rand), generator.apply(rand));
        }

        @Override
        public IExpression.ExpressionData apply(int... vals)
        {
            if (vals.length != 2)
            {
                throw new IllegalArgumentException("Binary operation should receive only two args");
            }
            return apply(vals[0], vals[1]);
        }

        public IExpression.ExpressionData apply(int left, int right)
        {
            Pair<Integer, Integer[]> res = op.apply(left, right);
            Integer[] vals = res.getRight();
            if (vals == null) vals = new Integer[]{left, right};
            return IExpression.ExpressionData.createData(getFormat(), res.getLeft(), (Object[]) vals);
        }

        @Override
        public int[] getValidArgsCount()
        {
            return new int[]{2};
        }

        @Override
        protected Operation cloneAction()
        {
            return new Binary(op);
        }
    }

}
