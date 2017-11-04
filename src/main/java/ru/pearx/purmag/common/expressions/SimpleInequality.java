package ru.pearx.purmag.common.expressions;

import ru.pearx.purmag.PurMag;

import java.util.Random;

public class SimpleInequality extends Expression
{
    private final Expression base = PurMag.INSTANCE.getExpressionRegistry().simple;
    private static final String FORMAT = "%s ? %s (Answer <, > or =)";
    public SimpleInequality() {
        setRegistryName("simple_ie");
        setLuck(5);
    }

    @Override
    public String getDescription()
    {
        return "Inequalities composed of simple expressions, like 6/2; 2+3 or 3*9; 5-2";
    }

    @Override
    public boolean enabledByDefault()
    {
        return true;
    }

    @Override
    public ExpressionData generateExpression(Random rand)
    {
        ExpressionData first = base.generateExpression(rand), second = base.generateExpression(rand);
        String op;
        int cmp = Integer.compare(Integer.parseInt(first.getResult()), Integer.parseInt(second.getResult()));
        switch (cmp) {
            case -1: {
                op = "<";
                break;
            }
            case 0:  {
                op = "=";
                break;
            }
            case 1: {
                op = ">";
                break;
            }
            default: {
                throw new RuntimeException(String.format("PURMAG: Integer.compare returned wrong value (%d), contact mod authors", cmp));
            }
        }
        return createData(FORMAT, op, first.getText(), second.getText());
    }

}
