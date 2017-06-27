package net.pearx.purmag.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.sif.SifStorageClient;

import java.util.Random;

/**
 * Created by mrAppleXZ on 27.06.17 15:38.
 */
@SideOnly(Side.CLIENT)
public class PurMagClient
{
    public static final PurMagClient INSTANCE = new PurMagClient();

    public SifStorageClient sif_storage = new SifStorageClient();
}
