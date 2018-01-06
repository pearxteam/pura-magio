package ru.pearx.purmag.common.items.tinkering_kit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.ClientUtils;
import ru.pearx.purmag.common.items.base.ItemBase;

/*
 * Created by mrAppleXZ on 03.01.18 18:10.
 */
public class ItemIronTinkeringKitMold extends ItemBase
{
    public static final int STAGES = 4;

    public ItemIronTinkeringKitMold()
    {
        super("iron_tinkering_kit_mold");
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return getUnlocalizedName() + "." + stack.getMetadata();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(isInCreativeTab(tab))
        {
            for(int i = 0; i < STAGES; i++)
            {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        for(int i = 0; i < STAGES; i++)
        {
            ClientUtils.setModelLocation(this, i, "_" + i);
        }
    }
}
