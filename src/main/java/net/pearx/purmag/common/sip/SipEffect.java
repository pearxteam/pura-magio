package net.pearx.purmag.common.sip;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * Created by mrAppleXZ on 21.05.17 17:09.
 */
public class SipEffect
{
    private Potion effect;
    private int seconds;
    private int maxLevel;

    public SipEffect(Potion effect, int seconds, int maxLevel)
    {
        this.effect = effect;
        this.seconds = seconds;
        this.maxLevel = maxLevel;
    }

    public Potion getEffect()
    {
        return effect;
    }

    public void setEffect(Potion effect)
    {
        this.effect = effect;
    }

    public int getSeconds()
    {
        return seconds;
    }

    public void setSeconds(int seconds)
    {
        this.seconds = seconds;
    }

    public int getMaxLevel()
    {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel)
    {
        this.maxLevel = maxLevel;
    }
}
