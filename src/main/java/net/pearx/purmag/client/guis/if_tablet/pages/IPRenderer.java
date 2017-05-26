package net.pearx.purmag.client.guis.if_tablet.pages;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.client.guis.if_tablet.GuiIfTabletSP;
import net.pearx.purmag.common.infofield.pages.IIfPage;

/**
 * Created by mrAppleXZ on 03.05.17 21:30.
 */
@SideOnly(Side.CLIENT)
public class IPRenderer extends Control
{
    public IIfPage page;
    public IPRenderer(IIfPage page)
    {
        this.page = page;
    }

    @Override
    public void init()
    {
        setWidth(getTablet().getWidth() - 16);
        setHeight(getTablet().getHeight() - (16 + 24));
        setX(8);
        setY(8 + 10);
    }

    public GuiIfTabletSP getTablet()
    {
        return (GuiIfTabletSP)getParent();
    }
}
