package ru.pearx.purmag.client.gui.controls.recipes;

import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.gui.IRecipeLayoutDrawable;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Colors;
import ru.pearx.lib.Supplied;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.common.AbstractOfParts;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.compat.jei.PMJeiPlugin;
import ru.pearx.purmag.common.compat.jei.magibench.MagibenchRecipeCategory;

import java.util.List;

/*
 * Created by mrAppleXZ on 03.09.17 11:50.
 */
@SideOnly(Side.CLIENT)
public class CraftingControl extends AbstractOfParts
{
    private static final Class<? extends IIngredients> INGREDIENTS;

    static
    {
        try
        {
            INGREDIENTS = Class.forName("mezz.jei.ingredients.Ingredients").asSubclass(IIngredients.class);
        }
        catch (ClassNotFoundException e)
        {
            throw new ReportedException(CrashReport.makeCrashReport(e, "Unable to locate JEI"));
        }
    }

    private IRecipeLayoutDrawable draw;
    private String title;
    private int off;

    public <T extends IRecipeWrapper> CraftingControl(IRecipeCategory<T> category, T wrapper)
    {
        super(Utils.gRL("textures/gui/recipe_transition.png"), 2);
        this.title = category.getTitle();
        off = DrawingTools.getStringHeight(title) + 2;
        IRecipeRegistry reg = PMJeiPlugin.RUNTIME.getRecipeRegistry();
        IRecipeLayoutDrawable draw = reg.createRecipeLayoutDrawable(category, wrapper, reg.createFocus(IFocus.Mode.OUTPUT, new ItemStack(Items.STICK /*hack*/)));
        setWidth(category.getBackground().getWidth() + size * 2);
        setHeight(category.getBackground().getHeight() + size * 2);
        draw.setPosition(size, size);
        this.draw = draw;
    }

    public static <T extends ICraftingRecipeWrapper> Supplied<CraftingControl> fromCrafting(String category, ResourceLocation id)
    {
        return new Supplied<>(() ->
        {
            IRecipeCategory cat = PMJeiPlugin.RUNTIME.getRecipeRegistry().getRecipeCategory(category);
            List<T> lst = PMJeiPlugin.RUNTIME.getRecipeRegistry().getRecipeWrappers(cat);
            T rec = null;
            for (T wr : lst)
            {
                if (wr.getRegistryName().equals(id))
                {
                    rec = wr;
                    break;
                }
            }
            return new CraftingControl(cat, rec);
        });
    }

    public static Supplied<CraftingControl> fromVanillaCrafting(ResourceLocation id)
    {
        return fromCrafting(VanillaRecipeCategoryUid.CRAFTING, id);
    }

    public static Supplied<CraftingControl> fromMagibench(ResourceLocation id)
    {
        return fromCrafting(MagibenchRecipeCategory.ID, id);
    }

    public static <T extends IRecipeWrapper> Supplied<CraftingControl> fromSmelting(ItemStack input)
    {
        return new Supplied<>(() ->
        {
            IRecipeCategory cat = PMJeiPlugin.RUNTIME.getRecipeRegistry().getRecipeCategory(VanillaRecipeCategoryUid.SMELTING);
            List<T> lst = PMJeiPlugin.RUNTIME.getRecipeRegistry().getRecipeWrappers(cat);
            T rec = null;
            try
            {
                IIngredients holder = INGREDIENTS.newInstance();
                recipeLoop:
                for (T wr : lst)
                {
                    wr.getIngredients(holder);
                    List<List<ItemStack>> inp = holder.getInputs(ItemStack.class);
                    if (inp.size() > 0)
                    {
                        List<ItemStack> i = inp.get(0);
                        for (ItemStack is : i)
                        {
                            if (ItemStack.areItemStacksEqual(is, input))
                            {
                                rec = wr;
                                break recipeLoop;
                            }
                        }
                    }
                }
            }
            catch (InstantiationException | IllegalAccessException e)
            {
                throw new ReportedException(CrashReport.makeCrashReport(e, "Something went wrong when creating furnace recipe renderer"));
            }
            return new CraftingControl(cat, rec);
        });
    }


    @Override
    public void render()
    {
        super.render();
        draw.drawRecipe(Minecraft.getMinecraft(), getLastMouseX(), getLastMouseY());
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, -off, 0);
        DrawingTools.drawString(title, (getWidth() - DrawingTools.measureString(title)) / 2, 0, Colors.WHITE);
        GlStateManager.popMatrix();
    }

    @Override
    public void render2()
    {
        if (isFocused())
        {
            draw.drawOverlays(Minecraft.getMinecraft(), getLastMouseX(), getLastMouseY());
        }
    }
}