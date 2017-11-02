package ru.pearx.purmag.common.jei.magibench;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import ru.pearx.purmag.client.gui.recipes.crafting.AbstractCraftingThings;
import ru.pearx.purmag.common.recipes.recipes.AbstractMagibenchRecipe;
import ru.pearx.purmag.common.recipes.recipes.MagibenchRecipe;

import javax.annotation.Nullable;
import java.util.List;

/*
 * Created by mrAppleXZ on 01.11.17 22:40.
 */
public class MagibenchShapedRecipeWrapper extends AbstractMagibenchRecipeWrapper implements IShapedCraftingRecipeWrapper
{
    private MagibenchRecipe recipe;

    public MagibenchShapedRecipeWrapper(MagibenchRecipe recipe, IStackHelper help)
    {
        super(recipe, help);
        this.recipe = recipe;
    }

    @Override
    public int getWidth()
    {
        return recipe.getRecipeWidth();
    }

    @Override
    public int getHeight()
    {
        return recipe.getRecipeHeight();
    }


}
