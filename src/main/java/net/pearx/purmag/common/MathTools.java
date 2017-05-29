package net.pearx.purmag.common;

/**
 * Created by mrAppleXZ on 29.05.17 10:16.
 */
public class MathTools
{
    public static int remainderOrAll(int divisible, int divisor)
    {
        int i = divisible % divisor;
        return i == 0 ? divisible : i;
    }
}
