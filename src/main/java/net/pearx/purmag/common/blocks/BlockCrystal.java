package net.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.PMCreativeTab;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.items.ItemRegistry;
import net.pearx.purmag.common.items.ItemUtils;
import net.pearx.purmag.common.sip.SipTypeRegistry;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by mrAppleXZ on 08.04.17 17:46.
 */
public class BlockCrystal extends BlockSingleSip
{
    public BlockCrystal()
    {
        super(Material.ROCK);
        setRegistryName(Utils.getRegistryName("crystal"));
        setUnlocalizedName("crystal");
        setHardness(2);
        setLightLevel(5);
    }

    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ItemRegistry.crystal_shard;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return random.nextInt(4) + 3;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return PurMag.instance.sip.getType(state.getValue(SIPTYPE)).getId();
    }

    @Override
    public int getHarvestLevel(IBlockState state)
    {
        return 1;
    }

    @Nullable
    @Override
    public String getHarvestTool(IBlockState state)
    {
        return "pickaxe";
    }
}
