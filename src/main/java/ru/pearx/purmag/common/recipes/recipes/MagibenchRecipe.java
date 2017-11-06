package ru.pearx.purmag.common.recipes.recipes;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IShapedRecipe;
import ru.pearx.libmc.common.ItemStackUtils;

/*
 * Created by mrAppleXZ on 30.10.17 18:11.
 */
public class MagibenchRecipe extends AbstractMagibenchRecipe implements IShapedRecipe
{
    private ItemStack out;
    private int width, height;
    private boolean mirrored;
    private NonNullList<Ingredient> ingredients;

    public MagibenchRecipe(Item out, int minTier, String entryId, Object... recipe)
    {
        this(new ItemStack(out), minTier, entryId, CraftingHelper.parseShaped(recipe));
    }

    public MagibenchRecipe(Block out, int minTier, String entryId, Object... recipe)
    {
        this(new ItemStack(out), minTier, entryId, CraftingHelper.parseShaped(recipe));
    }

    public MagibenchRecipe(ItemStack out, int minTier, String entryId, Object... recipe)
    {
        this(out, minTier, entryId, CraftingHelper.parseShaped(recipe));
    }

    public MagibenchRecipe(ItemStack out, int minTier, String entryId, CraftingHelper.ShapedPrimer primer)
    {
        setEntry(entryId);
        setTier(minTier);
        this.out = out.copy();
        this.width = primer.width;
        this.height = primer.height;
        this.ingredients = primer.input;
        this.mirrored = primer.mirrored;
    }

    @Override
    public int getRecipeWidth()
    {
        return width;
    }

    @Override
    public int getRecipeHeight()
    {
        return height;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        return super.matches(inv, worldIn) && ItemStackUtils.isCraftingMatrixMatches(inv, getRecipeWidth(), getRecipeHeight(), ingredients, mirrored);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        return out.copy();
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return width >= getRecipeWidth() && height >= getRecipeHeight();
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
