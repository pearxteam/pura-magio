package ru.pearx.purmag.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.items.ItemRegistry;
import ru.pearx.purmag.common.recipes.ingredients.IngredientNBT;
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
        register(new ShapedOreRecipe(null, ItemRegistry.unfinished_mortar_and_pestle,
                "  |",
                "BcB",
                " B ",
                'c', Items.CLAY_BALL, 'B', Blocks.CLAY, '|', "stickWood"
        ), e.getRegistry());

        register(new ShapelessOreRecipe(null, new ItemStack(ItemRegistry.pyroblend, 2),
                ItemRegistry.verda_wing,
                ItemRegistry.brulanta_flower,
                new IngredientNBT(SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal_shard), "flame")),
                ItemRegistry.mortar_and_pestle
        ), e.getRegistry());

        GameRegistry.addSmelting(new ItemStack(ItemRegistry.unfinished_mortar_and_pestle), new ItemStack(ItemRegistry.mortar_and_pestle), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ItemRegistry.beetle_meat), new ItemStack(ItemRegistry.beetle_meat, 1, 1), 0.5f);
    }

    private static int id = 0;
    private static ResourceLocation getRL()
    {
        return Utils.getResourceLocation("recipe_" + (id++));
    }

    private static void register(IRecipe recipe, IForgeRegistry<IRecipe> reg)
    {
        reg.register(recipe.setRegistryName(getRL()));
    }
}
