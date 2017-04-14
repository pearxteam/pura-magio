package net.pearx.purmag.registries;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.pearx.purmag.PurmagCore;
import net.pearx.purmag.tiles.TileCrystal;

/**
 * Created by mrAppleXZ on 08.04.17 21:04.
 */
public class TileRegistry
{
    public static void setup()
    {
        GameRegistry.registerTileEntity(TileCrystal.class, PurmagCore.ModId + "_crystal");

        //ModelLoader.setCustomModelResourceLocation(ItemRegistry.crystal, 0, new ModelResourceLocation(ItemRegistry.crystal.getRegistryName(), "inventory"));
    }
}
