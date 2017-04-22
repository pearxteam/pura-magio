package net.pearx.purmag.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 08.04.17 18:53.
 */
@SideOnly(Side.CLIENT)
public class ClientUtils
{
    public static void setModelLocation(Item itm, int meta)
    {
        String s = meta == -1 ? itm.getRegistryName().toString() : itm.getRegistryName() + "." + meta;
        ModelLoader.setCustomModelResourceLocation(itm, meta == -1 ? 0 : meta, new ModelResourceLocation(s, "normal"));
    }

    public static void setModelLocation(Item itm)
    {
        setModelLocation(itm, -1);
    }
}
