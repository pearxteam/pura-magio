package ru.pearx.purmag.common.sif;

import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.sif.SifStorageClient;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.sif.net.CPacketSyncSif;

/**
 * Created by mrAppleXZ on 27.06.17 16:37.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID)
public class SifEvents
{
    @SubscribeEvent
    public static void onAttach(AttachCapabilitiesEvent<Chunk> e)
    {
        e.addCapability(CapabilityRegistry.SIF_STORAGE_NAME, new SifStorageProvider(e.getObject().getWorld().isRemote ? new SifStorageClient() : new SifStorageServer(e.getObject())));
    }

    @SubscribeEvent
    public static void onChunkWatch(ChunkWatchEvent.Watch e)
    {
        try
        {
            Chunk ch = e.getChunkInstance();
            if(ch == null)
                ch = e.getPlayer().world.getChunkFromChunkCoords(e.getChunk().x, e.getChunk().z);
            NetworkManager.sendTo(new CPacketSyncSif(ch.x, ch.z, ch.getCapability(CapabilityRegistry.SIF_STORAGE, null).getPower()), e.getPlayer());
        }
        catch (Exception ex)
        {
            PurMag.INSTANCE.log.error("Can't sync the SIF chunk data with player!", ex);
        }
    }
}
