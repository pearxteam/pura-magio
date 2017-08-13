package ru.pearx.purmag.common.sip;

import net.minecraft.potion.Potion;

/**
 * Created by mrAppleXZ on 21.05.17 17:09.
 */
public class SipEffect
{
    private Potion effect;
    private int ticks;
    private int maxLevel;

    public SipEffect(Potion effect, int ticks, int maxLevel)
    {
        this.effect = effect;
        this.ticks = ticks;
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

    public int getTicks()
    {
        return ticks;
    }

    public void setTicks(int ticks)
    {
        this.ticks = ticks;
    }

    /**
     * Get the max level of the effect. -1 to unlimited.
     */
    public int getMaxLevel()
    {
        return maxLevel;
    }

    /**
     * Set the max level of the effect. Set to -1 for unlimited level.
     */
    public void setMaxLevel(int maxLevel)
    {
        this.maxLevel = maxLevel;
    }
}
