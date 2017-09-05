package ru.pearx.purmag.client.gui.recipes.crafting;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.IGuiScreen;
import ru.pearx.libmc.client.gui.controls.Control;
import ru.pearx.libmc.client.gui.drawables.ItemDrawable;
import ru.pearx.purmag.common.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Created by mrAppleXZ on 03.09.17 11:50.
 */
@SideOnly(Side.CLIENT)
public class CraftingControl extends Control
{
    @SideOnly(Side.CLIENT)
    public interface IRecipeHandler<T extends IRecipe>
    {
        /**
         * Gets the list of the recipe ingredients. Should be 9-sized list of matching ItemStacks. Use ItemStack.EMPTY for empty stacks.
         *
         * @param recipe The recipe.
         */
        List<List<ItemStack>> getInputs(T recipe);

        default ItemStack getOutput(T recipe) {
            return AbstractCraftingThings.getOutputSimple(recipe);
        }
    }

    private static Map<Class<? extends IRecipe>, IRecipeHandler> recipeHandlers = new HashMap<>();

    public static Map<Class<? extends IRecipe>, IRecipeHandler> getHandlers()
    {
        return recipeHandlers;
    }

    public static <T extends IRecipe> void registerHandler(Class<T> clazz, IRecipeHandler<T> handler)
    {
        getHandlers().put(clazz, handler);
    }

    public static boolean containsHandler(Class<? extends IRecipe> clazz)
    {
        return getHandlers().containsKey(clazz);
    }

    public static <T extends IRecipe> IRecipeHandler<T> getHandler(Class<T> clazz)
    {
        return getHandlers().get(clazz);
    }

    static
    {
        registerHandler(ShapelessOreRecipe.class, AbstractCraftingThings::getInputsShapeless);
        registerHandler(ShapelessRecipes.class, AbstractCraftingThings::getInputsShapeless);
        registerHandler(ShapedOreRecipe.class, AbstractCraftingThings::getInputsShaped);
        registerHandler(ShapedRecipes.class, AbstractCraftingThings::getInputsShaped);
    }

    //24 is an item, 4 and 8 are the margins.
    //4 + 24 + 4 + 24 + 4 + 24 + 16 + 24 + 4 = 128
    //4 + 24 + 4 + 24 + 4 + 24 + 4 = 88

    private ItemDrawable[][] inDraws;
    private ItemDrawable outDraw;

    private int[] xIn = new int[3], yIn = new int[3];
    private int xOut, yOut;

    private int mouseX, mouseY;

    public CraftingControl(IRecipe recipe)
    {
        if (containsHandler(recipe.getClass()))
        {
            setWidth(128);
            setHeight(88);

            xOut = 100;
            yOut = 32;

            for (int row = 0; row < 3; row++)
            {
                for (int column = 0; column < 3; column++)
                {
                    xIn[column] = 24 * column + 4 * (column + 1);
                    yIn[row] = 24 * row + 4 * (row + 1);
                }
            }

            IRecipeHandler handler = getHandler(recipe.getClass());

            outDraw = new ItemDrawable(handler.getOutput(recipe), 1.5f);
            List<List<ItemStack>> lst = handler.getInputs(recipe);

            inDraws = new ItemDrawable[lst.size()][];
            for (int i = 0; i < lst.size(); i++)
            {
                List<ItemStack> l = lst.get(i);
                ItemDrawable[] arr = new ItemDrawable[l.size()];
                for (int j = 0; j < l.size(); j++)
                {
                    arr[j] = new ItemDrawable(l.get(j), 1.5f);
                }
                inDraws[i] = arr;
            }
        }
        else
        {
            throw new RuntimeException("The recipe handler for " + recipe.getClass().getName() + " doesn't exists!");
        }
    }

    public CraftingControl(ResourceLocation loc)
    {
        this(ForgeRegistries.RECIPES.getValue(loc));
    }

    @Override
    public void render()
    {
        GlStateManager.enableBlend();
        DrawingTools.drawTexture(Utils.getResourceLocation("textures/gui/recipes/crafting.png"), 0, 0, getWidth(), getHeight());
        GlStateManager.disableBlend();
        IGuiScreen gs = getGuiScreen();
        for (int row = 0; row < 3; row++)
        {
            for (int column = 0; column < 3; column++)
            {
                ItemDrawable[] arr = inDraws[row * 3 + column];
                arr[(int) (System.currentTimeMillis() / 1000 % arr.length)].draw(gs, xIn[column], yIn[row]);
            }
        }
        outDraw.draw(gs, xOut, yOut);
        for (int row = 0; row < 3; row++)
        {
            for (int column = 0; column < 3; column++)
            {
                ItemDrawable[] arr = inDraws[row * 3 + column];
                ItemDrawable draw = arr[(int) (System.currentTimeMillis() / 1000 % arr.length)];
                if (!draw.stack.isEmpty())
                    draw.drawTooltip(gs, xIn[column], yIn[row], mouseX, mouseY);
            }
        }
        outDraw.drawTooltip(gs, xOut, yOut, mouseX, mouseY);
    }

    @Override
    public void mouseMove(int x, int y, int dx, int dy)
    {
        mouseX = x;
        mouseY = y;
    }
}