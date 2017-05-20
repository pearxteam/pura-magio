package net.pearx.purmag.common.config;

import java.util.List;

/**
 * Created by mrAppleXZ on 20.05.17 10:55.
 */
public class ConfigOregenEntry
{
    public boolean generate;
    public int minY, maxY, minVeinSize, maxVeinSize;
    public float chance;
    public List<Integer> dimList;
    public boolean dimListWhitelist;

    public ConfigOregenEntry(boolean generate, int minY, int maxY, int minVeinSize, int maxVeinSize, float chance, List<Integer> dimList, boolean dimListWhitelist)
    {
        this.generate = generate;
        this.minY = minY;
        this.maxY = maxY;
        this.minVeinSize = minVeinSize;
        this.maxVeinSize = maxVeinSize;
        this.chance = chance;
        this.dimList = dimList;
        this.dimListWhitelist = dimListWhitelist;
    }

    public ConfigOregenEntry()
    {
    }
}
