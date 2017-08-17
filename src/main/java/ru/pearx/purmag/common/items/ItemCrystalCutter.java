package ru.pearx.purmag.common.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.sip.SipUtils;

/**
 * Created by mrAppleXZ on 11.04.17 10:22.
 */
public class ItemCrystalCutter extends ItemBase
{
    public ItemCrystalCutter()
    {
        super("crystal_cutter");
        setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() == BlockRegistry.crystal)
        {
            world.playSound(playerIn, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1, 1);
            if (!world.isRemote)
            {
                ItemStack is = SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal), SipUtils.getSipInBlock(world, pos));
                world.setBlockToAir(pos);
                EntityItem ei = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ());
                ei.setItem(is);
                world.spawnEntity(ei);
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }
}
