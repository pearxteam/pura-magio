package net.pearx.purmag.server.sif;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerChunkMap;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.common.GlobalChunkPos;
import net.pearx.purmag.common.networking.NetworkManager;
import net.pearx.purmag.common.networking.packets.CPacketSyncSif;
import net.pearx.purmag.common.sif.SifStorage;

/**
 * Created by mrAppleXZ on 10.05.17 20:02.
 */
@SideOnly(Side.SERVER)
public class SifStorageServer extends SifStorage
{
    @Override
    public void setPower(GlobalChunkPos chunk, float f)
    {
        super.setPower(chunk, f);
        PlayerChunkMapEntry entr = DimensionManager.getWorld(chunk.getDimension()).getPlayerChunkMap().getEntry(chunk.getX(), chunk.getZ());
        if(entr != null)
        {
            for (EntityPlayerMP p : entr.players)
            {
                NetworkManager.sendTo(new CPacketSyncSif(chunk, false, f), p);
            }
        }
    }
}
