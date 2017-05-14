package net.pearx.purmag.common.sip;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.DimensionManager;
import net.pearx.purmag.PurMag;
import org.lwjgl.input.Mouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by mrAppleXZ on 13.05.17 9:30.
 */
public class SipWorldData extends WorldSavedData
{
    public static final String NAME = "sip_id_map";

    private int freeId = 0;
    public Map<String, Integer> ids = new HashMap<>();

    public SipWorldData(String name)
    {
        super(name);
        for(SipType t : PurMag.instance.sip.types)
        {
            ids.put(t.getName(), freeId);
            freeId++;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        if(nbt.hasKey("lastFreeId"))
            freeId = nbt.getInteger("lastFreeId");
        for(SipType t : PurMag.instance.sip.types)
        {
            if(nbt.hasKey(t.getName()))
                ids.put(t.getName(), nbt.getInteger(t.getName()));
            else
            {
                ids.put(t.getName(), freeId);
                freeId++;
            }
        }
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        for(Map.Entry<String, Integer> set : ids.entrySet())
        {
            compound.setInteger(set.getKey(), set.getValue());
        }
        compound.setInteger("lastFreeId", freeId);
        return compound;
    }
}
