package ru.pearx.purmag.common.recipes.ingredients;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 02.09.17 14:36.
 */
public class IngredientNBT extends Ingredient
{
    public IngredientNBT(ItemStack... stacks)
    {
        super(stacks);
    }

    @Override
    public boolean apply(@Nullable ItemStack used)
    {
        if (used != null)
        {
            for (ItemStack stack : getMatchingStacks())
            {
                if (stack.getItem() == used.getItem())
                {
                    int i = stack.getMetadata();
                    if (i == 32767 || i == used.getMetadata())
                    {
                        if (used.getTagCompound() != null && stack.getTagCompound() != null)
                        {
                            NBTTagCompound tagStack = stack.getTagCompound();
                            NBTTagCompound tagUsed = used.getTagCompound();
                            for (String s : tagStack.getKeySet())
                            {
                                if (!tagUsed.hasKey(s))
                                    return false;
                                if (!tagUsed.getTag(s).equals(tagStack.getTag(s)))
                                    return false;
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
