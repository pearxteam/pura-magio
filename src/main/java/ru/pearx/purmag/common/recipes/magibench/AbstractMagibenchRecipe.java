package ru.pearx.purmag.common.recipes.magibench;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.inventory.ContainerMagibench;

/*
 * Created by mrAppleXZ on 02.11.17 18:51.
 */
public abstract class AbstractMagibenchRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{
    private int tier;
    private String entry;

    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier)
    {
        this.tier = tier;
    }

    public String getEntry()
    {
        return entry;
    }

    public void setEntry(String entry)
    {
        this.entry = entry;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        if(inv instanceof ContainerMagibench.Crafting)
        {
            ContainerMagibench.Crafting craft = (ContainerMagibench.Crafting) inv;
            return craft.getMagibench().tier.getTier() >= getTier() &&
                    craft.getMagibench().inv.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).isFullyUnlocked(getEntry());
        }
        return false;
    }
}
