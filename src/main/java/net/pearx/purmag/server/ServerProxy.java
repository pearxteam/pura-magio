package net.pearx.purmag.server;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.common.CommonProxy;
import net.pearx.purmag.common.sif.SifStorage;
import net.pearx.purmag.common.sip.SipIdStorage;
import net.pearx.purmag.server.sif.SifStorageServer;
import net.pearx.purmag.server.sip.SipIdStorageServer;

/**
 * Created by mrAppleXZ on 08.04.17 21:06.
 */
@SideOnly(Side.SERVER)
public class ServerProxy extends CommonProxy
{
    private SifStorageServer sifStorage = new SifStorageServer();
    private SipIdStorageServer sipIdStorage = new SipIdStorageServer();

    @Override
    public SifStorage getSifStorage()
    {
        return sifStorage;
    }

    @Override
    public SipIdStorage getSipIdStorage()
    {
        return sipIdStorage;
    }
}
