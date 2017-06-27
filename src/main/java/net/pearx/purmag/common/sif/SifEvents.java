package net.pearx.purmag.common.sif;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.GlobalChunkPos;
import net.pearx.purmag.common.networking.NetworkManager;
import net.pearx.purmag.common.networking.packets.CPacketDesyncSif;
import net.pearx.purmag.common.networking.packets.CPacketSyncSif;

import java.util.Random;

/**
 * Created by mrAppleXZ on 27.06.17 16:37.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID)
public class SifEvents
{
    @SubscribeEvent
    public static void onChunkDataLoad(ChunkDataEvent.Load e)
    {
        PurMag.INSTANCE.sif_storage.set(GlobalChunkPos.fromChunk(e.getChunk()), e.getData().getFloat("sif_power"));
    }

    @SubscribeEvent
    public static void onChunkDataSave(ChunkDataEvent.Save e)
    {
        e.getData().setFloat("sif_power", PurMag.INSTANCE.sif_storage.get(GlobalChunkPos.fromChunk(e.getChunk())));
    }

    @SubscribeEvent
    public static void onChunkUnload(ChunkEvent.Unload e)
    {
        PurMag.INSTANCE.sif_storage.remove(GlobalChunkPos.fromChunk(e.getChunk()));
    }

    @SubscribeEvent
    public static void onChunkWatch(ChunkWatchEvent.Watch e)
    {
        GlobalChunkPos pos = new GlobalChunkPos(e.getChunk().x, e.getChunk().z, e.getPlayer().dimension);
        NetworkManager.sendTo(new CPacketSyncSif(pos, PurMag.INSTANCE.sif_storage.get(pos)), e.getPlayer());
    }

    @SubscribeEvent
    public static void onChunkUnWatch(ChunkWatchEvent.UnWatch e)
    {
        GlobalChunkPos pos = new GlobalChunkPos(e.getChunk().x, e.getChunk().z, e.getPlayer().dimension);
        NetworkManager.sendTo(new CPacketDesyncSif(pos), e.getPlayer());
    }
}
