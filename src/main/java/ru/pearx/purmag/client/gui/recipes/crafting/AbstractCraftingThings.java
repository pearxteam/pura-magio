package ru.pearx.purmag.client.gui.recipes.crafting;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.ShapedOreRecipe;
import ru.pearx.libmc.common.ItemStackUtils;

import java.util.ArrayList;
import java.util.Collections;
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
            if (i < ings.size())
                lst.add(ItemStackUtils.getIngredientItems(ings.get(i)));
            else
                lst.add(ItemStackUtils.EMPTY_LIST);
        return lst;
    }

    private static List<List<ItemStack>> getInputsShaped(IRecipe recipe, int width, int height)
    {
        List<List<ItemStack>> lst = new ArrayList<>();
        List<Ingredient> ings = recipe.getIngredients();
        int i = 0;
        for (int y = 3; y > 0; y--)
            for (int x = 3; x > 0; x--)
                if (x > width || y > height)
                    lst.add(Collections.singletonList(ItemStack.EMPTY));
                else if (i < ings.size())
                    lst.add(ItemStackUtils.getIngredientItems(ings.get(i++)));
                else
                    lst.add(ItemStackUtils.EMPTY_LIST);
        return lst;
    }

    public static List<List<ItemStack>> getInputsShaped(ShapedRecipes recipe)
    {
        return getInputsShaped(recipe, recipe.recipeWidth, recipe.recipeHeight);
    }

    public static List<List<ItemStack>> getInputsShaped(ShapedOreRecipe recipe)
    {
        return getInputsShaped(recipe, recipe.getWidth(), recipe.getHeight());
    }

    public static ItemStack getOutputSimple(IRecipe recipe)
    {
        return recipe.getRecipeOutput();
    }
}
