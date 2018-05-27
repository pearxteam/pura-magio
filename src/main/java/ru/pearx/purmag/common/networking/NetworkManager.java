package ru.pearx.purmag.common.networking;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.GuiHandler;
import ru.pearx.purmag.common.networking.packets.*;
import ru.pearx.purmag.common.networking.packets.code_storage.CPacketUnlockResponse;
import ru.pearx.purmag.common.networking.packets.code_storage.SPacketLock;
import ru.pearx.purmag.common.networking.packets.code_storage.SPacketUnlock;
import ru.pearx.purmag.common.networking.packets.microscope.CPacketCheckMicroscopeResearchResponse;
import ru.pearx.purmag.common.networking.packets.microscope.SPacketCheckMicroscopeResearch;
import ru.pearx.purmag.common.sif.net.CPacketSyncSif;

/**
 * Created by mrAppleXZ on 23.04.17 11:23.
 */
public class NetworkManager
{
    private static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(PurMag.MODID);
    private static int id = 0;

    public static void setup()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(PurMag.INSTANCE, new GuiHandler());

        INSTANCE.registerMessage(CPacketSyncEntryStore.Handler.class, CPacketSyncEntryStore.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(CPacketDisplayMessage.Handler.class, CPacketDisplayMessage.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(CPacketSyncSif.Handler.class, CPacketSyncSif.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(SPacketUseSipAmulet.Handler.class, SPacketUseSipAmulet.class, id++, Side.SERVER);
        INSTANCE.registerMessage(CPacketSpawnSipParticle.Handler.class, CPacketSpawnSipParticle.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(SPacketDoneTranslation.Handler.class, SPacketDoneTranslation.class, id++, Side.SERVER);
        INSTANCE.registerMessage(CPacketSpawnParticle.Handler.class, CPacketSpawnParticle.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(CPacketSyncAura.Handler.class, CPacketSyncAura.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(SPacketLock.Handler.class, SPacketLock.class, id++, Side.SERVER);
        INSTANCE.registerMessage(SPacketUnlock.Handler.class, SPacketUnlock.class, id++, Side.SERVER);
        INSTANCE.registerMessage(CPacketUnlockResponse.Handler.class, CPacketUnlockResponse.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(SPacketCheckMicroscopeResearch.Handler.class, SPacketCheckMicroscopeResearch.class, id++, Side.SERVER);
        INSTANCE.registerMessage(CPacketCheckMicroscopeResearchResponse.Handler.class, CPacketCheckMicroscopeResearchResponse.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(CPacketSpawnMultiblockParticles.Handler.class, CPacketSpawnMultiblockParticles.class, id++, Side.CLIENT);
    }

    public static void sendTo(IMessage msg, EntityPlayerMP p)
    {
        INSTANCE.sendTo(msg, p);
    }

    public static void sendToAllAround(IMessage msg, int x, int y, int z, int dim, int radius)
    {
        INSTANCE.sendToAllAround(msg, new NetworkRegistry.TargetPoint(dim, x + .5, y + .5, z + .5, radius));
    }


    public static void sendToServer(IMessage msg)
    {
        INSTANCE.sendToServer(msg);
    }

    public static void sendToAll(IMessage msg)
    {
        INSTANCE.sendToAll(msg);
    }
}
