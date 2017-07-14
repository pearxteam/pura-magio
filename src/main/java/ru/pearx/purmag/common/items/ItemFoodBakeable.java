package ru.pearx.purmag.common.items;

import ru.pearx.purmag.common.PMCreativeTab;

/*
 * Created by mrAppleXZ on 14.07.17 11:50.
 */
public class ItemFoodBakeable extends ru.pearx.libmc.common.items.ItemFoodBakeable
{
    public ItemFoodBakeable(int food, float saturation, boolean isWolfsFood, int foodB, float saturationB)
    {
        super(food, saturation, isWolfsFood, foodB, saturationB);
        setCreativeTab(PMCreativeTab.INSTANCE);
    }
}
