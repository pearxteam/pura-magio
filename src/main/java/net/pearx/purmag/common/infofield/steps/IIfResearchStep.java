package net.pearx.purmag.common.infofield.steps;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.if_tablet.steps.IRSRenderer;

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
     * Gets the display name of the research step that will be rendered in IF Tablet.
     */
    String getDisplayName();

    @SideOnly(Side.CLIENT)
    IRSRenderer getRenderer();
}
