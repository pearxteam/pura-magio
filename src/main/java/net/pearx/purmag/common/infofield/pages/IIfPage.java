package net.pearx.purmag.common.infofield.pages;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 22.04.17 20:42.
 */
public interface IIfPage
{
    @SideOnly(Side.CLIENT)
    void render();
}