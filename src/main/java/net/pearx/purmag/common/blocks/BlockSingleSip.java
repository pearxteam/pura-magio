package net.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.blocks.properties.PropertySipType;
import net.pearx.purmag.common.items.ItemRegistry;
import net.pearx.purmag.common.items.ItemUtils;
import net.pearx.purmag.common.sip.SipType;
import net.pearx.purmag.common.sip.SipTypeRegistry;
import org.lwjgl.input.Mouse;

/**
 * Created by mrAppleXZ on 12.05.17 22:00.
 */
public class BlockSingleSip extends BlockBase
{
    public static final PropertySipType SIPTYPE = PropertySipType.create();

    public BlockSingleSip(Material materialIn)
    {
        super(materialIn);
        setDefaultState(getBlockState().getBaseState().withProperty(SIPTYPE, SipTypeRegistry.DEFAULT));
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return PurMag.instance.sip.getType(state.getValue(SIPTYPE)).getId();
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list)
    {
        for(SipType t : PurMag.instance.sip.types)
        {
            list.add(new ItemStack(itemIn, 1, t.getId()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(SIPTYPE, PurMag.instance.sip.getType(meta).getName());
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return PurMag.instance.sip.getType(state.getValue(SIPTYPE)).getId();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, SIPTYPE);
    }
}
