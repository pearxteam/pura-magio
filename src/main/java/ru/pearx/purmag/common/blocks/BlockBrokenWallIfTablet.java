package ru.pearx.purmag.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.common.blocks.controllers.HorizontalFacingController;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.CPacketSpawnParticle;

import javax.annotation.Nullable;
import javax.vecmath.Vector3d;
import java.util.List;
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
        NetworkManager.sendToAllAround(new CPacketSpawnParticle(EnumParticleTypes.EXPLOSION_LARGE, new Vector3d(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5), new Vector3d()),
                pos.getX(), pos.getY(), pos.getZ(), worldIn.provider.getDimension(), 256);
        worldIn.setBlockToAir(pos);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
        tooltip.add(I18n.format(getUnlocalizedName() + ".tooltip"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double x = pos.getX() + 0.5;
        double z = pos.getZ() + 0.5;
        double y = pos.getY() + 0.5;
        switch (stateIn.getValue(HorizontalFacingController.FACING_H))
        {
            case NORTH:
                z += 0.4;
                break;
            case SOUTH:
                z -= 0.4;
                break;
            case EAST:
                x -= 0.4;
                break;
            case WEST:
                x += 0.4;
                break;
        }
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0, 0 ,0);
    }
}
