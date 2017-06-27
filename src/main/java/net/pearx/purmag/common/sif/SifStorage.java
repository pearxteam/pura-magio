package net.pearx.purmag.common.sif;

import net.pearx.purmag.common.GlobalChunkPos;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrAppleXZ on 27.06.17 15:29.
 */
public class SifStorage
{
    protected Map<GlobalChunkPos, Float> loaded = new HashMap<>();

    public float get(GlobalChunkPos pos)
    {
        return loaded.get(pos);
    }

    public void set(GlobalChunkPos pos, float value)
    {
        loaded.put(pos, value);
    }

    public void remove(GlobalChunkPos pos)
    {
        loaded.remove(pos);
    }
}
