package net.pearx.purmag.client.guis.if_tablet.steps;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.client.guis.if_tablet.GuiIfTabletSR;

/**
 * Created by mrAppleXZ on 29.04.17 20:01.
 */
@SideOnly(Side.CLIENT)
public class IRSRenderer extends Control
{
    @Override
    public void init()
    {
        setWidth(getTablet().getWidth() - 16);
        setHeight(getTablet().getHeight() - (8 + 38 + 24));
        setX(8);
        setY(38);
    }

    public GuiIfTabletSR getTablet()
    {
        return (GuiIfTabletSR)getParent();
    }
}
