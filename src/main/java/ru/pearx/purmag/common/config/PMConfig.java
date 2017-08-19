package ru.pearx.purmag.common.config;

import net.minecraft.util.StringUtils;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 04.05.17 17:13.
 */
public class PMConfig
{
    //todo air and informa worldgen.
    public boolean genCrystals;
    public List<Integer> genRockCrystalsDimBlacklist;
    public ConfigOregenEntry genCrysagnetite;
    public ConfigOregenEntry genCrystallizedRedstone;
    public ConfigOregenEntry genCrystallizedGlowstone;

    public void setup(Configuration configFile)
    {
        genCrystals = configFile.getBoolean("Generate Crystals", "WORLD", true, "");
        genRockCrystalsDimBlacklist = loadIntList(configFile, "Rock Crystals Dimension Blacklist", "WORLD", new String[] {"1", "-1"}, "");

        genCrysagnetite = loadOregen(configFile, "Crysagnetite", 15, 25, 1, 3, 1, 2, 0.1f, new String[] {"-1", "1"}, false);
        genCrystallizedRedstone = loadOregen(configFile, "Crystallized Redstone", 2, 16, 2, 4, 1, 3, 0.2f, new String[] {"-1", "1"}, false);
        genCrystallizedGlowstone = loadOregen(configFile, "Crystallized Glowstone", 2, 100, 2, 4, 1, 3, 0.5f, new String[] {"-1"}, true);

        if(configFile.hasChanged())
            configFile.save();
    }

    private ConfigOregenEntry loadOregen(Configuration config, String orename, int minY, int maxY, int minVs, int maxVs, int minVeins, int maxVeins, float chance, String[] dimList, boolean dimListMode)
    {
        ConfigOregenEntry cfg = new ConfigOregenEntry();

        cfg.generate = config.getBoolean(orename, "WORLD", true, "");
        cfg.minY = config.getInt(orename + " Min Y", "WORLD", minY, 0, Integer.MAX_VALUE, "");
        cfg.maxY = config.getInt(orename + " Max Y", "WORLD", maxY, 0, Integer.MAX_VALUE, "");
        cfg.minVeinSize = config.getInt(orename + " Min Vein Size", "WORLD", minVs, 0, Integer.MAX_VALUE, "");
        cfg.maxVeinSize = config.getInt(orename + " Max Vein Size", "WORLD", maxVs, 0, Integer.MAX_VALUE, "");
        cfg.minVeins = config.getInt(orename + " Min Veins Per Chunk", "WORLD", minVeins, 0, Integer.MAX_VALUE, "");
        cfg.maxVeins = config.getInt(orename + " Max Veins Per Chunk", "WORLD", minVeins, 0, Integer.MAX_VALUE, "");
        cfg.chance = config.getFloat(orename + " Generation Chance", "WORLD", chance, 0, 1, "");
        cfg.dimList = loadIntList(config, orename + " DimList", "WORLD", dimList, "");
        cfg.dimListWhitelist = config.getBoolean(orename + " DimList Mode", "WORLD", dimListMode, "True = whitelist; False = blacklist");

        return cfg;
    }

    private List<Integer> loadIntList(Configuration configFile, String name, String category, String[] defaults, String descr)
    {
        String[] rcbld = configFile.getStringList(name, category, defaults, descr);
        List<Integer> lst = new ArrayList<>();
        for(String s : rcbld)
        {
            if(!StringUtils.isNullOrEmpty(s))
            {
                lst.add(Integer.parseInt(s));
            }
        }
        return lst;
    }
}
