package ru.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.sip.SipType;
import ru.pearx.purmag.common.sip.SipUtils;
import ru.pearx.purmag.common.tiles.TileSingleSip;

import javax.annotation.Nullable;

/**
 * Created by mrAppleXZ on 12.05.17 22:00.
 */
public class BlockSingleSip extends BlockBase
{
    public BlockSingleSip(Material materialIn)
    {
        super(materialIn);
    }


    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list)
    {
        for(SipType t : PurMag.INSTANCE.sip.types)
        {
            list.add(SipUtils.getStackWithSip(new ItemStack(this), t.getName()));
        }
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileSingleSip();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        SipUtils.setSipInBlock(worldIn, pos, SipUtils.getSipInStack(stack));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return SipUtils.getStackWithSip(new ItemStack(this), SipUtils.getSipInBlock(world, pos));
    }
}
