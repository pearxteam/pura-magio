package ru.pearx.purmag.common.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.items.ItemRegistry;
import ru.pearx.purmag.common.sip.SipUtils;

/*
 * Created by mrAppleXZ on 14.08.17 12:43.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID)
public class RecipeRegistry
{
    @SubscribeEvent
    public static void onRecipeRegistry(RegistryEvent.Register<IRecipe> e)
    {
        //todo make normal sip recipe, so only flame crystal must be used in the recipe.
        e.getRegistry().register(new ShapelessOreRecipe(null, ItemRegistry.pyroblend, new ItemStack(ItemRegistry.verda_wing), new ItemStack(ItemRegistry.brulanta_flower), SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal_shard), "flame")).setRegistryName(getRL()));
        GameRegistry.addSmelting(new ItemStack(ItemRegistry.beetle_meat), new ItemStack(ItemRegistry.beetle_meat, 1, 1), 0.5f);
    }

    private static int id = 0;
    private static ResourceLocation getRL()
    {
        return Utils.getResourceLocation("recipe_" + (id++));
    }
}
