package net.pearx.purmag.server;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.common.CommonProxy;
import net.pearx.purmag.common.sif.SifStorage;
import net.pearx.purmag.server.sif.SifStorageServer;

/**
 * Created by mrAppleXZ on 08.04.17 21:06.
 */
@SideOnly(Side.SERVER)
public class ServerProxy extends CommonProxy
{
    private SifStorageServer storage = new SifStorageServer();

    @Override
    public SifStorage getSifStorage()
    {
        return storage;
    }
}
