package ru.pearx.purmag.common.tiles;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.tesrs.TESRCodeStorage;
import ru.pearx.purmag.client.tesrs.TESRMicroscope;
import ru.pearx.purmag.common.Utils;

/**
 * Created by mrAppleXZ on 08.04.17 21:04.
 */
public class TileRegistry
{
    public static void register()
    {
        GameRegistry.registerTileEntity(TileTranslationDesk.class, Utils.gRL("translation_desk").toString());
        GameRegistry.registerTileEntity(TileSingleSip.class, Utils.gRL("single_sip").toString());
        GameRegistry.registerTileEntity(TileWallIfTablet.class, Utils.gRL("wall_if_tablet").toString());
        GameRegistry.registerTileEntity(TileMicroscope.class, Utils.gRL("microscope").toString());
        GameRegistry.registerTileEntity(TileCodeStorage.class, Utils.gRL("code_storage").toString());
        GameRegistry.registerTileEntity(TileMagibench.class, Utils.gRL("magibench").toString());
    }

    @SideOnly(Side.CLIENT)
    public static void registerClient()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileMicroscope.class, new TESRMicroscope());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCodeStorage.class, new TESRCodeStorage());
    }
}
