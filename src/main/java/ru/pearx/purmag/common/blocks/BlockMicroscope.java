package ru.pearx.purmag.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.tiles.TileAbstractSingleItem;
import ru.pearx.purmag.common.tiles.TileMicroscope;

import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 17.08.17 14:49.
 */
public class BlockMicroscope extends BlockAbstractSingleItemHorizontal
{
    public BlockMicroscope()
    {
        super("microscope", Material.IRON);
        setHarvestLevel("pickaxe", 0);
        setHardness(1.2f);
        setSoundType(SoundType.METAL);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileMicroscope();
    }

    @Override
    public void openClientGui(World worldIn, BlockPos pos, IBlockState state, EntityPlayer p, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ, TileAbstractSingleItem tile)
    {
        PurMag.proxy.openMicroscope(tile.handler.getStackInSlot(0));
    }
}
