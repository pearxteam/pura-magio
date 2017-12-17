package ru.pearx.purmag.common.compat.jei.magibench;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.magibench.MagibenchRegistry;
import ru.pearx.purmag.common.recipes.recipes.AbstractMagibenchRecipe;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Created by mrAppleXZ on 02.11.17 18:57.
 */
public abstract class AbstractMagibenchRecipeWrapper implements ICraftingRecipeWrapper
{
    public static final ResourceLocation INFO = Utils.gRL("textures/gui/icons/magibench_info.png");
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
        DrawingTools.drawTexture(INFO, MagibenchRecipeCategory.WIDTH - 16, 2, 14, 14);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public List<String> getTooltipStrings(int mouseX, int mouseY)
    {
        int stX = MagibenchRecipeCategory.WIDTH - 16;
        int stY = 2;
        if(mouseX >= stX && mouseY >= stY && mouseX <= stX + 14 && mouseY <= stY + 14)
        {
            boolean unloc = Minecraft.getMinecraft().player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).isFullyUnlocked(recipe.getEntry());
            return Arrays.asList(
                    I18n.format("misc.jei.magibench.tooltip.entry"),
                    (unloc ? TextFormatting.GREEN : TextFormatting.RED) + PurMag.INSTANCE.getIfRegistry().getEntry(recipe.getEntry()).getDisplayName() + TextFormatting.RESET,
                    I18n.format("misc.jei.magibench.tooltip.tier"),
                    TextFormatting.GRAY + I18n.format(PurMag.INSTANCE.getMagibenchRegistry().getTier(recipe.getTier()).getUnlocalizedName()) + TextFormatting.RESET
            );
        }
        return Collections.emptyList();
    }
}
