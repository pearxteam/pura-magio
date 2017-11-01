package ru.pearx.purmag.common.recipes.recipes;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IShapedRecipe;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import ru.pearx.libmc.common.ItemStackUtils;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.inventory.ContainerMagibench;
import ru.pearx.purmag.common.magibench.MagibenchRegistry;

import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 30.10.17 18:11.
 */
public class MagibenchRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IShapedRecipe
{
    private int tier;
    private ItemStack out;
    private String entry;
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
        this.entry = entryId;
        this.tier = minTier;
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
        if(inv instanceof ContainerMagibench.Crafting)
        {
            ContainerMagibench.Crafting craft = (ContainerMagibench.Crafting) inv;
            return craft.getMagibench().tier.getTier() >= tier &&
                    craft.getMagibench().inv.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).isFullyUnlocked(entry) &&
                    ItemStackUtils.isCraftingMatrixMatches(inv, getRecipeWidth(), getRecipeHeight(), ingredients, mirrored);
        }
        return false;
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
