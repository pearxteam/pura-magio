package net.pearx.purmag.common.infofield.steps;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;

/**
 * Created by mrAppleXZ on 22.04.17 14:46.
 */
public interface IIfResearchStep
{
    /**
     * Gets the description of the research step that will be rendered in IF Tablet.
     */
    String getDescription();

    /**
     * Gets the display name of the research step that will be rendered in IF Tablet tab tooltip.
     */
    String getDisplayName();

    /**
     * Gets the Icon of the research step that will be rendered on the IF Tablet's tab.
     */
    @SideOnly(Side.CLIENT)
    IGuiDrawable getIcon();
}
