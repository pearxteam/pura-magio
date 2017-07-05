package net.pearx.purmag.client.guis;

import net.minecraft.item.ItemStack;

/**
 * Created by mrAppleXZ on 26.05.17 13:51.
 */
public interface IGuiScreen
{
    int getWidth();
    int getHeight();

    int getMouseX();
    int getMouseY();

    void drawTooltip(ItemStack stack, int x, int y);
    void drawHovering(String text, int x, int y);
}
