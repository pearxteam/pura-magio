package ru.pearx.purmag.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import ru.pearx.libmc.PXLMC;
import ru.pearx.libmc.common.tiles.TileMultiblockMaster;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.CPacketSpawnMultiblockParticles;

import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 23.02.18 14:51.
 */
public class TilePMMultiblockMaster extends TileMultiblockMaster
{
    @Override
    public void postForm(@Nullable EntityPlayer p)
    {
        NetworkManager.sendToAllAround(new CPacketSpawnMultiblockParticles(getZeroPos(), getMultiblock().getRegistryName(), getRotation()), pos.getX(), pos.getY(), pos.getZ(), getWorld().provider.getDimension(), 256);
    }
}
