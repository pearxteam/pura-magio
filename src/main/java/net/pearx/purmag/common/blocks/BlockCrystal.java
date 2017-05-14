package net.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pearx.purmag.common.PMCreativeTab;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.items.ItemRegistry;
import net.pearx.purmag.common.items.ItemUtils;
import net.pearx.purmag.common.sip.SipTypeRegistry;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by mrAppleXZ on 08.04.17 17:46.
 */
public class BlockCrystal extends BlockSingleSip
{
    public BlockCrystal()
    {
        super(Material.ROCK);
        setRegistryName(Utils.getRegistryName("crystal"));
        setCreativeTab(PMCreativeTab.instance);
        setUnlocalizedName("crystal");
        setHardness(2);
        setLightLevel(5);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(world, pos, state, placer, stack);
        TileEntity te = world.getTileEntity(pos);
        if (te != null && te instanceof TileSingleSip)
        {
            TileSingleSip tec = (TileSingleSip) te;
            String type = SipTypeRegistry.DEFAULT;
            if (stack.hasTagCompound())
            {
                if (stack.getTagCompound().hasKey("SIPTYPE"))
                {
                    type = stack.getTagCompound().getString("SIPTYPE");
                }
            }
            tec.setType(type);
        }
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        if(willHarvest) return true;
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack)
    {
        super.harvestBlock(world, player, pos, state, te, stack);
        world.setBlockToAir(pos);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World) world).rand : RANDOM;
        ArrayList<ItemStack> lst = new ArrayList<>();

        TileEntity te = world.getTileEntity(pos);
        if(te != null && te instanceof TileSingleSip)
        {
            ItemStack stack = ItemUtils.getItemWithSip(((TileSingleSip)te).getType(), ItemRegistry.crystal_shard);
            stack.setCount(rand.nextInt(4) + 3);
            lst.add(stack);
        }
        return lst;
    }
}
