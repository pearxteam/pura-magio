package net.pearx.purmag.client.guis;

import net.minecraft.client.gui.GuiScreen;

/**
 * Created by mrAppleXZ on 15.04.17 9:48.
 */
public interface IGuiDrawable
{
    short getWidth();
    short getHeight();

    void draw(GuiScreen gs, int x, int y);
}
