package net.pearx.purmag.client.models;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 24.06.17 11:27.
 */
public interface IModelProvider
{
    @SideOnly(Side.CLIENT)
    void setupModels();
}
