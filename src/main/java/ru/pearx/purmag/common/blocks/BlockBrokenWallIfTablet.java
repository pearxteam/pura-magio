package ru.pearx.purmag.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.CPacketSpawnParticle;

import javax.annotation.Nullable;
import javax.vecmath.Vector3d;

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
        NetworkManager.sendToAllAround(new CPacketSpawnParticle(EnumParticleTypes.EXPLOSION_LARGE, new Vector3d(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5), new Vector3d()),
                pos.getX(), pos.getY(), pos.getZ(), worldIn.provider.getDimension(), 256);
        worldIn.setBlockToAir(pos);
    }
}
