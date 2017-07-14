package ru.pearx.purmag.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
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

    public void setTier(int tier)
    {
        this.tier = tier;
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("tier", getTier());
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        if(compound.hasKey("tier"))
            setTier(compound.getInteger("tier"));
    }
}
