package ru.pearx.purmag.common.expressions;

import net.minecraftforge.registries.IForgeRegistryEntry;
import ru.pearx.purmag.common.expressions.operations.Operation;

import java.util.Random;

/*
 * Created by mrAppleXZ on 04.10.17 20:42.
 */
public class Expression extends IForgeRegistryEntry.Impl<IExpression> implements IExpression
{
    private final String desc;
    private final boolean enabledByDefault;
    private final int luck;
    private final Operation[] pool;

    public Expression(String name, String desc, boolean enabledByDefault, int luck, Operation... pool)
    {
        setRegistryName(name);
        this.desc = desc;
        this.enabledByDefault = enabledByDefault;
        this.luck = luck;
        this.pool = pool;
    }

    private Operation selectOp(Random rand)
    {
        return pool[rand.nextInt(pool.length)];
    }


    @Override
    public String getDescription()
    {
        return desc;
    }

    @Override
    public boolean enabledByDefault()
    {
        return enabledByDefault;
    }

    @Override
    public ExpressionData generateExpression(Random rand)
    {
        ExpressionData dat = selectOp(rand).apply(rand);
        dat.setLuck(luck);
        return dat;
    }
}
