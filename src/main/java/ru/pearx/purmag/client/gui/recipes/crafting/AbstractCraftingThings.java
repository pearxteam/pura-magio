package ru.pearx.purmag.client.gui.recipes.crafting;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by mrAppleXZ on 04.09.17 13:47.
 */
public final class AbstractCraftingThings
{
    public static List<List<ItemStack>> getInputsShapeless(IRecipe recipe)
    {
        List<List<ItemStack>> lst = new ArrayList<>();
        List<Ingredient> ings = recipe.getIngredients();
        for (int i = 0; i < 9; i++)
        {
            List<ItemStack> matching = new ArrayList<>();
            if (i < ings.size())
            {
                Ingredient ing = ings.get(i);
                for(ItemStack stack : ing.getMatchingStacks())
                {
                    NonNullList<ItemStack> subs = NonNullList.create();
                    stack.getItem().getSubItems(stack.getItem().getCreativeTab() == null ? CreativeTabs.SEARCH : stack.getItem().getCreativeTab(), subs);
                    for(ItemStack sub : subs)
                    {
                        if(ing.apply(sub))
                            matching.add(sub);
                    }
                }
            }
            else
                matching.add(ItemStack.EMPTY);
            lst.add(matching);
        }
        return lst;
    }

    public static ItemStack getOutputSimple(IRecipe recipe)
    {
        return recipe.getRecipeOutput();
    }
}
