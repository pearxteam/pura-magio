package net.pearx.purmag.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.ClientUtils;

/**
 * Created by mrAppleXZ on 25.06.17 15:44.
 */
public class ItemFoodBakeable extends ItemFoodBase
{
    private int foodB;
    private float saturationB;

    public ItemFoodBakeable(int food, float saturation, boolean isWolfsFood, int foodB, float saturationB)
    {
        super(food, saturation, isWolfsFood);
        this.foodB = foodB;
        this.saturationB = saturationB;
        setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(isInCreativeTab(tab))
        {
            items.add(new ItemStack(this));
            items.add(new ItemStack(this, 1, 1));
        }
    }

    @Override
    public int getHealAmount(ItemStack stack)
    {
        return stack.getMetadata() == 1 ? foodB : super.getHealAmount(stack);
    }

    @Override
    public float getSaturationModifier(ItemStack stack)
    {
        return stack.getMetadata() == 1 ? saturationB : super.getSaturationModifier(stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        ClientUtils.setModelLocation(this, 0, "");
        ClientUtils.setModelLocation(this, 1, ".baked");
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return stack.getMetadata() == 1 ? getUnlocalizedName() + ".baked" : getUnlocalizedName();
    }
}
