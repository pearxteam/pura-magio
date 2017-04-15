package net.pearx.purmag.tiles;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.pearx.purmag.PurMag;

/**
 * Created by mrAppleXZ on 08.04.17 21:04.
 */
public class TileRegistry
{
    public static void setup()
    {
        GameRegistry.registerTileEntity(TileCrystal.class, PurMag.ModId + "_crystal");

        //ModelLoader.setCustomModelResourceLocation(ItemRegistry.crystal, 0, new ModelResourceLocation(ItemRegistry.crystal.getRegistryName(), "inventory"));
    }
}
