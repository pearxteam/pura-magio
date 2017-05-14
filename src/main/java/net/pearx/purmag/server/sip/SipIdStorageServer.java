package net.pearx.purmag.server.sip;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.common.networking.NetworkManager;
import net.pearx.purmag.common.networking.packets.CPacketCleanSipIds;
import net.pearx.purmag.common.networking.packets.CPacketSyncSipIds;
import net.pearx.purmag.common.sip.SipIdStorage;

/**
 * Created by mrAppleXZ on 13.05.17 20:07.
 */
@SideOnly(Side.SERVER)
public class SipIdStorageServer extends SipIdStorage
{
    @Override
    public void sync(EntityPlayerMP p)
    {
        super.sync(p);
        NetworkManager.sendTo(new CPacketSyncSipIds(getMap()), p);
    }

    @Override
    public void desync(EntityPlayerMP p)
    {
        super.desync(p);
        NetworkManager.sendTo(new CPacketCleanSipIds(), p);
    }
}
