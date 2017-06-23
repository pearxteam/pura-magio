package net.pearx.purmag.common.tiles;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.pearx.purmag.common.Utils;

/**
 * Created by mrAppleXZ on 08.04.17 21:04.
 */
public class TileRegistry
{
    public static void setup()
    {
        GameRegistry.registerTileEntity(TileTranslationDesk.class, Utils.getRegistryName("translation_desk").toString());
    }
}
