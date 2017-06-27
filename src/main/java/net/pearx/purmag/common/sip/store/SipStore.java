package net.pearx.purmag.common.sip.store;

import net.minecraft.nbt.NBTTagCompound;
import net.pearx.purmag.PurMag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mrAppleXZ on 23.05.17 9:48.
 */
public class SipStore implements ISipStore
{
    private HashMap<String, Integer> sip = new HashMap<>();

    @Override
    public int get(String type)
    {
        if(sip.containsKey(type))
            return sip.get(type);
        return 0;
    }

    @Override
    public int getMax(String type)
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean canAdd(String type, int count)
    {
        int base = get(type);
        base += count;
        return base <= getMax(type);
    }

    @Override
    public void add(String type, int count)
    {
        int base = get(type);
        base += count;
        sip.put(type, base);
    }

    @Override
    public boolean canRemove(String type, int count)
    {
        int base = get(type);
        base -= count;
        return base >= 0;
    }

    @Override
    public void remove(String type, int count)
    {
        int base = get(type);
        base -= count;
        sip.put(type, base);
    }

    @Override
    public List<String> getAllowedTypes()
    {
        return PurMag.INSTANCE.sip.allowedValues;
    }

    @Override
    public Map<String, Integer> getStored()
    {
        return sip;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        for(Map.Entry<String, Integer> entr : sip.entrySet())
        {
            nbt.setInteger(entr.getKey(), entr.getValue());
        }
        return nbt;
    }

    @Override
    public void removeAll()
    {
        sip.clear();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        for(String type : nbt.getKeySet())
        {
            sip.put(type, nbt.getInteger(type));
        }
    }
}
