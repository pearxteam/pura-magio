package net.pearx.purmag.registries;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.Utils;
import net.pearx.purmag.items.*;

/**
 * Created by mrAppleXZ on 08.04.17 18:46.
 */
public class ItemRegistry
{
    public static Item crystal;
    public static Item crystal_shard;
    public static Item crystal_cutter;
    public static Item if_tablet;

    public static void setup()
    {
        crystal = new ItemBlockCrystal();
        GameRegistry.register(crystal);

        crystal_shard = new ItemCrystalShard();
        GameRegistry.register(crystal_shard);

        crystal_cutter = new ItemCrystalCutter();
        GameRegistry.register(crystal_cutter);

        if_tablet = new ItemIfTablet();
        GameRegistry.register(if_tablet);
    }

    @SideOnly(Side.CLIENT)
    public static void setupModels()
    {
        Utils.setModelLocation(crystal);
        Utils.setModelLocation(crystal_shard);
        Utils.setModelLocation(crystal_cutter);
    }

}
