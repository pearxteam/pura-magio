package ru.pearx.purmag.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.PurMag;

/**
 * Created by mrAppleXZ on 08.04.17 18:53.
 */
@SideOnly(Side.CLIENT)
public class ClientUtils
{
    public static void setModelLocationDirBased(Item itm, int meta, String file)
    {
        ModelLoader.setCustomModelResourceLocation(itm, meta, new ModelResourceLocation(itm.getRegistryName() + "/" + file, "normal"));
    }

    public static void setModelLocationDirBased(Item itm, int meta)
    {
        setModelLocationDirBased(itm, meta, Integer.toString(meta));
    }

    public static void setModelLocationSimple(Item itm)
    {
        setModelLocationSimple(itm, 0, "");
    }

    public static void setModelLocationSimple(Item itm, int meta)
    {
        setModelLocationSimple(itm, meta, "");
    }

    public static void setModelLocationSimple(Item itm, int meta, String suffix)
    {
        ModelLoader.setCustomModelResourceLocation(itm, meta, new ModelResourceLocation(itm.getRegistryName() + suffix, "normal"));
    }
}
