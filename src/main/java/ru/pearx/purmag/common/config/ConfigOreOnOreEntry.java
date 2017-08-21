package ru.pearx.purmag.common.config;

import java.util.List;

/*
 * Created by mrAppleXZ on 20.08.17 11:30.
 */
public class ConfigOreOnOreEntry
{
    public boolean generate;
    public int minY, maxY;
    public float chance;
    public List<Integer> dimList;
    public boolean dimListWhitelist;

    public ConfigOreOnOreEntry()
    {

    }
}
