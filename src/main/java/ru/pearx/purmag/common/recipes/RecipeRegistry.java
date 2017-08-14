package ru.pearx.purmag.common.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.pearx.purmag.common.items.ItemRegistry;

/*
 * Created by mrAppleXZ on 14.08.17 12:43.
 */
public class RecipeRegistry
{
    public static void setup()
    {
        GameRegistry.addSmelting(new ItemStack(ItemRegistry.beetle_meat), new ItemStack(ItemRegistry.beetle_meat, 1, 1), 0.5f);
    }
}
