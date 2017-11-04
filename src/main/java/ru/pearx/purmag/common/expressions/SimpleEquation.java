package ru.pearx.purmag.common.expressions;

import java.util.Random;
import java.util.function.Function;

public class SimpleEquation extends Expression
{

    public SimpleEquation()
    {
        setRegistryName("simple_eq");
        setLuck(5);
    }

    @Override
    public String getDescription()
    {
        return "Simple equations, like x + 2 = 5 or 5 / x = 1";
    }

    @Override
    public boolean enabledByDefault()
    {
        return true;
    }

    @Override
    public ExpressionData generateExpression(Random rand)
    {
        int opType = rand.nextInt(6); //x + n; x - n; n - x; x * n; x / n; n / x
        Function<Random, Integer> generator = opType < 3 ? this::nextNum : this::nextSmallNum; //Use small numbers for multiplication and division
        int x = generator.apply(rand), n = generator.apply(rand);
        int result = -1;
        String format = "SOMETHING WENT WRONG!!!! OpType: " + opType;
        switch (opType)
        {
            case 0:
            {
                result = x + n;
                format = "x + %d = %d";
            }
            break;
            case 1:
            {
                result = x - n;
                format = "x - %d = %d";
            }
            break;
            case 2:
            {
                result = n - x;
                format = "%d - x = %d";
            }
            break;
            case 3:
            {
                result = x * n;
                format = "x * %d = %d";
            }
            break;
            case 4:
            {
                int mul = x * n;
                int min = Math.min(x, n);
                result = mul / min;
                x = mul;
                n = min;
                format = "x / %d = %d";
            }
            break;
            case 5:
            {
                int mul = x * n;
                int min = Math.min(x, n);
                result = mul / min;
                x = min;
                n = mul;
                format = "%d / x = %d";
            }
            break;
        }

        return createData(format, x, n, result);
    }


}
