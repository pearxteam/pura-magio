package net.pearx.purmag.common.infofield.pages;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.if_tablet.pages.IPRenderer;

/**
 * Created by mrAppleXZ on 22.04.17 20:42.
 */
@SideOnly(Side.CLIENT)
public interface IIfPage
{
    IPRenderer getRenderer();
}
