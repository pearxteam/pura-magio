package ru.pearx.purmag.common.blocks.multiblock;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.pearx.libmc.common.blocks.BlockMultiblockPart;
import ru.pearx.purmag.common.tiles.TileStoneCrusher;

/*
 * Created by mrAppleXZ on 17.11.17 7:19.
 */
public class BlockStoneCrusher extends BlockMultiblockPart
{
    public BlockStoneCrusher()
    {
        super(Material.WOOD);
        setHardness(1.1f);
        setSoundType(SoundType.WOOD);
        setRegistryName("stone_crusher");
    }

    @Override
    public TileEntity createMasterTile(World world, IBlockState state)
    {
        return new TileStoneCrusher();
    }
}
