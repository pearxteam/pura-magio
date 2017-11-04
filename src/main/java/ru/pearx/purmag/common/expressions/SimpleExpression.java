package ru.pearx.purmag.common.expressions;

import java.util.Random;
import java.util.function.Function;

/*
 * Created by mrAppleXZ on 29.09.17 20:20.
 */
public class SimpleExpression extends Expression
{
    public SimpleExpression()
    {
        setRegistryName("simple");
        setLuck(0);
    }

    @Override
    public String getDescription()
    {
        return "Simple math expressions from 1th-4th grades like 2 + 2 or 3 * 8.";
    }

    @Override
    public boolean enabledByDefault()
    {
        return true;
    }

    @Override
    public ExpressionData generateExpression(Random rand)
    {
        int act = rand.nextInt(4);
        Function<Random, Integer> generator = act < 3 ? this::nextNum : this::nextSmallNum; //Use small numbers for multiplication and division
        int i1 = generator.apply(rand), i2 = generator.apply(rand);
        String format = "Something went wrong";
        int result = -1;

        switch (act)
        {
            case 0:
            {
                format = "%d + %d";
                result = i1 + i2;

            }
            break;
            case 1:
            {
                if(i2 > i1) {
                    int tmp = i1;
                    i1 = i2;
                    i2 = tmp;
                }
                format = "%d - %d";
                result = i1 - i2;
            }
            break;
            case 2:
            {
                format = "%d * %d";
                result = i1 * i2;
            }
            break;
            case 3:
            {
                int mul = i1 * i2;
                int min = Math.min(i1, i2);
                i1 = mul;
                i2 = min;
                format = "%d / %d";
                result = i1 / i2;
            }
            break;
        }

        return createData(format, result, i1, i2);
    }
}
