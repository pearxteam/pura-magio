package ru.pearx.purmag.common.infofield.steps;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.if_tablet.steps.IRSRenderer;

/**
 * Created by mrAppleXZ on 22.04.17 14:46.
 */
public interface IIfResearchStep
{
    /**
     * Gets the description of the research step that will be rendered in IF Tablet.
     */
    @SideOnly(Side.CLIENT)
    String getDescription();

    /**
     * Gets the display name of the research step that will be rendered in IF Tablet.
     */
    @SideOnly(Side.CLIENT)
    String getDisplayName();

    @SideOnly(Side.CLIENT)
    IRSRenderer getRenderer();
}
