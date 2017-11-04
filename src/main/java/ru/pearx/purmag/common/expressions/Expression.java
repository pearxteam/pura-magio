package ru.pearx.purmag.common.expressions;

import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Random;

/*
 * Created by mrAppleXZ on 04.10.17 20:42.
 */
public abstract class Expression extends IForgeRegistryEntry.Impl<IExpression> implements IExpression
{

    protected int luck = 0;

    public void setLuck(int luck)
    {
        this.luck = luck;
    }

    public int getLuck()
    {
        return luck;
    }

    protected ExpressionData createData(String format, int result, Object... vals) {
        return createData(format, Integer.toString(result), vals);
    }

    protected ExpressionData createData(String format, String result, Object... vals)
    {
        ExpressionData dat = new ExpressionData();
        dat.setLuck(luck);
        dat.setText(String.format(format, vals));
        dat.setResult(result);
        return dat;
    }

    protected int nextNum(Random rand)
    {
        return rand.nextInt(100001);
    }

    protected int nextSmallNum(Random rand)
    {
        int[] simple = new int[]{1, 2, 3, 5, 7, 11, 13};
        int times = rand.nextInt(5) + 1;
        int num = 1;
        for (int i = 0; i < times; i++)
        {
            num *= simple[rand.nextInt(simple.length)];
        }
        return num;
    }
}
