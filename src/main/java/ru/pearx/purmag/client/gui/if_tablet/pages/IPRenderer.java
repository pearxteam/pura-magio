package ru.pearx.purmag.client.gui.if_tablet.pages;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.Control;
import ru.pearx.purmag.client.GuiDrawableRegistry;
import ru.pearx.purmag.client.gui.if_tablet.GuiIfTabletSP;
import ru.pearx.purmag.client.infofield.pages.IIfPage;

/**
 * Created by mrAppleXZ on 03.05.17 21:30.
 */
@SideOnly(Side.CLIENT)
public class
IPRenderer<T extends IIfPage> extends Control
{
    public T page;

    public IPRenderer(T page)
    {
        this.page = page;
    }

    @Override
    public void init()
    {
        setWidth(getTablet().getWidth() - 16);
        //top + bottom + back height + text + splitter + margins
        setHeight(getTablet().getHeight() - (8 + 8 + 16 + DrawingTools.getFontHeight() + GuiDrawableRegistry.splitter.getHeight() + 4));
    }

    public GuiIfTabletSP getTablet()
    {
        return (GuiIfTabletSP) getParent();
    }
}
