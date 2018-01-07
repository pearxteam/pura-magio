package ru.pearx.purmag.common.recipes.recipes;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import ru.pearx.libmc.common.ItemStackUtils;

import javax.annotation.Nonnull;

/*
 * Created by mrAppleXZ on 02.11.17 16:51.
 */
public class MagibenchShapelessRecipe extends AbstractMagibenchRecipe
{
    private ItemStack out;
    private NonNullList<Ingredient> ingredients = NonNullList.create();
    private boolean simple;

    public MagibenchShapelessRecipe(Block result, int minTier, String entryId, Object... recipe)
    {
        this(new ItemStack(result), minTier, entryId, recipe);
    }

    public MagibenchShapelessRecipe(Item result, int minTier, String entryId, Object... recipe)
    {
        this(new ItemStack(result), minTier, entryId, recipe);
    }

    public MagibenchShapelessRecipe(NonNullList<Ingredient> input, @Nonnull ItemStack result, int minTier, String entryId)
    {
        out = result.copy();
        ingredients = input;
        setTier(minTier);
        setEntry(entryId);
    }

    public MagibenchShapelessRecipe(@Nonnull ItemStack result, int minTier, String entryId, Object... recipe)
    {
        setTier(minTier);
        setEntry(entryId);
        out = result.copy();
        for (Object in : recipe)
        {
            Ingredient ing = CraftingHelper.getIngredient(in);
            if (ing != null)
            {
                ingredients.add(ing);
                this.simple &= ing.isSimple();
            }
            else
            {
                StringBuilder ret = new StringBuilder("Invalid shapeless ore recipe: ");
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
        return super.matches(inv, worldIn) && ItemStackUtils.isCraftingMatrixMatchesShapeless(inv, ingredients, this, simple);
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
