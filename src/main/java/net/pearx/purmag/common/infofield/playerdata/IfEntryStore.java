package net.pearx.purmag.common.infofield.playerdata;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.networking.NetworkManager;
import net.pearx.purmag.common.networking.packets.SPacketSyncEntryStore;

import java.util.HashMap;

/**
 * Created by mrAppleXZ on 22.04.17 17:30.
 */
public class IfEntryStore implements IIfEntryStore
{
    //TODO: CHECK SYNCS!
    private HashMap<String, Integer> entries = new HashMap<>();

    @Override
    public HashMap<String, Integer> getMap()
    {
        return entries;
    }

    @Override
    public int getSteps(String id)
    {
        if(entries.containsKey(id))
            return entries.get(id);
        return 0;
    }

    @Override
    public void setSteps(String id, int steps)
    {
        //TODO: Syncing
        entries.put(id, steps);
    }

    @Override
    public boolean isFullyUnlocked(String id)
    {
        return getSteps(id) >= PurMag.instance.if_registry.getEntry(id).getSteps().size();
    }

    @Override
    public void sync(EntityPlayerMP p)
    {
        NetworkManager.sendTo(new SPacketSyncEntryStore(serializeNBT()), p);
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound tag = new NBTTagCompound();
        for (String entr : entries.keySet())
        {
            tag.setInteger(entr, entries.get(entr));
        }
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        for(String s : nbt.getKeySet())
        {
            setSteps(s, nbt.getInteger(s));
        }
    }
}
