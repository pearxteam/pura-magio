package net.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.pearx.purmag.common.PMCreativeTab;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.items.ItemRegistry;
import net.pearx.purmag.common.sip.SipType;
import net.pearx.purmag.common.sip.SipTypeRegistry;
import net.pearx.purmag.common.tiles.TileCrystal;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by mrAppleXZ on 08.04.17 17:46.
 */
public class BlockCrystal extends BlockBase
{
    public BlockCrystal()
    {
        super(Material.ROCK);
        setRegistryName(Utils.getRegistryName("crystal"));
        setCreativeTab(PMCreativeTab.instance);
        setUnlocalizedName("crystal");
        setHardness(2);
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
        if (te != null && te instanceof TileCrystal)
        {
            TileCrystal tec = (TileCrystal) te;
            String type = SipTypeRegistry.def;
            if (stack.hasTagCompound())
            {
                if (stack.getTagCompound().hasKey("type"))
                {
                    type = stack.getTagCompound().getString("type");
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
        ItemStack stack = new ItemStack(ItemRegistry.crystal_shard, rand.nextInt(4) + 3);

        TileEntity te = world.getTileEntity(pos);
        if(te != null && te instanceof TileCrystal)
        {
            String type = ((TileCrystal)te).getType();
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("type", type);
            stack.setTagCompound(tag);
        }
        lst.add(stack);
        return lst;
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        for(SipType t : PurMag.instance.sip.types)
        {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("type", t.getName());
            ItemStack st = new ItemStack(ItemRegistry.crystal);
            st.setTagCompound(tag);
            list.add(st);
        }
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileCrystal();
    }

    @Nullable
    @Override
    protected RayTraceResult rayTrace(BlockPos pos, Vec3d start, Vec3d end, AxisAlignedBB boundingBox)
    {
        return super.rayTrace(pos, start, end, boundingBox);
    }
}
