package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.pearx.purmag.client.guis.controls.GuiControlContainer;

/**
 * Created by mrAppleXZ on 18.04.17 23:26.
 */
public class GuiIfTabletContainer extends GuiControlContainer
{
    public GuiIfTabletContainer()
    {
        controls.add(new GuiIfTablet());
    }
}
