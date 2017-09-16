package ru.pearx.purmag.common.tiles;

import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.tesrs.TESRMicroscope;
import ru.pearx.purmag.common.Utils;

/**
 * Created by mrAppleXZ on 08.04.17 21:04.
 */
public class TileRegistry
{
    public static void register()
    {
        GameRegistry.registerTileEntity(TileTranslationDesk.class, Utils.getResourceLocation("translation_desk").toString());
        GameRegistry.registerTileEntity(TileSingleSip.class, Utils.getResourceLocation("single_sip").toString());
        GameRegistry.registerTileEntity(TileWallIfTablet.class, Utils.getResourceLocation("wall_if_tablet").toString());
        GameRegistry.registerTileEntity(TileMicroscope.class, Utils.getResourceLocation("microscope").toString());
        GameRegistry.registerTileEntity(TileCodeStorage.class, Utils.getResourceLocation("code_storage").toString());
    }

    @SideOnly(Side.CLIENT)
    public static void registerClient()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileMicroscope.class, new TESRMicroscope());
    }
}
