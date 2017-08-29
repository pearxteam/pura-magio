package ru.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.particle.ParticleEngine;
import ru.pearx.purmag.client.particle.ParticleCrystal;
import ru.pearx.purmag.common.items.ItemRegistry;
import ru.pearx.purmag.common.sip.SipUtils;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by mrAppleXZ on 08.04.17 17:46.
 */
public class BlockCrystal extends BlockSingleSip
{
    public BlockCrystal()
    {
        super("crystal", Material.ROCK);
        setHardness(2);
        setLightLevel(0.5f);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public boolean isFullBlock(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }


    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        return willHarvest || super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack)
    {
        super.harvestBlock(world, player, pos, state, te, stack);
        world.setBlockToAir(pos);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World) world).rand : RANDOM;
        drops.add(SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal_shard, rand.nextInt(4) + 3), SipUtils.getSipInBlock(world, pos)));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if(rand.nextInt(3) == 0)
        {
            ParticleEngine.addParticle(new ParticleCrystal(pos.getX() + rand.nextFloat(), pos.getY() + 1.5f, pos.getZ() + rand.nextFloat(), SipUtils.getSipInBlock(worldIn, pos), 100));
        }
    }
}
