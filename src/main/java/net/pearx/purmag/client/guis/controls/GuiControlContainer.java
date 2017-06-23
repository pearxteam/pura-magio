package net.pearx.purmag.client.guis.controls;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.IGuiScreen;

/**
 * Created by mrAppleXZ on 16.04.17 19:52.
 */
@SideOnly(Side.CLIENT)
public class GuiControlContainer extends Control
{
    public GuiControlContainer(Control cont)
    {
        controls.add(cont);
    }

    private IGuiScreen gs;

    public IGuiScreen getGs()
    {
        return gs;
    }

    public void setGs(IGuiScreen gs)
    {
        this.gs = gs;
    }

    @Override
    public int getWidth()
    {
        return getGuiScreen().getWidth();
    }

    @Override
    public int getHeight()
    {
        return getGuiScreen().getHeight();
    }
}
