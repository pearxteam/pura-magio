package net.pearx.purmag.client.guis.if_tablet;

import net.pearx.purmag.client.guis.controls.Control;

/**
 * Created by mrAppleXZ on 23.04.17 19:53.
 */
public class ControlIfTabletPart extends Control
{
    public GuiIfTablet getTablet()
    {
        if(getParent() instanceof GuiIfTablet)
            return (GuiIfTablet) getParent();
        return null;
    }
}
