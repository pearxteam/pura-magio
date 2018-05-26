package ru.pearx.purmag.client.gui.controls;

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
import ru.pearx.carbide.Colors;
import ru.pearx.carbide.Supplied;
import ru.pearx.carbide.mc.client.gui.DrawingTools;
import ru.pearx.carbide.mc.client.gui.controls.common.AbstractOfParts;
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
    public static final int BORDER_PURMAG_SIZE = 2;
    public static final ResourceLocation BORDER_PURMAG = Utils.gRL("textures/gui/recipe_transition.png");
    public static final int BORDER_VANILLA_SIZE = 4;
    public static final ResourceLocation BORDER_VANILLA = Utils.gRL("textures/gui/recipe_transition_vanilla.png");

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

    public <T extends IRecipeWrapper> CraftingControl(IRecipeCategory<T> category, T wrapper, ResourceLocation border, int borderSize)
    {
        super(border, 2);
        this.title = category.getTitle();
        off = DrawingTools.getStringHeight(title) + 2;
        IRecipeRegistry reg = PMJeiPlugin.RUNTIME.getRecipeRegistry();
        IRecipeLayoutDrawable draw = reg.createRecipeLayoutDrawable(category, wrapper, reg.createFocus(IFocus.Mode.OUTPUT, new ItemStack(Items.STICK /*hack*/)));
        setWidth(category.getBackground().getWidth() + size * 2);
        setHeight(category.getBackground().getHeight() + size * 2);
        draw.setPosition(size, size);
        this.draw = draw;
    }

    public static <T extends ICraftingRecipeWrapper> Supplied<CraftingControl> fromCrafting(String category, ResourceLocation id, ResourceLocation border, int borderSize)
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
            return new CraftingControl(cat, rec, border, borderSize);
        });
    }

    public static Supplied<CraftingControl> fromVanillaCrafting(ResourceLocation id)
    {
        return fromCrafting(VanillaRecipeCategoryUid.CRAFTING, id, BORDER_VANILLA, BORDER_VANILLA_SIZE);
    }

    public static Supplied<CraftingControl> fromMagibench(ResourceLocation id)
    {
        return fromCrafting(MagibenchRecipeCategory.ID, id, BORDER_PURMAG, BORDER_PURMAG_SIZE);
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
            return new CraftingControl(cat, rec, BORDER_VANILLA, BORDER_VANILLA_SIZE);
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