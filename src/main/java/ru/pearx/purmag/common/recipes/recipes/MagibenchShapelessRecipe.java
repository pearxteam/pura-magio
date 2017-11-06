package ru.pearx.purmag.common.recipes.recipes;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import ru.pearx.libmc.common.ItemStackUtils;

import javax.annotation.Nonnull;

/*
 * Created by mrAppleXZ on 02.11.17 16:51.
 */
public class MagibenchShapelessRecipe extends AbstractMagibenchRecipe
{
    private ItemStack out;
    private NonNullList<Ingredient> ingredients;

    public MagibenchShapelessRecipe(Block result, Object... recipe)
    {
        this(new ItemStack(result), recipe);
    }

    public MagibenchShapelessRecipe(Item result, Object... recipe)
    {
        this(new ItemStack(result), recipe);
    }

    public MagibenchShapelessRecipe(NonNullList<Ingredient> input, @Nonnull ItemStack result)
    {
        out = result.copy();
        ingredients = input;
    }

    public MagibenchShapelessRecipe(@Nonnull ItemStack result, Object... recipe)
    {
        out = result.copy();
        for (Object in : recipe)
        {
            Ingredient ing = CraftingHelper.getIngredient(in);
            if (ing != null)
            {
                ingredients.add(ing);
            }
            else
            {
                StringBuilder ret = new StringBuilder("Invalid shapeless  recipe: ");
                for (Object tmp :  recipe)
                {
                    ret.append(tmp).append(", ");
                }
                ret.append(out);
                throw new RuntimeException(ret.toString());
            }
        }
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        return super.matches(inv, worldIn) && ItemStackUtils.isCraftingMatrixMatchesShapeless(inv, ingredients);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        return out.copy();
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return width * height >= ingredients.size();
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return out;
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return ingredients;
    }
}
