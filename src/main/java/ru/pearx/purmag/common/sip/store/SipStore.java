package ru.pearx.purmag.common.sip.store;

import net.minecraft.nbt.NBTTagCompound;
import ru.pearx.purmag.PurMag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mrAppleXZ on 23.05.17 9:48.
 */
public class SipStore implements ISipStore
{
    private HashMap<String, Integer> sip = new HashMap<>();
    private int max;

    public SipStore(int max)
    {
        this.max = max;
    }

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
        return max;
    }

    @Override
    public boolean canAdd(String type, int count)
    {
        return get(type) < getMax(type);
    }

    @Override
    public int add(String type, int count)
    {
        int i = get(type) + count;
        if(i > getMax(type))
        {
            sip.put(type, getMax(type));
            return i - getMax(type);
        }
        sip.put(type, i);
        return 0;
    }

    @Override
    public boolean canRemove(String type, int count)
    {
        int i = get(type) - count;
        return i >= 0;
    }

    @Override
    public void remove(String type, int count)
    {
        sip.put(type, get(type) - count);
    }

    @Override
    public List<String> getAllowedTypes()
    {
        return PurMag.INSTANCE.sip.getAllowedValues();
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
    public void clear()
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
