package net.pearx.purmag.client.guis;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

/**
 * Created by mrAppleXZ on 15.04.17 9:49.
 */
public class ItemDrawable implements IGuiDrawable
{
    public ItemStack stack;

    public ItemDrawable(ItemStack stack)
    {
        this.stack = stack;
    }

    @Override
    public void draw(GuiScreen gs, int x, int y)
    {
        gs.mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x, y);
    }
}
