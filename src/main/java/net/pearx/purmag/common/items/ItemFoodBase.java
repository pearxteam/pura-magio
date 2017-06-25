package net.pearx.purmag.common.items;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.ClientUtils;
import net.pearx.purmag.client.models.IModelProvider;
import net.pearx.purmag.common.PMCreativeTab;

/**
 * Created by mrAppleXZ on 25.06.17 14:50.
 */
public class ItemFoodBase extends ItemFood implements IModelProvider
{
    public ItemFoodBase(int food, float saturation, boolean isWolfsFood)
    {
        super(food, saturation, isWolfsFood);
        setCreativeTab(PMCreativeTab.instance);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        ClientUtils.setModelLocation(this);
    }

    @Override
    public String getUnlocalizedName()
    {
        return super.unlocalizedName == null ? "item." + getRegistryName().getResourcePath() : super.getUnlocalizedName();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return getUnlocalizedName();
    }
}
