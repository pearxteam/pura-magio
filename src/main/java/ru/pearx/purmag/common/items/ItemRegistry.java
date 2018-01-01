package ru.pearx.purmag.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import ru.pearx.libmc.PXLMC;
import ru.pearx.libmc.client.models.IModelProvider;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.items.papyrus.ItemPapyrus;

/**
 * Created by mrAppleXZ on 08.04.17 18:46.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID)
@GameRegistry.ObjectHolder(PurMag.MODID)
public class ItemRegistry
{
    public static final ItemBlockSingleSip crystal = null;
    public static final ItemCrystalShard crystal_shard = null;
    public static final ItemCrystalCutter crystal_cutter = null;
    public static final ItemIfTablet if_tablet = null;
    public static final ItemBlockSingleSip crystal_glass = null;
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
    public static final ItemBlockSingleSip luminous_crystal_glass = null;
    public static final ItemBlock test = null;
    public static final ItemBlock brulanta_flower = null;
    public static final Item pyroblend = null;
    public static final ItemMortarAndPestle mortar_and_pestle = null;
    public static final Item unfinished_mortar_and_pestle = null;
    public static final ItemBlockCodeStorage code_storage = null;
    public static final ItemBlock microscope = null;
    public static final Item painting_kit = null;
    public static final Item gray_paper_pack = null;
    public static final ItemBlockMagibench magibench = null;
    public static final Item porcelain = null;
    public static final ItemTinkeringKit stone_tinkering_kit = null;
    public static final ItemBlock rope_coil = null;

    public static void register(Item itm, IForgeRegistry<Item> reg)
    {
        reg.register(itm);
        if (itm instanceof IModelProvider)
            PXLMC.PROXY.setupModels((IModelProvider) itm);
    }

    @SuppressWarnings("ConstantConditions")
    public static void setupOreDict()
    {
        OreDictionary.registerOre("sipCrystal", new ItemStack(crystal, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("sipShard", new ItemStack(crystal_shard, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("blockGlass", new ItemStack(crystal_glass, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("blockGlass", new ItemStack(luminous_crystal_glass, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("oreCrysagnetite", ore_crysagnetite);
        OreDictionary.registerOre("ingotCrysagnetite", ingot_crysagnetite);
        OreDictionary.registerOre("ingotPlumfero", ingot_plumfero);
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> e)
    {
        IForgeRegistry<Item> reg = e.getRegistry();
        register(new ItemBlockSingleSip(BlockRegistry.crystal), reg);
        register(new ItemCrystalShard(), reg);
        register(new ItemCrystalCutter(), reg);
        register(new ItemIfTablet(), reg);
        register(new ItemBlockSingleSip(BlockRegistry.crystal_glass), reg);
        register(new ItemBlockBase(BlockRegistry.ore_crysagnetite), reg);
        register(new ItemBase("ingot_crysagnetite"), reg);
        register(new ItemSipAmulet(), reg);
        register(new ItemGlove(), reg);
        register(new ItemPapyrus(), reg);
        register(new ItemBlockBase(BlockRegistry.translation_desk), reg);
        register(new ItemBlockCrystalSmall(), reg);
        register(new ItemBase("ingot_plumfero"), reg);
        register(new ItemBase("verda_wing"), reg);
        register(new ItemFoodBakeable("beetle_meat", 3, 0.125f, false, 8, 0.55f), reg);
        register(new ItemBlockWallIfTablet(), reg);
        register(new ItemBlockBrokenWallIfTablet(), reg);
        register(new ItemBlockBase(BlockRegistry.microscope), reg);
        register(new ItemBlockSingleSip(BlockRegistry.luminous_crystal_glass), reg);
        register(new ItemBlockBase(BlockRegistry.test), reg);
        register(new ItemBlockBase(BlockRegistry.brulanta_flower), reg);
        register(new ItemBase("pyroblend"), reg);
        register(new ItemMortarAndPestle(), reg);
        register(new ItemBase("unfinished_mortar_and_pestle"), reg);
        register(new ItemBlockCodeStorage(), reg);
        register(new ItemBase("painting_kit"), reg);
        register(new ItemBase("gray_paper_pack"), reg);
        register(new ItemBlockMagibench(), reg);
        register(new ItemBase("porcelain"), reg);
        register(new ItemTinkeringKit(Item.ToolMaterial.STONE, "stone_tinkering_kit"), reg);
        register(new ItemBlockRopeCoil(), reg);
    }
}
