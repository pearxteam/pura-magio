package net.pearx.purmag.common.worldgen;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 04.05.17 16:08.
 */
public class WGCrystalsEntry
{
    private WGCrystalsType type;
    private String sip;
    private BiomeDictionary.Type[] biomes;
    private List<Integer> blacklistedDims;

    public WGCrystalsEntry(WGCrystalsType type, String sip, List<Integer> blacklistedDims, BiomeDictionary.Type... biomes)
    {
        setType(type);
        setSip(sip);
        if(blacklistedDims == null)
            blacklistedDims = new ArrayList<>();
        setBlacklistedDims(blacklistedDims);
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

    public BiomeDictionary.Type[] getBiomes()
    {
        return biomes;
    }

    public void setBiomes(BiomeDictionary.Type[] biomes)
    {
        this.biomes = biomes;
    }

    public List<Integer> getBlacklistedDims()
    {
        return blacklistedDims;
    }

    public void setBlacklistedDims(List<Integer> blacklistedDims)
    {
        this.blacklistedDims = blacklistedDims;
    }
}
