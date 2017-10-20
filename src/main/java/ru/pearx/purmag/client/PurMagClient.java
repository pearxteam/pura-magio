package ru.pearx.purmag.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.microscope.MicroscopeDataBuilder;
import ru.pearx.purmag.client.sif.SifStorageClient;

import java.util.Random;

/**
 * Created by mrAppleXZ on 27.06.17 15:38.
 */
@SideOnly(Side.CLIENT)
public enum PurMagClient
{
    INSTANCE;
    public SifStorageClient sif_storage = new SifStorageClient();

    private MicroscopeDataBuilder microscopeDataBuilder = new MicroscopeDataBuilder();

    public MicroscopeDataBuilder getMicroscopeDataBuilder()
    {
        return microscopeDataBuilder;
    }
}
