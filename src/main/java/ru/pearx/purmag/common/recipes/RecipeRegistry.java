package ru.pearx.purmag.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
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
import ru.pearx.purmag.common.recipes.recipes.MagibenchRecipe;
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
        //SIMPLE CRAFTING TABLE
        register("painting_kit", new ShapelessOreRecipe(null, new ItemStack(ItemRegistry.painting_kit),
                "stickWood", "string", Blocks.WOODEN_PRESSURE_PLATE, "dyeBlack", "dyeWhite", "dyeBlue", "dyeYellow", "dyeRed"
        ), e.getRegistry());

        register("gray_paper_pack", new ShapelessOreRecipe(null, new ItemStack(ItemRegistry.gray_paper_pack),
                "paper", "paper", "paper", "paper", "paper", "paper", "dyeGray", "dyeGray"
        ), e.getRegistry());
        register("wooden_tablet", new ShapedOreRecipe(null, new ItemStack(ItemRegistry.if_tablet, 1, 0),
                "SSS",
                "PPP",
                " K ",
                'S', "slabWood", 'P', ItemRegistry.gray_paper_pack, 'K', ItemRegistry.painting_kit
        ), e.getRegistry());
        register("simple_magibench", new ShapedOreRecipe(null, new ItemStack(ItemRegistry.magibench, 1, 0),
                "NNN",
                "WWW",
                " P ",
                'N', "nuggetGold", 'W', "slabWood", 'P', "plankWood"
        ), e.getRegistry());

        //MAGIBENCH
        register("pyroblend", new ShapelessOreRecipe(null, new ItemStack(ItemRegistry.pyroblend, 2),
                ItemRegistry.verda_wing,
                ItemRegistry.brulanta_flower,
                new IngredientNBT(SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal_shard), "flame")),
                ItemRegistry.mortar_and_pestle
        ), e.getRegistry());
        register("unf_mortar_and_pestle", new MagibenchRecipe(ItemRegistry.unfinished_mortar_and_pestle, 0, "mortar_and_pestle",
                "  |",
                "BcB",
                " B ",
                'c', Items.CLAY_BALL, 'B', Blocks.CLAY, '|', "stickWood"
        ), e.getRegistry());
        register("rope_coil", new MagibenchRecipe(new ItemStack(ItemRegistry.rope_coil, 1, 0), 0, "stone_crusher",
                "SSS",
                "SWS",
                "SSS",
                'S', "string", 'W', "plankWood"
        ), e.getRegistry());
        register("cog_rope_coil", new MagibenchRecipe(new ItemStack(ItemRegistry.rope_coil, 1, 1), 0, "stone_crusher",
                " | ",
                "|C|",
                " | ",
                '|', "stickWood", 'C', new ItemStack(ItemRegistry.rope_coil, 1, 0)
        ), e.getRegistry());

        GameRegistry.addSmelting(new ItemStack(ItemRegistry.unfinished_mortar_and_pestle), new ItemStack(ItemRegistry.mortar_and_pestle), 0.1f);
        GameRegistry.addSmelting(new ItemStack(ItemRegistry.beetle_meat), new ItemStack(ItemRegistry.beetle_meat, 1, 1), 0.5f);
    }

    private static void register(String name, IRecipe recipe, IForgeRegistry<IRecipe> reg)
    {
        reg.register(recipe.setRegistryName(Utils.gRL(name)));
    }
}
