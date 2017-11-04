package ru.pearx.purmag.common.jei.magibench;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.infofield.IfTier;
import ru.pearx.purmag.common.magibench.MagibenchRegistry;
import ru.pearx.purmag.common.recipes.recipes.AbstractMagibenchRecipe;
import ru.pearx.purmag.common.recipes.recipes.MagibenchRecipe;

import javax.annotation.Nullable;
import java.util.List;

/*
 * Created by mrAppleXZ on 02.11.17 18:57.
 */
public abstract class AbstractMagibenchRecipeWrapper implements ICraftingRecipeWrapper
{
    public AbstractMagibenchRecipe recipe;
    private IStackHelper help;

    public AbstractMagibenchRecipeWrapper(AbstractMagibenchRecipe recipe, IStackHelper help)
    {
        this.recipe = recipe;
        this.help = help;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setOutput(ItemStack.class, recipe.getRecipeOutput());
        ingredients.setInputLists(ItemStack.class, help.expandRecipeItemStackInputs(recipe.getIngredients()));
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName()
    {
        return recipe.getRegistryName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
    {
        MagibenchRegistry.Tier t = PurMag.INSTANCE.getMagibenchRegistry().getTier(recipe.getTier());
        DrawingTools.drawTexture(t.getGuiTexture(), 0, 0, MagibenchRecipeCategory.WIDTH, MagibenchRecipeCategory.HEIGHT, t.getGuiJeiStartX(), t.getGuiJeiStartY(), 196, 190);
    }
}
