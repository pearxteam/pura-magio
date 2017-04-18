package net.pearx.purmag.client.guis.controls;

import net.minecraft.client.gui.GuiScreen;

/**
 * Created by mrAppleXZ on 16.04.17 19:52.
 */
public class GuiControlContainer extends Control
{
    private GuiScreen gs;

    public GuiScreen getGs()
    {
        return gs;
    }

    public void setGs(GuiScreen gs)
    {
        this.gs = gs;
    }

    @Override
    public int getWidth()
    {
        return getGuiScreen().width;
    }

    @Override
    public int getHeight()
    {
        return getGuiScreen().height;
    }
}
