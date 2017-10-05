package ru.pearx.purmag.common.expressions;

import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Random;

/*
 * Created by mrAppleXZ on 29.09.17 20:08.
 */
public interface IExpression extends IForgeRegistryEntry<IExpression>
{
    class ExpressionData
    {
        private String text;
        private String result;
        private float luck;

        public ExpressionData(String text, String result, float luck)
        {
            this.text = text;
            this.result = result;
            this.luck = luck;
        }

        public ExpressionData()
        {
        }

        public String getText()
        {
            return text;
        }

        public void setText(String text)
        {
            this.text = text;
        }

        public String getResult()
        {
            return result;
        }

        public void setResult(String result)
        {
            this.result = result;
        }

        public float getLuck()
        {
            return luck;
        }

        public void setLuck(float luck)
        {
            this.luck = luck;
        }
    }

    String getDescription();
    boolean enabledByDefault();
    ExpressionData generateExpression(Random rand);
}
