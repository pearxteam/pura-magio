package ru.pearx.purmag.common.loot_tables;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetNBT;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.items.ItemRegistry;
import ru.pearx.purmag.common.items.papyrus.PapyrusData;
import ru.pearx.purmag.common.items.papyrus.PapyrusRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 24.06.17 12:29.
 */
public class LootTablesRegistry
{
    public static void setup()
    {
        LootTableList.register(Utils.getRegistryName("entities/verda_beetle"));
        LootTableList.register(Utils.getRegistryName("chests/lab_small"));
        LootTableList.register(Utils.getRegistryName("chests/microscope"));
    }
}
