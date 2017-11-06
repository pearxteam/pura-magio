package ru.pearx.purmag.common.expressions;

import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.expressions.operations.EquationOperations;
import ru.pearx.purmag.common.expressions.operations.SimpleOperations;

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
        getRegistry().register(simple = new Expression("simple", "Simple math expressions from 1th-4th grades like 2 + 2 or 3 * 8.", true, 0, SimpleOperations.getOps()));
        getRegistry().register(simpleEq = new Expression("simple_eq", "Simple equations, like x + 2 = 5 or 5 / x = 1", true, 5, EquationOperations.getOps()));
        getRegistry().register(simpleIe = new SimpleInequality());
    }
}
