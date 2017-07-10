package net.pearx.purmag.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.infofield.IfTier;

import javax.annotation.Nullable;
import java.util.Random;

/*
 * Created by mrAppleXZ on 09.07.17 17:15.
 */
public class BlockBrokenWallIfTablet extends AbstractWallIfTablet
{
    public BlockBrokenWallIfTablet()
    {
        setRegistryName("broken_wall_if_tablet");
        setUnlocalizedName("broken_wall_if_tablet");
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        worldIn.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1, 1);
        worldIn.setBlockToAir(pos);
    }
}
