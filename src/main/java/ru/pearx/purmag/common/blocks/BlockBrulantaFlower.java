package ru.pearx.purmag.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.RandomUtils;

import java.util.Random;

/*
 * Created by mrAppleXZ on 31.08.17 20:58.
 */
public class BlockBrulantaFlower extends BlockBushBase
{
    public BlockBrulantaFlower()
    {
        super("brulanta_flower");
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + RandomUtils.nextFloat(0.25f, 0.75f, rand), pos.getY() + 0.9f, pos.getZ() + RandomUtils.nextFloat(0.25f, 0.75f, rand), 0, 0, 0);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Desert;
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.SAND;
    }
}
