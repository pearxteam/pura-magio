package ru.pearx.purmag.common.expressions;

import java.util.Random;

public class NumberGenerators
{

    public static int nextNum(Random rand)
    {
        return rand.nextInt(100001);
    }

    public static int nextPrimeDivisorsNum(Random rand)
    {
        int[] prime = new int[]{1, 2, 3, 5, 7, 11, 13};
        int times = rand.nextInt(5) + 1;
        int num = 1;
        for (int i = 0; i < times; i++)
        {
            num *= prime[rand.nextInt(prime.length)];
        }
        return num;
    }
}
