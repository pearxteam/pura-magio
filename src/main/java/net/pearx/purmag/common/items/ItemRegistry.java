package net.pearx.purmag.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.ClientUtils;
import net.pearx.purmag.client.blockstates.ISMSingleSip;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.blocks.BlockOre;
import net.pearx.purmag.common.blocks.BlockRegistry;
import net.pearx.purmag.common.blocks.BlockSingleSip;
import net.pearx.purmag.common.infofield.IfTier;
import net.pearx.purmag.common.sip.SipType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrAppleXZ on 08.04.17 18:46.
 */
public class ItemRegistry
{
    public static Item crystal;
    public static Item crystal_shard;
    public static Item crystal_cutter;
    public static Item if_tablet;
    public static Item crystal_glass;
    public static Item ore_crysagnetite;
    public static Item ingot_crysagnetite;
    public static Item sip_amulet;
    public static Item glove;

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

        ore_crysagnetite = ItemUtils.getItemFromBlock(BlockRegistry.ore_crysagnetite);
        GameRegistry.register(ore_crysagnetite);
        BlockRegistry.ore_crysagnetite.dropped = new ItemStack(ore_crysagnetite);

        ingot_crysagnetite = new ItemSimple(Utils.getRegistryName("ingot_crysagnetite"));
        GameRegistry.register(ingot_crysagnetite);
        OreDictionary.registerOre("ingotCrysagnetite", ingot_crysagnetite);

        sip_amulet = new ItemSipAmulet();
        GameRegistry.register(sip_amulet);

        glove = new ItemGlove();
        GameRegistry.register(glove);
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

        ModelLoader.setCustomStateMapper(BlockRegistry.crystal, new ISMSingleSip(ClientUtils.getModelResourceLocation("crystal")));
        ModelLoader.setCustomStateMapper(BlockRegistry.crystal_glass, new ISMSingleSip(ClientUtils.getModelResourceLocation("crystal_glass")));
    }
}
