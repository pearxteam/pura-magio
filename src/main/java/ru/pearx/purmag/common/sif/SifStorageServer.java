package ru.pearx.purmag.common.sif;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.sif.net.CPacketSyncSif;

import java.util.Random;

/**
 * Created by mrAppleXZ on 27.06.17 15:36.
 */
public class SifStorageServer extends SifStorage
{
    private Chunk chunk;
    private boolean isSet;

    public SifStorageServer(Chunk chunk)
    {
        this.chunk = chunk;
    }

    @Override
    public void setPower(float value)
    {
        isSet = true;
        super.setPower(value);
        World w = chunk.getWorld();
        if(w instanceof WorldServer)
        {
            PlayerChunkMapEntry map = ((WorldServer) w).getPlayerChunkMap().getEntry(chunk.x, chunk.z);
            if (map != null)
            {
                for (EntityPlayerMP p : map.players)
                {
                    NetworkManager.sendTo(new CPacketSyncSif(chunk.x, chunk.z, value), p);
                }
            }
        }
    }

    @Override
    public float getPower()
    {
        if(!isSet)
        {
            long worldSeed = chunk.getWorld().getSeed();
            Random rand = new Random(worldSeed);
            long xSeed = rand.nextLong() >> 2 + 1L;
            long zSeed = rand.nextLong() >> 2 + 1L;
            rand.setSeed((xSeed * chunk.x + zSeed * chunk.z) ^ worldSeed);

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
            setPower(main + f);
        }
        return super.getPower();
    }

    @Override
    public void markDirty()
    {
        chunk.markDirty();
    }
}