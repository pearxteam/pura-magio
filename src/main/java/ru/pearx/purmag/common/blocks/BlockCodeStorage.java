package ru.pearx.purmag.common.blocks;

import jdk.nashorn.internal.runtime.CodeStore;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.tiles.TileCodeStorage;
import ru.pearx.purmag.common.tiles.TileWallIfTablet;

import javax.annotation.Nullable;
import java.util.List;

/*
 * Created by mrAppleXZ on 16.09.17 16:26.
 */
public class BlockCodeStorage extends BlockBase
{
    public static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.1, 0f, 0.1f, 0.9f, 0.8f, 0.9f);
    public static final int GUI_ID = 0;

    public BlockCodeStorage()
    {
        super("code_storage", Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(1f);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDING_BOX;
    }

    @Override
    public boolean isFullBlock(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileCodeStorage();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        if(te != null && te instanceof TileCodeStorage)
        {
            TileCodeStorage storage = (TileCodeStorage) te;
            if(storage.isUnlocked())
            {
                playerIn.openGui(PurMag.INSTANCE, GUI_ID,  worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
            else
            {
                //todo open unlocking gui
            }
        }
        return true;
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        return willHarvest || super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack)
    {
        super.harvestBlock(world, player, pos, state, te, stack);
        world.setBlockToAir(pos);
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        TileEntity te = world.getTileEntity(pos);
        if(te != null && te instanceof TileCodeStorage)
        {
            TileCodeStorage storage = (TileCodeStorage) te;
            if(storage.isUnlocked())
            {
                ItemStack stack = new ItemStack(this, 1, storage.isLockable() ? 0 : 1);
                NBTTagCompound tag = new NBTTagCompound();
                tag.setTag("items", storage.handler.serializeNBT());
                stack.setTagCompound(tag);
                drops.add(stack);
            }
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te != null && te instanceof TileCodeStorage)
        {
            TileCodeStorage storage = (TileCodeStorage) te;
            //meta == 0 - lockable; meta == 1 - not lockable
            storage.setLockable(stack.getMetadata() == 0, false);
            storage.setUnlocked(true, false);
            if (stack.hasTagCompound())
            {
                NBTTagCompound tag = stack.getTagCompound();
                if (tag.hasKey("items", Constants.NBT.TAG_COMPOUND))
                {
                    storage.handler.deserializeNBT(tag.getCompoundTag("items"));
                }
            }
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(int i = 0; i < 2; i++)
        {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        int meta = 0;
        TileEntity te = world.getTileEntity(pos);
        if (te != null && te instanceof TileCodeStorage)
            meta = ((TileCodeStorage) te).isLockable() ? 0 : 1;
        return new ItemStack(this, 1, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced)
    {
        if(stack.hasTagCompound())
        {
            NBTTagCompound tag = stack.getTagCompound();
            if(tag.hasKey("items", Constants.NBT.TAG_COMPOUND))
            {
                NBTTagList itemList = tag.getCompoundTag("items").getTagList("Items", Constants.NBT.TAG_COMPOUND);
                tooltip.add(I18n.format(getUnlocalizedName() + ".tooltip", itemList.tagCount()));
            }
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, 1, 0);
        super.breakBlock(worldIn, pos, state);
    }
}