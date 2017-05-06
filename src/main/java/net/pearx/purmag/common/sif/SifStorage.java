package net.pearx.purmag.common.sif;


import net.minecraft.world.chunk.Chunk;
import net.pearx.purmag.PurMag;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by mrAppleXZ on 06.05.17 17:49.
 */
public class SifStorage
{
    public static SifStorage INSTANCE = new SifStorage();

    public Map<Chunk, Float> values = new WeakHashMap<>();

    public void setPower(Chunk chunk, float f)
    {
        values.put(chunk, f);
    }

    public float getPower(Chunk chunk)
    {
        if(!values.containsKey(chunk))
            setPower(chunk, PurMag.rand.nextFloat());
        return values.get(chunk);
    }
}
