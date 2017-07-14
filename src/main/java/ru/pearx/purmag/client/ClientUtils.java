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
    public static ModelResourceLocation getModelResourceLocation(String s)
    {
        return new ModelResourceLocation(PurMag.MODID + ":" + s, "normal");
    }

    public static void setModelLocation(Item itm, int meta, String suffix)
    {
        ModelLoader.setCustomModelResourceLocation(itm, meta, new ModelResourceLocation(itm.getRegistryName() + suffix, "normal"));
    }

    public static void setModelLocation(Item itm)
    {
        setModelLocation(itm, 0, "");
    }
}
