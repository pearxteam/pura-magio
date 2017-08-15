package ru.pearx.purmag.client.gui.recipes;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.Control;
import ru.pearx.libmc.client.gui.drawables.ItemDrawable;
import ru.pearx.purmag.common.Utils;

/*
 * Created by mrAppleXZ on 15.08.17 14:49.
 */
public class FurnaceControl extends Control
{
    private ItemStack in;
    private ItemStack out;
    private ItemDrawable inDraw;
    private ItemDrawable outDraw;

    private int lastX;
    private int lastY;

    public FurnaceControl(ItemStack in, ItemStack out)
    {
        this.in = in;
        this.out = out;
        //40 + 32 (item) + 40
        setWidth(112);
        //8 + 32 (item) + 32 (flame) + 32 (item) + 8
        setHeight(112);
    }

    @Override
    public void render()
    {
        if(inDraw == null || !ItemStack.areItemStacksEqualUsingNBTShareTag(inDraw.stack, in))
            inDraw = new ItemDrawable(in, 2);
        if(outDraw == null || !ItemStack.areItemStacksEqualUsingNBTShareTag(outDraw.stack, out))
            outDraw = new ItemDrawable(out, 2);
        GlStateManager.enableBlend();
        DrawingTools.drawTexture(Utils.getRegistryName("textures/gui/recipes/furnace.png"), 0, 0, getWidth(), getHeight());
        GlStateManager.disableBlend();
        inDraw.drawWithTooltip(getGuiScreen(), 40, 16, lastX, lastY);
        outDraw.drawWithTooltip(getGuiScreen(), 40, 54, lastX, lastY);
    }

    public ItemStack getIn()
    {
        return in;
    }

    public void setIn(ItemStack in)
    {
        this.in = in;
    }

    public ItemStack getOut()
    {
        return out;
    }

    public void setOut(ItemStack out)
    {
        this.out = out;
    }

    @Override
    public void mouseMove(int x, int y, int dx, int dy)
    {
        lastX = x;
        lastY = y;
    }

    @Override
    public void setFocused(boolean val)
    {
        super.setFocused(val);
        if(!val)
        {
            lastX = -1;
            lastY = -1;
        }
    }
}
