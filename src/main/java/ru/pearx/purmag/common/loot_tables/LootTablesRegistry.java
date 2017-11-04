package ru.pearx.purmag.common.loot_tables;

import net.minecraft.world.storage.loot.*;
import ru.pearx.purmag.common.Utils;

/**
 * Created by mrAppleXZ on 24.06.17 12:29.
 */
public class LootTablesRegistry
{
    public static void setup()
    {
        LootTableList.register(Utils.gRL("entities/verda_beetle"));
        LootTableList.register(Utils.gRL("chests/lab"));
        LootTableList.register(Utils.gRL("chests/microscope"));
    }
}
