package ru.pearx.purmag.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import ru.pearx.libmc.common.nbt.NBTTagCompoundBuilder;
import ru.pearx.libmc.common.nbt.serialization.NBTSerializer;
import ru.pearx.libmc.common.tiles.syncable.TileSyncable;
import ru.pearx.libmc.common.tiles.syncable.TileSyncableComposite;
import ru.pearx.libmc.common.tiles.syncable.WriteTarget;
import ru.pearx.purmag.PurMag;

/*
 * Created by mrAppleXZ on 09.07.17 8:48.
 */
public class TileSingleSip extends TileSyncableComposite
{
    public static final String NBT_SIP_TYPE = "sip_type";

    private String type = PurMag.INSTANCE.getSipRegistry().getDefaultType().getName();

    public TileSingleSip()
    {
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_SIP_TYPE, String.class, this::setType, this::getType));
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
        markDirty();
    }

    public void setTypeAndSync(EntityPlayer p, String type)
    {
        setType(type);
        sendUpdates(p, NBT_SIP_TYPE);
    }
}
