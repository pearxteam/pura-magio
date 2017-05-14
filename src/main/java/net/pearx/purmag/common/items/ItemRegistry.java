package net.pearx.purmag.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.ClientUtils;
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
        for(SipType t : PurMag.instance.sip.types)
        {
            ClientUtils.setModelLocation(crystal, t.getId(), "");
            ClientUtils.setModelLocation(crystal_shard, t.getId(), "");
        }
        ClientUtils.setModelLocation(crystal_cutter);
        for (IfTier t : PurMag.instance.if_registry.tiers)
        {
            ClientUtils.setModelLocation(if_tablet, t.getTier(), "." + t.getTier());
        }

        ModelLoader.setCustomStateMapper(BlockRegistry.crystal, new IStateMapper()
        {
            @Override
            @SideOnly(Side.CLIENT)
            public Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block blockIn)
            {
                HashMap<IBlockState, ModelResourceLocation> map = new HashMap<>();
                for(String s : PurMag.instance.sip.allowedValues)
                {
                    map.put(BlockRegistry.crystal.getDefaultState().withProperty(BlockSingleSip.SIPTYPE, s), ClientUtils.getModelResourceLocation("crystal"));
                }
                return map;
            }
        });
    }
}
