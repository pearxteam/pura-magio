package net.pearx.purmag.client.guis.if_tablet;

import net.pearx.purmag.client.guis.controls.Control;

/**
 * Created by mrAppleXZ on 28.04.17 11:34.
 */
public class GuiIfTabletSEPart extends Control
{
    public GuiIfTabletSE getTabletScreen()
    {
        if(getParent() instanceof GuiIfTabletSE)
            return (GuiIfTabletSE) getParent();
        return null;
    }
}
