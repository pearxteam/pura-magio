package ru.pearx.purmag.common.sif;

import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.pearx.libmc.common.GlobalChunkPos;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.CPacketDesyncSif;
import ru.pearx.purmag.common.networking.packets.CPacketSyncSif;

/**
 * Created by mrAppleXZ on 27.06.17 16:37.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID)
public class SifEvents
{
    @SubscribeEvent
    public static void onChunkDataLoad(ChunkDataEvent.Load e)
    {
        try
        {
            PurMag.INSTANCE.sif_storage.set(GlobalChunkPos.fromChunk(e.getChunk()), e.getData().getFloat("sif_power"));
        }
        catch(Exception ex)
        {
            PurMag.INSTANCE.log.error("Can't load the SIF chunk data!", ex);
        }
    }

    @SubscribeEvent
    public static void onChunkDataSave(ChunkDataEvent.Save e)
    {
        try
        {
            e.getData().setFloat("sif_power", PurMag.INSTANCE.sif_storage.get(GlobalChunkPos.fromChunk(e.getChunk())));
        }
        catch(Exception ex)
        {
            PurMag.INSTANCE.log.error("Can't save the SIF chunk data!", ex);
        }
    }

    @SubscribeEvent
    public static void onChunkUnload(ChunkEvent.Unload e)
    {
        try
        {
            PurMag.INSTANCE.sif_storage.remove(GlobalChunkPos.fromChunk(e.getChunk()));
        }
        catch(Exception ex)
        {
            PurMag.INSTANCE.log.error("Can't remove the SIP chunk data on chunk unload!", ex);
        }
    }

    @SubscribeEvent
    public static void onChunkWatch(ChunkWatchEvent.Watch e)
    {
        try
        {
            GlobalChunkPos pos = new GlobalChunkPos(e.getChunk().x, e.getChunk().z, e.getPlayer().dimension);
            NetworkManager.sendTo(new CPacketSyncSif(pos, PurMag.INSTANCE.sif_storage.get(pos)), e.getPlayer());
        }
        catch(Exception ex)
        {
            PurMag.INSTANCE.log.error("Can't sync the SIF chunk data with player!", ex);
        }
    }

    @SubscribeEvent
    public static void onChunkUnWatch(ChunkWatchEvent.UnWatch e)
    {
        try
        {
            GlobalChunkPos pos = new GlobalChunkPos(e.getChunk().x, e.getChunk().z, e.getPlayer().dimension);
            NetworkManager.sendTo(new CPacketDesyncSif(pos), e.getPlayer());
        }
        catch(Exception ex)
        {
            PurMag.INSTANCE.log.error("Can't desync the SIF chunk data with player!", ex);
        }
    }
}
