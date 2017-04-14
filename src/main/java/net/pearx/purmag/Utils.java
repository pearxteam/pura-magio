package net.pearx.purmag;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 08.04.17 18:53.
 */
public class Utils
{
    public static String getRegistryName(String name)
    {
        return PurmagCore.ModId + ":" + name;
    }

    @SideOnly(Side.CLIENT)
    public static void setModelLocation(Item itm)
    {
        ModelLoader.setCustomModelResourceLocation(itm, 0, new ModelResourceLocation(itm.getRegistryName(), "normal"));
    }
}
