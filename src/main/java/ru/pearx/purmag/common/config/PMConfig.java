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
    public ConfigOreOnOreEntry genCrystallizedRedstone;
    public ConfigOreOnOreEntry genCrystallizedGlowstone;
    public ConfigStructureEntry genLabSmall;
    public ConfigGroundgenEntry genBrulantaFlower;

    public void setup(Configuration configFile)
    {
        genCrystals = configFile.getBoolean("Generate Crystals", "WORLD", true, "");
        genRockCrystalsDimBlacklist = loadIntList(configFile, "Rock Crystals Dimension Blacklist", "WORLD", new String[]{"1", "-1"}, "");
        genCrysagnetite = loadOre(configFile, "Crysagnetite", 15, 25, 1, 3, 1, 2, 0.1f, new String[]{"-1", "1"}, false);
        genCrystallizedRedstone = loadOreOnOre(configFile, "Crystallized Redstone", 0, 16, 0.03f, new String[]{"-1", "1"}, false);
        genCrystallizedGlowstone = loadOreOnOre(configFile, "Crystallized Glowstone", 4, 123, 0.03f, new String[]{"-1"}, true);
        genLabSmall = loadStructure(configFile, "Small Laboratory", 0.0007f, 15, 256, new String[]{"-1", "1"}, false);
        genBrulantaFlower = loadGround(configFile, "Brulanta Flower", 3, 256, 0, 4, 0.1f, new String[]{"-1", "1"}, false);

        if (configFile.hasChanged())
            configFile.save();
    }

    private ConfigOregenEntry loadOre(Configuration config, String name, int minY, int maxY, int minVs, int maxVs, int minVeins, int maxVeins, float chance, String[] dimList, boolean dimListMode)
    {
        ConfigOregenEntry entr = new ConfigOregenEntry();

        entr.generate = config.getBoolean(name, "WORLD", true, "");
        entr.minY = config.getInt(name + " Min Y", "WORLD", minY, 0, Integer.MAX_VALUE, "");
        entr.maxY = config.getInt(name + " Max Y", "WORLD", maxY, 0, Integer.MAX_VALUE, "");
        entr.minVeinSize = config.getInt(name + " Min Vein Size", "WORLD", minVs, 0, Integer.MAX_VALUE, "");
        entr.maxVeinSize = config.getInt(name + " Max Vein Size", "WORLD", maxVs, 0, Integer.MAX_VALUE, "");
        entr.minVeins = config.getInt(name + " Min Veins Per Chunk", "WORLD", minVeins, 0, Integer.MAX_VALUE, "");
        entr.maxVeins = config.getInt(name + " Max Veins Per Chunk", "WORLD", maxVeins, 0, Integer.MAX_VALUE, "");
        entr.chance = config.getFloat(name + " Generation Chance", "WORLD", chance, 0, 1, "");
        entr.dimList = loadIntList(config, name + " DimList", "WORLD", dimList, "");
        entr.dimListMode = config.getBoolean(name + " DimList Mode", "WORLD", dimListMode, "True = whitelist; False = blacklist");

        return entr;
    }

    private ConfigOreOnOreEntry loadOreOnOre(Configuration config, String orename, int minY, int maxY, float chance, String[] dimList, boolean dimListMode)
    {
        ConfigOreOnOreEntry entr = new ConfigOreOnOreEntry();

        entr.generate = config.getBoolean(orename, "WORLD", true, "");
        entr.minY = config.getInt(orename + " Min Y", "WORLD", minY, 0, Integer.MAX_VALUE, "");
        entr.maxY = config.getInt(orename + " Max Y", "WORLD", maxY, 0, Integer.MAX_VALUE, "");
        entr.chance = config.getFloat(orename + " Generation Chance", "WORLD", chance, 0, 1, "");
        entr.dimList = loadIntList(config, orename + " DimList", "WORLD", dimList, "");
        entr.dimListMode = config.getBoolean(orename + " DimList Mode", "WORLD", dimListMode, "True = whitelist; False = blacklist");

        return entr;
    }

    private ConfigStructureEntry loadStructure(Configuration config, String name, float chance, int minY, int maxY, String[] dimList, boolean dimListMode)
    {
        ConfigStructureEntry entr = new ConfigStructureEntry();

        entr.generate = config.getBoolean(name, "WORLD", true, "");
        entr.chance = config.getFloat(name + " Generation Chance", "WORLD", chance, 0, 1, "");
        entr.dimList = loadIntList(config, name + " DimList", "WORLD", dimList, "");
        entr.dimListMode = config.getBoolean(name + " DimList Mode", "WORLD", dimListMode, "True = whitelist; False = blacklist");
        entr.minY = config.getInt(name + " Min Y", "WORLD", minY, 0, Integer.MAX_VALUE, "");
        entr.maxY = config.getInt(name + " Max Y", "WORLD", maxY, 0, Integer.MAX_VALUE, "");

        return entr;
    }

    private ConfigGroundgenEntry loadGround(Configuration config, String name, int minY, int maxY, int minCount, int maxCount, float chance, String[] dimList, boolean dimListMode)
    {
        ConfigGroundgenEntry entr = new ConfigGroundgenEntry();

        entr.generate = config.getBoolean(name, "WORLD", true, "");
        entr.minY = config.getInt(name + " Min Y", "WORLD", minY, 0, Integer.MAX_VALUE, "");
        entr.maxY = config.getInt(name + " Max Y", "WORLD", maxY, 0, Integer.MAX_VALUE, "");
        entr.minCount = config.getInt(name + " Min Count Per Chunk", "WORLD", minCount, 0, Integer.MAX_VALUE, "");
        entr.maxCount = config.getInt(name + " Max Count Per Chunk", "WORLD", maxCount, 0, Integer.MAX_VALUE, "");
        entr.chance = config.getFloat(name + " Generation Chance", "WORLD", chance, 0, 1, "");
        entr.dimList = loadIntList(config, name + " DimList", "WORLD", dimList, "");
        entr.dimListMode = config.getBoolean(name + " DimList Mode", "WORLD", dimListMode, "True = whitelist; False = blacklist");

        return entr;
    }

    private List<Integer> loadIntList(Configuration configFile, String name, String category, String[] defaults, String descr)
    {
        String[] rcbld = configFile.getStringList(name, category, defaults, descr);
        List<Integer> lst = new ArrayList<>();
        for (String s : rcbld)
        {
            if (!StringUtils.isNullOrEmpty(s))
            {
                lst.add(Integer.parseInt(s));
            }
        }
        return lst;
    }
}
