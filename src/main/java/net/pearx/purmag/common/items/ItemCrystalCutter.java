package net.pearx.purmag.common.items;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.GlobalChunkPos;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.blocks.BlockRegistry;
import net.pearx.purmag.common.tiles.TileCrystal;
import org.lwjgl.input.Mouse;

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
    public EnumActionResult onItemUse(EntityPlayer playerIn, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        //todo remove this debug print
        Chunk ch = world.getChunkFromBlockCoords(pos);
        playerIn.sendMessage(new TextComponentString(Float.toString(PurMag.proxy.getSifStorage().getPower(new GlobalChunkPos(ch.xPosition, ch.zPosition, world.provider.getDimension())))));

        if (world.getBlockState(pos).getBlock() == BlockRegistry.crystal)
        {
            TileEntity te = world.getTileEntity(pos);
            if (te != null && te instanceof TileCrystal)
            {
                world.playSound(playerIn, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1, 1);
                if(!world.isRemote)
                {
                    TileCrystal tc = ((TileCrystal) te);
                    ItemStack is = ItemBlockCrystal.getCrystalWithSip(tc.getType());
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
