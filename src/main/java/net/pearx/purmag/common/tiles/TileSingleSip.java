package net.pearx.purmag.common.tiles;

import net.minecraft.nbt.NBTTagCompound;

/*
 * Created by mrAppleXZ on 09.07.17 8:48.
 */
public class TileSingleSip extends TileSyncable
{
    private String type;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setString("sip_type", getType());
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        if(compound.hasKey("sip_type"))
            setType(compound.getString("sip_type"));
    }
}
