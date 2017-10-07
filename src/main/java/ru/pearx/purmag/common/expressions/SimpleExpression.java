package ru.pearx.purmag.common.expressions;

import net.minecraftforge.registries.IForgeRegistryEntry;
import ru.pearx.lib.RandomUtils;

import javax.script.ScriptEngine;
import java.util.Random;

/*
 * Created by mrAppleXZ on 29.09.17 20:20.
 */
public class SimpleExpression extends Expression
{
    public SimpleExpression()
    {
        setRegistryName("simple");
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
        //todo this
        ExpressionData dat = new ExpressionData();
        dat.setLuck(0);

        int act = rand.nextInt(4);
        switch (act)
        {
            case 0:
            {
                int i1 = rand.nextInt(100001);
                int i2 = rand.nextInt(100001);
                dat.setText(i1 + " + " + i2);
                dat.setResult(Integer.toString(i1 + i2));
            }
            break;
            case 1:
            {
                int i1 = rand.nextInt(100001);
                int i2 = rand.nextInt(100001);
                int first = Math.max(i1, i2);
                int second = Math.min(i1, i2);
                dat.setText(first + " - " + second);
                dat.setResult(Integer.toString(first - second));
            }
            break;
            case 2:
            {
                int[] simple = new int[] {1, 2, 3, 5, 7, 11, 13};
                int firstTimes = rand.nextInt(5) + 1;
                int first = 1;
                for(int i = 0; i < firstTimes; i++)
                {
                    first *= simple[rand.nextInt(simple.length)];
                }
                int second = 1;
                int secondTimes = rand.nextInt(3) + 1;
                for(int i = 0; i < secondTimes; i++)
                {
                    second *= simple[rand.nextInt(simple.length)];
                }
                dat.setText(first + " * " + second);
                dat.setResult(Integer.toString(first * second));
            }
            break;
            case 3:
            {
                int[] simple = new int[] {1, 2, 3, 5, 7, 11, 13};
                int firstTimes = rand.nextInt(5) + 1;
                int first = 1;
                for(int i = 0; i < firstTimes; i++)
                {
                    first *= simple[rand.nextInt(simple.length)];
                }
                int second = 1;
                int secondTimes = rand.nextInt(3) + 1;
                for(int i = 0; i < secondTimes; i++)
                {
                    second *= simple[rand.nextInt(simple.length)];
                }
                int result = first * second;

                int min = Math.min(first, second);
                dat.setText(result + " / " + min);
                dat.setResult(Integer.toString(result / min));
            }
            break;
        }

        return dat;
    }
}
