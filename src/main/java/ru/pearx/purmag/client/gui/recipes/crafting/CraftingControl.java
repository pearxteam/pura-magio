package ru.pearx.purmag.client.gui.recipes.crafting;

import mezz.jei.api.recipe.IFocus;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.Point;
import ru.pearx.libmc.client.gui.IGuiScreen;
import ru.pearx.libmc.client.gui.controls.Control;
import ru.pearx.libmc.client.gui.controls.common.Button;
import ru.pearx.libmc.client.gui.drawables.item.ItemDrawable;
import ru.pearx.purmag.client.GuiDrawableRegistry;
import ru.pearx.purmag.client.PurMagClient;
import ru.pearx.purmag.common.compat.Loadings;
import ru.pearx.purmag.common.compat.jei.PMJeiPlugin;

/*
 * Created by mrAppleXZ on 03.09.17 11:50.
 */
@SideOnly(Side.CLIENT)
public class CraftingControl extends Control
{
    private ItemDrawable outDraw;
    private int xOut, yOut;
    private Button showJei;

    public CraftingControl(IRecipe recipe)
    {
        setWidth(118);
        setHeight(88);

        outDraw = new ItemDrawable(recipe.getRecipeOutput(), 2f);

        xOut = (getWidth() - outDraw.getWidth()) / 2;
        yOut = 5;

        if(Loadings.JEI)
            showJei = new Button(PurMagClient.BUTTON_TEXTURE, I18n.format("misc.gui.crafting.show"), () ->
                    PMJeiPlugin.RUNTIME.getRecipesGui().show(PMJeiPlugin.RUNTIME.getRecipeRegistry().createFocus(IFocus.Mode.OUTPUT, recipe.getRecipeOutput())));
        else
            showJei = new Button(PurMagClient.BUTTON_TEXTURE, I18n.format("misc.gui.crafting.jeiUnavailable"), () -> {});

        showJei.setSize(getWidth() - 5*2, 32);
        showJei.setPos(5, getHeight() - showJei.getHeight() - 5);
    }

    public CraftingControl(ResourceLocation loc)
    {
        this(ForgeRegistries.RECIPES.getValue(loc));
    }

    @Override
    public void render()
    {
        IGuiScreen gs = getGuiScreen();
        GuiDrawableRegistry.crafting.draw(gs, 0, 0);
        outDraw.draw(gs, xOut, yOut);
    }

    @Override
    public void render2()
    {
        if(isFocused())
        {
            Point pos = getPosOnScreen();
            IGuiScreen gs = getGuiScreen();
            outDraw.drawTooltip(gs, xOut, yOut, getLastMouseX(), getLastMouseY(), pos.getX(), pos.getY());
        }
    }

    @Override
    public void init()
    {
        controls.add(showJei);
    }
}