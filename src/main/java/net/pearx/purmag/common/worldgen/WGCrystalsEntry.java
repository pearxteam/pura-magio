package net.pearx.purmag.common.worldgen;

import net.minecraft.world.biome.Biome;

/**
 * Created by mrAppleXZ on 04.05.17 16:08.
 */
public class WGCrystalsEntry
{
    private WGCrystalsType type;
    private String sip;
    private Biome[] biomes;

    public WGCrystalsEntry(WGCrystalsType type, String sip, Biome... biomes)
    {
        setType(type);
        setSip(sip);
        setBiomes(biomes);
    }

    public WGCrystalsType getType()
    {
        return type;
    }

    public void setType(WGCrystalsType type)
    {
        this.type = type;
    }

    public String getSip()
    {
        return sip;
    }

    public void setSip(String sip)
    {
        this.sip = sip;
    }

    public Biome[] getBiomes()
    {
        return biomes;
    }

    public void setBiomes(Biome[] biomes)
    {
        this.biomes = biomes;
    }
}
