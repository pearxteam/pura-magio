package ru.pearx.purmag.common.recipes.crushing;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/*
 * Created by mrAppleXZ on 17.02.18 19:15.
 */
public interface IStandardCrushingRecipe extends ICrushingRecipe
{
    Ingredient getInput();
    List<CrushingOutput> getOutputs();

    @Override
    default boolean isInputMatches(ItemStack stack)
    {
        return getInput().apply(stack);
    }

    @Override
    default NonNullList<ItemStack> createOutputs(Random rand, @Nullable ChanceModifier modifier)
    {
        NonNullList<ItemStack> lst = NonNullList.create();
        for(CrushingOutput out : getOutputs())
        {
            if(rand.nextFloat() <= (modifier != null ? modifier.apply(out.getChance()) : out.getChance()))
            {
                lst.add(out.getStack().copy());
            }
        }
        return lst;
    }
}
