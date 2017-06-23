package net.pearx.purmag.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.ClientUtils;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.blocks.*;
import net.pearx.purmag.common.blocks.controllers.FacingController;
import net.pearx.purmag.common.infofield.IfTier;
import net.pearx.purmag.common.items.papyrus.ItemPapyrus;
import net.pearx.purmag.common.sip.SipType;
import net.pearx.purmag.common.sip.SipTypeRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrAppleXZ on 08.04.17 18:46.
 */
public class ItemRegistry
{
    public static ItemBlockCrystal crystal;
    public static ItemCrystalShard crystal_shard;
    public static ItemCrystalCutter crystal_cutter;
    public static ItemIfTablet if_tablet;
    public static ItemBlockCrystalGlass crystal_glass;
    public static Item ore_crysagnetite;
    public static Item ingot_crysagnetite;
    public static ItemSipAmulet sip_amulet;
    public static ItemGlove glove;
    public static ItemPapyrus papyrus;
    public static Item translation_desk;
    public static ItemBlockCrystalSmall crystal_small;
    public static Item plumfero;

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

        crystal_glass = new ItemBlockCrystalGlass();
        GameRegistry.register(crystal_glass);

        for(SipType t : PurMag.instance.sip.types)
        {
            OreDictionary.registerOre("sipCrystal", new ItemStack(crystal, 1, t.getId()));
            OreDictionary.registerOre("sipShard", new ItemStack(crystal_shard, 1, t.getId()));
            OreDictionary.registerOre("blockGlass", new ItemStack(crystal_glass, 1, t.getId()));
        }

        ore_crysagnetite = ItemUtils.getItemFromBlock(BlockRegistry.ore_crysagnetite);
        GameRegistry.register(ore_crysagnetite);
        BlockRegistry.ore_crysagnetite.dropped = new ItemStack(ore_crysagnetite);
        OreDictionary.registerOre("oreCrysagnetite", ore_crysagnetite);

        ingot_crysagnetite = new ItemSimple(Utils.getRegistryName("ingot_crysagnetite"));
        GameRegistry.register(ingot_crysagnetite);
        OreDictionary.registerOre("ingotCrysagnetite", ingot_crysagnetite);

        sip_amulet = new ItemSipAmulet();
        GameRegistry.register(sip_amulet);

        glove = new ItemGlove();
        GameRegistry.register(glove);

        papyrus = new ItemPapyrus();
        GameRegistry.register(papyrus);

        translation_desk = ItemUtils.getItemFromBlock(BlockRegistry.translation_desk);
        GameRegistry.register(translation_desk);

        crystal_small = new ItemBlockCrystalSmall();
        GameRegistry.register(crystal_small);

        plumfero = new ItemSimple(Utils.getRegistryName("plumfero"));
        GameRegistry.register(plumfero);
        OreDictionary.registerOre("ingotPlumfero", plumfero);
    }

    @SideOnly(Side.CLIENT)
    public static void setupModels()
    {
        for(SipType t : PurMag.instance.sip.types)
        {
            ClientUtils.setModelLocation(crystal, t.getId(), "");
            ClientUtils.setModelLocation(crystal_shard, t.getId(), "");
            ClientUtils.setModelLocation(crystal_glass, t.getId(), "");
        }
        ClientUtils.setModelLocation(crystal_cutter);
        for (IfTier t : PurMag.instance.if_registry.tiers)
        {
            ClientUtils.setModelLocation(if_tablet, t.getTier(), "." + t.getTier());
        }
        ClientUtils.setModelLocation(ore_crysagnetite);
        ClientUtils.setModelLocation(ingot_crysagnetite);
        for(int i = 0; i < 3; i++)
            ClientUtils.setModelLocation(sip_amulet, i, "." + i);
        ClientUtils.setModelLocation(glove);
        ClientUtils.setModelLocation(papyrus);
        ClientUtils.setModelLocation(translation_desk);
        for(BlockCrystalSmall.Type t : BlockCrystalSmall.Type.values())
            ClientUtils.setModelLocation(crystal_small, t.ordinal(), "");
        ClientUtils.setModelLocation(plumfero);

        ModelLoader.setCustomStateMapper(BlockRegistry.crystal, new StateMap.Builder().ignore(BlockSingleSip.SIPTYPE).build());
        ModelLoader.setCustomStateMapper(BlockRegistry.crystal_glass, new StateMap.Builder().ignore(BlockSingleSip.SIPTYPE).build());
        ModelLoader.setCustomStateMapper(BlockRegistry.translation_desk, new StateMap.Builder().ignore(FacingController.FACING).build());
        ModelLoader.setCustomStateMapper(BlockRegistry.crystal_small, new StateMap.Builder().ignore(BlockCrystalSmall.TYPE).build());
    }
}
