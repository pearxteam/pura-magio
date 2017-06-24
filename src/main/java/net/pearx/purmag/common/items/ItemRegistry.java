package net.pearx.purmag.common.items;

import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.ClientUtils;
import net.pearx.purmag.client.models.IModelProvider;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.blocks.*;
import net.pearx.purmag.common.blocks.controllers.FacingController;
import net.pearx.purmag.common.infofield.IfTier;
import net.pearx.purmag.common.items.papyrus.ItemPapyrus;
import net.pearx.purmag.common.sip.SipType;

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
    public static Item beetle_venom;
    public static Item beetle_meat;

    public static void setup()
    {
        crystal = new ItemBlockCrystal();
        register(crystal);

        crystal_shard = new ItemCrystalShard();
        register(crystal_shard);

        crystal_cutter = new ItemCrystalCutter();
        register(crystal_cutter);

        if_tablet = new ItemIfTablet();
        register(if_tablet);

        crystal_glass = new ItemBlockCrystalGlass();
        register(crystal_glass);

        ore_crysagnetite = new ItemBlockBase(BlockRegistry.ore_crysagnetite);
        register(ore_crysagnetite);
        BlockRegistry.ore_crysagnetite.dropped = new ItemStack(ore_crysagnetite);

        ingot_crysagnetite = new ItemSimple(Utils.getRegistryName("ingot_crysagnetite"));
        register(ingot_crysagnetite);

        sip_amulet = new ItemSipAmulet();
        register(sip_amulet);

        glove = new ItemGlove();
        register(glove);

        papyrus = new ItemPapyrus();
        register(papyrus);

        translation_desk = new ItemBlockBase(BlockRegistry.translation_desk);
        register(translation_desk);

        crystal_small = new ItemBlockCrystalSmall();
        register(crystal_small);

        plumfero = new ItemSimple(Utils.getRegistryName("plumfero"));
        register(plumfero);

        beetle_venom = new ItemSimple(Utils.getRegistryName("beetle_venom"));
        register(beetle_venom);

        beetle_meat = new ItemSimple(Utils.getRegistryName("beetle_meat"));
        register(beetle_meat);
    }

    public static void setupOreDict()
    {
        for(SipType t : PurMag.instance.sip.types)
        {
            OreDictionary.registerOre("sipCrystal", new ItemStack(crystal, 1, t.getId()));
            OreDictionary.registerOre("sipShard", new ItemStack(crystal_shard, 1, t.getId()));
            OreDictionary.registerOre("blockGlass", new ItemStack(crystal_glass, 1, t.getId()));
        }
        OreDictionary.registerOre("oreCrysagnetite", ore_crysagnetite);
        OreDictionary.registerOre("ingotCrysagnetite", ingot_crysagnetite);
        OreDictionary.registerOre("ingotPlumfero", plumfero);
    }

    public static void register(Item itm)
    {
        GameRegistry.register(itm);
        if(itm instanceof IModelProvider)
            PurMag.proxy.setupModels((IModelProvider)itm);
    }

    @SideOnly(Side.CLIENT)
    public static void setupModels()
    {
        ModelLoader.setCustomStateMapper(BlockRegistry.crystal, new StateMap.Builder().ignore(BlockSingleSip.SIPTYPE).build());
        ModelLoader.setCustomStateMapper(BlockRegistry.crystal_glass, new StateMap.Builder().ignore(BlockSingleSip.SIPTYPE).build());
        ModelLoader.setCustomStateMapper(BlockRegistry.translation_desk, new StateMap.Builder().ignore(FacingController.FACING).build());
        ModelLoader.setCustomStateMapper(BlockRegistry.crystal_small, new StateMap.Builder().ignore(BlockCrystalSmall.TYPE).build());
    }
}
