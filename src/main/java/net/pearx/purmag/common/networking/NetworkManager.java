package net.pearx.purmag.common.networking;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.networking.packets.SPacketSyncEntryStore;

/**
 * Created by mrAppleXZ on 23.04.17 11:23.
 */
public class NetworkManager
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(PurMag.ModId);
    private static int id = 0;

    public static void setup()
    {
        INSTANCE.registerMessage(SPacketSyncEntryStore.Handler.class, SPacketSyncEntryStore.class, id++, Side.CLIENT);
    }

    public static void sendTo(IMessage msg, EntityPlayer p)
    {
        if(p instanceof EntityPlayerMP)
        {
            INSTANCE.sendTo(msg, (EntityPlayerMP) p);
        }
    }
}