package ru.pearx.purmag.common.recipes.crushing;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import ru.pearx.purmag.common.tiles.TileStoneCrusher;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

/*
 * Created by mrAppleXZ on 15.02.18 13:27.
 */
public interface ICrushingRecipe
{
    interface ChanceModifier
    {
        float apply(float f);
    }
    boolean isInputMatches(ItemStack stack);
    NonNullList<ItemStack> createOutputs(Random rand, @Nullable ChanceModifier modifier);
}
