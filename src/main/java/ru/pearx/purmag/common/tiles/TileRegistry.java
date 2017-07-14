package ru.pearx.purmag.common.tiles;

import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.pearx.purmag.common.Utils;

/**
 * Created by mrAppleXZ on 08.04.17 21:04.
 */
public class TileRegistry
{
    public static void register()
    {
        GameRegistry.registerTileEntity(TileTranslationDesk.class, Utils.getRegistryName("translation_desk").toString());
        GameRegistry.registerTileEntity(TileSingleSip.class, Utils.getRegistryName("single_sip").toString());
        GameRegistry.registerTileEntity(TileWallIfTablet.class, Utils.getRegistryName("wall_if_tablet").toString());
    }
}
