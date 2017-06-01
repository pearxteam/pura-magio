package net.pearx.purmag.client.guis.drawables;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 15.04.17 9:49.
 */
@SideOnly(Side.CLIENT)
public class ItemDrawable implements IGuiDrawable
{
    public ItemStack stack;
    public float scale;

    public ItemDrawable(ItemStack stack, float scale)
    {
        this.stack = stack;
        this.scale = scale;
    }

    public ItemDrawable(ItemStack stack)
    {
        this(stack, 1);
    }

    @Override
    public void draw(int x, int y)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0);
        GlStateManager.scale(scale, scale, scale);
        RenderHelper.enableGUIStandardItemLighting();
        RenderItem rend = Minecraft.getMinecraft().getRenderItem();
        rend.renderItemAndEffectIntoGUI(stack, 0, 0);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    @Override
    public int getWidth()
    {
        return (int)(16 * scale);
    }

    @Override
    public int getHeight()
    {
        return (int)(16 * scale);
    }
}
