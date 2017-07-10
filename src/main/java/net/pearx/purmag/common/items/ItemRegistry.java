package net.pearx.purmag.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.models.IModelProvider;
import net.pearx.purmag.common.blocks.*;
import net.pearx.purmag.common.items.papyrus.ItemPapyrus;

/**
 * Created by mrAppleXZ on 08.04.17 18:46.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID)
@GameRegistry.ObjectHolder(PurMag.MODID)
public class ItemRegistry
{
    public static final ItemBlockCrystal crystal = null;
    public static final ItemCrystalShard crystal_shard = null;
    public static final ItemCrystalCutter crystal_cutter = null;
    public static final ItemIfTablet if_tablet = null;
    public static final ItemBlockCrystalGlass crystal_glass = null;
    public static final Item ore_crysagnetite = null;
    public static final Item ingot_crysagnetite = null;
    public static final ItemSipAmulet sip_amulet = null;
    public static final ItemGlove glove = null;
    public static final ItemPapyrus papyrus = null;
    public static final Item translation_desk = null;
    public static final ItemBlockCrystalSmall crystal_small = null;
    public static final Item ingot_plumfero = null;
    public static final Item verda_wing = null;
    public static final Item beetle_meat = null;
    public static final ItemBlockWallIfTablet wall_if_tablet = null;
    public static final ItemBlockBrokenWallIfTablet broken_wall_if_tablet = null;

    public static void register(Item itm, IForgeRegistry<Item> reg)
    {
        reg.register(itm);
        if(itm instanceof IModelProvider)
            PurMag.proxy.setupModels((IModelProvider) itm);
    }

    @SuppressWarnings("ConstantConditions")
    public static void setup()
    {
        BlockRegistry.ore_crysagnetite.dropped = new ItemStack(ore_crysagnetite);

        OreDictionary.registerOre("sipCrystal", new ItemStack(crystal, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("sipShard", new ItemStack(crystal_shard, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("blockGlass", new ItemStack(crystal_glass, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("oreCrysagnetite", ore_crysagnetite);
        OreDictionary.registerOre("ingotCrysagnetite", ingot_crysagnetite);
        OreDictionary.registerOre("ingotPlumfero", ingot_plumfero);
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> e)
    {
        IForgeRegistry<Item> reg = e.getRegistry();
        register(new ItemBlockCrystal(), reg);
        register(new ItemCrystalShard(), reg);
        register(new ItemCrystalCutter(), reg);
        register(new ItemIfTablet(), reg);
        register(new ItemBlockCrystalGlass(), reg);
        register(new ItemBlockBase(BlockRegistry.ore_crysagnetite), reg);
        register(new ItemBase().setRegistryName("ingot_crysagnetite"), reg);
        register(new ItemSipAmulet(), reg);
        register(new ItemGlove(), reg);
        register(new ItemPapyrus(), reg);
        register(new ItemBlockBase(BlockRegistry.translation_desk), reg);
        register(new ItemBlockCrystalSmall(), reg);
        register(new ItemBase().setRegistryName("ingot_plumfero"), reg);
        register(new ItemBase().setRegistryName("verda_wing"), reg);
        register(new ItemFoodBakeable(3, 0.125f, false, 8, 0.55f).setRegistryName("beetle_meat"), reg);
        register(new ItemBlockWallIfTablet(), reg);
        register(new ItemBlockBrokenWallIfTablet(), reg);
    }
}
