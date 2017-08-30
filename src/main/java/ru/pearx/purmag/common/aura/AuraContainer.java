package ru.pearx.purmag.common.aura;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.CPacketSyncAura;

import java.util.UUID;

/*
 * Created by mrAppleXZ on 30.08.17 18:52.
 */
public class AuraContainer implements IAuraContainer
{
    private UUID uuid = null;

    @Override
    public UUID getKey()
    {
        if(uuid == null)
            uuid = UUID.randomUUID();
        return uuid;
    }

    @Override
    public void sync(EntityPlayerMP p)
    {
        NetworkManager.sendTo(new CPacketSyncAura(serializeNBT()), p);
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setUniqueId("key", getKey());
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        uuid = nbt.getUniqueId("key");
    }
}
