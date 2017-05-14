package net.pearx.purmag.common.sip;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.DimensionManager;
import net.pearx.purmag.PurMag;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrAppleXZ on 13.05.17 20:01.
 */
public class SipIdStorage
{
    private Map<String, Integer> ids = new HashMap<>();

    public void loadMap(Map<String, Integer> value)
    {
        ids = value;
    }

    public Map<String, Integer> getMap()
    {
        return ids;
    }

    public void cleanMap()
    {
        ids = null;
    }

    public void sync(EntityPlayerMP p)
    {

    }

    public void desync(EntityPlayerMP p)
    {

    }

    public static void onServerLoad()
    {
        World w = DimensionManager.getWorld(0);
        MapStorage stor = w.getMapStorage();
        SipWorldData data = (SipWorldData) stor.getOrLoadData(SipWorldData.class, SipWorldData.NAME);
        if (data == null)
        {
            data = new SipWorldData(SipWorldData.NAME);
            stor.setData(SipWorldData.NAME, data);
            data.markDirty();
        }
        PurMag.proxy.getSipIdStorage().loadMap(data.ids);
    }

    public static void onServerShutdown()
    {
        PurMag.proxy.getSipIdStorage().cleanMap();
    }
}
