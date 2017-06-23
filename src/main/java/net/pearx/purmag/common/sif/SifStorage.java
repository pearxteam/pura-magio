package net.pearx.purmag.common.sif;


import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.GlobalChunkPos;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrAppleXZ on 06.05.17 17:49.
 */
public class SifStorage
{
    public Map<GlobalChunkPos, Float> values = new HashMap<>();

    public void setPower(GlobalChunkPos chunk, float f)
    {
        values.put(chunk, f);
    }

    public void removeChunk(GlobalChunkPos pos)
    {
        values.remove(pos);
    }

    public float getPower(GlobalChunkPos chunk)
    {
        if(!values.containsKey(chunk))
        {
            int main;
            float f = PurMag.rand.nextFloat();
            if(PurMag.rand.nextFloat() <= 0.01f)
                main = -1;
            else
            {
                float ff = PurMag.rand.nextFloat();
                if(ff <= 0.02f)
                    main = 2;
                else if(ff <= 0.20f)
                    main = 1;
                else
                    main = 0;

            }
            setPower(chunk, main + f);
        }
        return values.get(chunk);
    }
}
