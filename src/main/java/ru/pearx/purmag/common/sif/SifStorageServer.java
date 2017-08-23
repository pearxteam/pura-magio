package ru.pearx.purmag.common.sif;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import ru.pearx.libmc.common.GlobalChunkPos;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.CPacketDesyncSif;
import ru.pearx.purmag.common.networking.packets.CPacketSyncSif;

import java.util.Random;

/**
 * Created by mrAppleXZ on 27.06.17 15:36.
 */
public class SifStorageServer extends SifStorage
{
    @Override
    public void set(GlobalChunkPos pos, float value)
    {
        if (loaded.containsKey(pos))
            if (loaded.get(pos).equals(value))
                return;
        loaded.put(pos, value);
        WorldServer wrld = DimensionManager.getWorld(pos.getDimension());
        wrld.getChunkFromChunkCoords(pos.getX(), pos.getZ()).markDirty();
        PlayerChunkMapEntry map = wrld.getPlayerChunkMap().getEntry(pos.getX(), pos.getZ());
        if (map != null)
        {
            for (EntityPlayerMP p : map.players)
            {
                NetworkManager.sendTo(new CPacketSyncSif(pos, value), p);
            }
        }
    }

    @Override
    public void remove(GlobalChunkPos pos)
    {
        if (!loaded.containsKey(pos))
            return;
        loaded.remove(pos);
        PlayerChunkMapEntry map = DimensionManager.getWorld(pos.getDimension()).getPlayerChunkMap().getEntry(pos.getX(), pos.getZ());
        if (map != null)
        {
            for (EntityPlayerMP p : map.players)
            {
                NetworkManager.sendTo(new CPacketDesyncSif(pos), p);
            }
        }
    }

    @Override
    public float get(GlobalChunkPos pos)
    {
        if (!loaded.containsKey(pos))
        {
            long worldSeed = DimensionManager.getWorld(pos.getDimension()).getSeed();
            Random rand = new Random(worldSeed);
            long xSeed = rand.nextLong() >> 2 + 1L;
            long zSeed = rand.nextLong() >> 2 + 1L;
            rand.setSeed((xSeed * pos.getX() + zSeed * pos.getZ()) ^ worldSeed);

            int main;
            float f = rand.nextFloat();
            if (rand.nextFloat() <= 0.01f)
                main = -1;
            else
            {
                float ff = rand.nextFloat();
                if (ff <= 0.02f)
                    main = 2;
                else if (ff <= 0.20f)
                    main = 1;
                else
                    main = 0;

            }
            set(pos, main + f);
        }
        return loaded.get(pos);
    }
}
