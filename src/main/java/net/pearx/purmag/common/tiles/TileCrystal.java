package net.pearx.purmag.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.pearx.purmag.common.sip.SipTypeRegistry;

/**
 * Created by mrAppleXZ on 08.04.17 17:56.
 */
public class TileCrystal extends SipTileEntity
{

    private String type = SipTypeRegistry.def;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        if(compound.hasKey("type"))
            setType(compound.getString("type"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setString("type", getType());
        return compound;
    }

}
