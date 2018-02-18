package ru.pearx.purmag.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import ru.pearx.libmc.common.nbt.NBTTagCompoundBuilder;
import ru.pearx.libmc.common.tiles.TileSyncable;
import ru.pearx.purmag.PurMag;

/*
 * Created by mrAppleXZ on 09.07.17 8:48.
 */
public class TileSingleSip extends TileSyncable
{
    private String type = PurMag.INSTANCE.getSipRegistry().getDefaultType().getName();

    public String getType()
    {
        return type;
    }

    public void setType(String type, boolean sync)
    {
        this.type = type;
        markDirty();
        if (sync)
            sendUpdates(new NBTTagCompoundBuilder().setString("sip_type", type).build());
    }

    @Override
    public void readCustomData(NBTTagCompound tag)
    {
        if (tag.hasKey("sip_type"))
            setType(tag.getString("sip_type"), false);
    }

    @Override
    public void writeCustomData(NBTTagCompound tag)
    {
        tag.setString("sip_type", getType());
    }
}
