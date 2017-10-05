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
        int i1 = rand.nextInt(1001);
        int i2 = rand.nextInt(1001);
        dat.setText(i1 + " + " + i2);
        dat.setResult(Integer.toString(i1 + i2));
        return dat;
    }
}
