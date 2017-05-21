package net.pearx.purmag.common.config;

import net.minecraft.util.StringUtils;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 04.05.17 17:13.
 */
public class PMConfig
{
    public boolean genCrystals;
    public List<Integer> genRockCrystalsDimBlacklist;
    public ConfigOregenEntry genCrysagnetite;

    public void setup(Configuration configFile)
    {
        genCrystals = configFile.getBoolean("Generate Crystals", "WORLD", true, "");
        genRockCrystalsDimBlacklist = loadIntList(configFile, "Rock Crystals Dimension Blacklist", "WORLD", new String[] {"1", "-1"}, "");

        genCrysagnetite = loadOregen(configFile, "Crysagnetite", 15, 25, 1, 1, 0.1f, new String[] {"-1", "1"});

        if(configFile.hasChanged())
            configFile.save();
    }

    private ConfigOregenEntry loadOregen(Configuration config, String orename, int minY, int maxY, int minVs, int maxVs, float chance, String[] dimList)
    {
        ConfigOregenEntry cfg = new ConfigOregenEntry();

        cfg.generate = config.getBoolean("Generate " + orename, "WORLD", true, "");
        cfg.minY = config.getInt(orename + " Min Y", "WORLD", minY, 0, Integer.MAX_VALUE, "");
        cfg.maxY = config.getInt(orename + " Max Y", "WORLD", maxY, 0, Integer.MAX_VALUE, "");
        cfg.minVeinSize = config.getInt(orename + " Min Vein Size", "WORLD", minVs, 0, Integer.MAX_VALUE, "");
        cfg.maxVeinSize = config.getInt(orename + " Max Vein Size", "WORLD", maxVs, 0, Integer.MAX_VALUE, "");
        cfg.chance = config.getFloat(orename + " Generation Chance", "WORLD", chance, 0, 1, "");
        cfg.dimList = loadIntList(config, orename + " DimList", "WORLD", dimList, "");
        cfg.dimListWhitelist = config.getBoolean(orename + " DimList Mode", "WORLD", false, "True = whitelist; False = blacklist");

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
