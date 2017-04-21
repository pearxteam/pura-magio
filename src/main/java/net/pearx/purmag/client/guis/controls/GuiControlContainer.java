package net.pearx.purmag.client.guis.controls;

import net.minecraft.client.gui.GuiScreen;
import net.pearx.purmag.client.guis.PmGui;

/**
 * Created by mrAppleXZ on 16.04.17 19:52.
 */
public class GuiControlContainer extends Control
{
    public GuiControlContainer(Control cont)
    {
        controls.add(cont);
    }

    public GuiControlContainer()
    {

    }

    private PmGui gs;

    public PmGui getGs()
    {
        return gs;
    }

    public void setGs(PmGui gs)
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
