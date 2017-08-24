package ru.pearx.purmag.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.common.blocks.controllers.ConnectionsController;
import ru.pearx.purmag.common.sip.SipUtils;

/**
 * Created by mrAppleXZ on 14.05.17 18:30.
 */
public class BlockCrystalGlass extends BlockAbstractCrystalGlass
{
    public BlockCrystalGlass()
    {
        super("crystal_glass");
    }
}
