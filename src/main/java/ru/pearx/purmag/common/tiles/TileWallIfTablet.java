package ru.pearx.purmag.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraftforge.common.util.Constants;
import ru.pearx.libmc.common.nbt.NBTTagCompoundBuilder;
import ru.pearx.libmc.common.tiles.TileSyncable;

/*
 * Created by mrAppleXZ on 09.07.17 10:23.
 */
public class TileWallIfTablet extends TileSyncable
{
    private int tier;

    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier, boolean sync)
    {
        this.tier = tier;
        markDirty();

        if (sync)
            sendUpdates(new NBTTagCompoundBuilder().setInteger("tier", tier).build(), null);
    }

    @Override
    public void readCustomData(NBTTagCompound tag)
    {
        if (tag.hasKey("tier", Constants.NBT.TAG_INT))
            setTier(tag.getInteger("tier"), false);
    }

    @Override
    public void writeCustomData(NBTTagCompound tag)
    {
        tag.setInteger("tier", getTier());
    }
}
