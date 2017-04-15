package net.pearx.purmag.items;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pearx.purmag.Utils;
import net.pearx.purmag.blocks.BlockRegistry;
import net.pearx.purmag.tiles.TileCrystal;

/**
 * Created by mrAppleXZ on 11.04.17 10:22.
 */
public class ItemCrystalCutter extends ItemBase
{
    public ItemCrystalCutter()
    {
        setRegistryName(Utils.getRegistryName("crystal_cutter"));
        setUnlocalizedName("crystal_cutter");
        setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (world.getBlockState(pos).getBlock() == BlockRegistry.crystal)
        {
            TileEntity te = world.getTileEntity(pos);
            if (te != null && te instanceof TileCrystal)
            {
                world.playSound(playerIn, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1, 1);
                if(!world.isRemote)
                {
                    TileCrystal tc = ((TileCrystal) te);
                    ItemStack is = new ItemStack(ItemRegistry.crystal);
                    NBTTagCompound tag = new NBTTagCompound();
                    tag.setString("type", tc.getType());
                    is.setTagCompound(tag);
                    world.setBlockToAir(pos);
                    EntityItem ei = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ());
                    ei.setEntityItemStack(is);
                    world.spawnEntity(ei);
                }
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.PASS;
    }
}
