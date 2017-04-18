package net.pearx.purmag.infofield;

/**
 * Created by mrAppleXZ on 15.04.17 21:07.
 */
public class IFEntry
{
    private String id;
    private int tier;

    public IFEntry(String id, int tier)
    {
        setId(id);
        setTier(tier);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier)
    {
        this.tier = tier;
    }
}
