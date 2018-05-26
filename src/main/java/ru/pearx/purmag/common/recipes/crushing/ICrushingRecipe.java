package ru.pearx.purmag.common.recipes.crushing;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nullable;
import java.util.Random;

/*
 * Created by mrAppleXZ on 15.02.18 13:27.
 */
public interface ICrushingRecipe
{
    interface ChanceModifier
    {
        float apply(float f);
    }
    boolean isInputMatches(ItemStack stack);
    NonNullList<ItemStack> createOutputs(Random rand, @Nullable ChanceModifier modifier);
}
