package net.pearx.purmag.client.guis.if_tablet;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.controls.Control;

/**
 * Created by mrAppleXZ on 28.04.17 11:27.
 */
@SideOnly(Side.CLIENT)
public class GuiIfTabletS extends Control
{
    public GuiIfTablet getTablet()
    {
        if(getParent() instanceof GuiIfTablet)
            return (GuiIfTablet) getParent();
        return null;
    }

    @Override
    public int getWidth()
    {
        return getTablet().getWidth();
    }

    @Override
    public int getHeight()
    {
        return getTablet().getHeight();
    }
}
