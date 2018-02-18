package ru.pearx.purmag.common.recipes.crushing;

import net.minecraft.item.ItemStack;

/*
 * Created by mrAppleXZ on 17.02.18 19:55.
 */
public class CrushingOutput
{
    private ItemStack stack;
    private float chance;

    public CrushingOutput(ItemStack stack, float chance)
    {
        this.stack = stack;
        this.chance = chance;
    }

    public ItemStack getStack()
    {
        return stack;
    }

    public float getChance()
    {
        return chance;
    }
}
