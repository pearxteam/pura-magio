package ru.pearx.purmag.common.config;

import java.util.List;

/**
 * Created by mrAppleXZ on 20.05.17 10:55.
 */
public class ConfigOregenEntry
{
    public boolean generate;
    public int minY, maxY, minVeinSize, maxVeinSize, minVeins, maxVeins;
    public float chance;
    public List<Integer> dimList;
    public boolean dimListWhitelist;

    public ConfigOregenEntry()
    {
    }
}
