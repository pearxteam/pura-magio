package ru.pearx.purmag.common.compat.jei.magibench;

import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import ru.pearx.purmag.common.recipes.recipes.MagibenchRecipe;

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
