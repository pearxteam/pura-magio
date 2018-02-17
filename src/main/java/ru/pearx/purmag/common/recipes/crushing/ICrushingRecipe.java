package ru.pearx.purmag.common.recipes.crushing;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;

/*
 * Created by mrAppleXZ on 15.02.18 13:27.
 */
public interface ICrushingRecipe
{
    Ingredient getInput();
    ItemStack getOutput();

    default boolean isInputMatches(ItemStack stack)
    {
        return getInput().apply(stack);
    }
    default ItemStack newOutput()
    {
        return getOutput().copy();
    }
}
