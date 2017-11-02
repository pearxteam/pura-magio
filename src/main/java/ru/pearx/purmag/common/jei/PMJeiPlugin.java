package ru.pearx.purmag.common.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import ru.pearx.libmc.common.ItemStackUtils;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.gui.GuiContainerMagibench;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.inventory.ContainerMagibench;
import ru.pearx.purmag.common.items.ItemRegistry;
import ru.pearx.purmag.common.jei.magibench.AbstractMagibenchRecipeWrapper;
import ru.pearx.purmag.common.jei.magibench.MagibenchRecipeCategory;
import ru.pearx.purmag.common.jei.magibench.MagibenchShapedRecipeWrapper;
import ru.pearx.purmag.common.jei.magibench.MagibenchShapelessRecipeWrapper;
import ru.pearx.purmag.common.magibench.MagibenchRegistry;
import ru.pearx.purmag.common.recipes.recipes.AbstractMagibenchRecipe;
import ru.pearx.purmag.common.recipes.recipes.MagibenchRecipe;
import ru.pearx.purmag.common.recipes.recipes.MagibenchShapelessRecipe;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by mrAppleXZ on 14.08.17 11:42.
 */
@JEIPlugin
public class PMJeiPlugin implements IModPlugin
{
    @Override
    public void register(IModRegistry registry)
    {
        for(MagibenchRegistry.Tier t : PurMag.INSTANCE.getMagibenchRegistry().getTiers())
        {
            registry.addRecipeCatalyst(new ItemStack(ItemRegistry.magibench, 1, t.getTier()), MagibenchRecipeCategory.ID);
        }
        registry.handleRecipes(AbstractMagibenchRecipeWrapper.class, recipe -> recipe, MagibenchRecipeCategory.ID);

        List<AbstractMagibenchRecipeWrapper> lst = new ArrayList<>();
        for(IRecipe rec : ForgeRegistries.RECIPES)
        {
            if(rec instanceof MagibenchRecipe)
                lst.add(new MagibenchShapedRecipeWrapper((MagibenchRecipe)rec, registry.getJeiHelpers().getStackHelper()));
            if(rec instanceof MagibenchShapelessRecipe)
                lst.add(new MagibenchShapelessRecipeWrapper((MagibenchShapelessRecipe)rec, registry.getJeiHelpers().getStackHelper()));
        }
        registry.addRecipes(lst, MagibenchRecipeCategory.ID);
        registry.addRecipeClickArea(GuiContainerMagibench.class, );
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        registry.addRecipeCategories(new MagibenchRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }
}
