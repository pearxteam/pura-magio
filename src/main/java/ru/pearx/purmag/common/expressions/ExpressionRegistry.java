package ru.pearx.purmag.common.expressions;

import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import ru.pearx.purmag.common.Utils;

/*
 * Created by mrAppleXZ on 04.10.17 20:36.
 */
public class ExpressionRegistry
{
    private final IForgeRegistry<IExpression> registry = new RegistryBuilder<IExpression>().setName(Utils.gRL("expression")).setType(IExpression.class).create();

    public Expression simple;
    public Expression simpleEq;
    public Expression simpleIe;

    public IForgeRegistry<IExpression> getRegistry()
    {
        return registry;
    }

    public void setup()
    {
        getRegistry().register(simple = new SimpleExpression());
        getRegistry().register(simpleEq = new SimpleEquation());
        getRegistry().register(simpleIe = new SimpleInequality());
    }
}
