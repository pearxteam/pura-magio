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
import ru.pearx.purmag.common.tiles.TileTranslationDesk;

import javax.annotation.Nullable;

/**
 * Created by mrAppleXZ on 03.06.17 19:07.
 */
public class BlockTranslationDesk extends BlockAbstractSingleItemHorizontal
{
    public BlockTranslationDesk()
    {
        super("translation_desk", Material.WOOD);
        setHarvestLevel("axe", 0);
        setHardness(1.7f);
        setSoundType(SoundType.WOOD);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileTranslationDesk();
    }

    @Override
    public void openClientGui(World worldIn, BlockPos pos, IBlockState state, EntityPlayer p, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ, TileAbstractSingleItem tile)
    {
        PurMag.proxy.openTranslationDesk(pos, worldIn);
    }
}
