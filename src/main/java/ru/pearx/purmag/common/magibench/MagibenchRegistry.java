package ru.pearx.purmag.common.magibench;

import ru.pearx.purmag.common.sip.store.ISipStore;
import ru.pearx.purmag.common.sip.store.SipStore;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by mrAppleXZ on 30.10.17 18:19.
 */
public enum MagibenchRegistry
{
    INSTANCE;

    public static abstract class Tier
    {
        private int tier;
        private int width;
        private int height;

        public int getTier()
        {
            return tier;
        }

        public int getWidth()
        {
            return width;
        }

        public int getHeight()
        {
            return height;
        }
    }

    private List<Tier> tiers = new ArrayList<>();

    public List<Tier> getTiers()
    {
        return tiers;
    }

    public Tier getTier(int tier)
    {
        for(Tier t : getTiers())
            if(t.getTier() == tier)
                return t;
    }
}
