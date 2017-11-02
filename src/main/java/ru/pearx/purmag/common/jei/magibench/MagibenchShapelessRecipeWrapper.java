package ru.pearx.purmag.common.jei.magibench;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import ru.pearx.purmag.common.recipes.recipes.AbstractMagibenchRecipe;
import ru.pearx.purmag.common.recipes.recipes.MagibenchRecipe;
import ru.pearx.purmag.common.recipes.recipes.MagibenchShapelessRecipe;

import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 02.11.17 17:33.
 */
public class MagibenchShapelessRecipeWrapper extends AbstractMagibenchRecipeWrapper
{
    public MagibenchShapelessRecipeWrapper(MagibenchShapelessRecipe recipe, IStackHelper help)
    {
        super(recipe, help);
    }
}
