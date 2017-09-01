package ru.pearx.purmag.common.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.items.ItemRegistry;

/*
 * Created by mrAppleXZ on 14.08.17 12:43.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID)
public class RecipeRegistry
{
    public static void onRecipeRegistry(RegistryEvent.Register<IRecipe> e)
    {
        GameRegistry.addSmelting(new ItemStack(ItemRegistry.beetle_meat), new ItemStack(ItemRegistry.beetle_meat, 1, 1), 0.5f);
    }
}
