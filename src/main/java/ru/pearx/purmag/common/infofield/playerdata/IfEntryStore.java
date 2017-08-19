package ru.pearx.purmag.common.infofield.playerdata;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.DisplayMessage;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.CPacketDisplayMessage;
import ru.pearx.purmag.common.networking.packets.CPacketSyncEntryStore;

import java.util.HashMap;

/**
 * Created by mrAppleXZ on 22.04.17 17:30.
 */
public class IfEntryStore implements IIfEntryStore
{
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
        entries.put(id, steps);
    }

    @Override
    public boolean isFullyUnlocked(String id)
    {
        return getSteps(id) >= PurMag.INSTANCE.getIfRegistry().getEntry(id).getSteps().size();
    }

    @Override
    public void sync(EntityPlayerMP p)
    {
        NetworkManager.sendTo(new CPacketSyncEntryStore(serializeNBT()), p);
    }

    @Override
    public void sync(EntityPlayerMP p, String res)
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger(res, entries.get(res));
        NetworkManager.sendTo(new CPacketSyncEntryStore(tag), p);
    }

    @Override
    public void unlockStepAndSync(String id, EntityPlayerMP p)
    {
        setSteps(id, getSteps(id) + 1);
        sync(p, id);
        NetworkManager.sendTo(new CPacketDisplayMessage(new DisplayMessage("%0", "%1", "if_entry:" + id, "i18n:if_step.unlocked.text")), p);
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
