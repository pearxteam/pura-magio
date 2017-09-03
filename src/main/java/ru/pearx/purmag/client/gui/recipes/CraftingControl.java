package ru.pearx.purmag.client.gui.recipes;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.IGuiScreen;
import ru.pearx.libmc.client.gui.controls.Control;
import ru.pearx.libmc.client.gui.drawables.IGuiDrawable;
import ru.pearx.libmc.client.gui.drawables.ItemDrawable;
import ru.pearx.purmag.common.items.ItemRegistry;

import java.util.*;

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
         * @param recipe The recipe.
         */
        List<List<ItemStack>> getInputs(T recipe);
        ItemStack getOutput(T recipe);
    }

    private static Map<Class<? extends IRecipe>, IRecipeHandler> recipeHandlers = new HashMap<>();

    public static Map<Class<? extends IRecipe>, IRecipeHandler> getHandlers()
    {
        return recipeHandlers;
    }

    public static <T extends IRecipe> void registerHandler(Class<T> clazz,  IRecipeHandler<T> handler)
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
        registerHandler(ShapelessOreRecipe.class, new IRecipeHandler<ShapelessOreRecipe>()
        {
            @Override
            public List<List<ItemStack>> getInputs(ShapelessOreRecipe recipe)
            {
                List<List<ItemStack>> lst = new ArrayList<>();
                List<Ingredient> ings = recipe.getIngredients();
                for (int i = 0; i < 9; i++)
                {
                    List<ItemStack> matching = new ArrayList<>();
                    if (i < ings.size())
                    {
                        Ingredient ing = ings.get(i);
                        for(ItemStack stack : ing.getMatchingStacks())
                        {
                            NonNullList<ItemStack> subs = NonNullList.create();
                            stack.getItem().getSubItems(stack.getItem().getCreativeTab() == null ? CreativeTabs.SEARCH : stack.getItem().getCreativeTab(), subs);
                            for(ItemStack sub : subs)
                            {
                                if(ing.apply(sub))
                                    matching.add(sub);
                            }
                        }
                    }
                    else
                        matching.add(ItemStack.EMPTY);
                    lst.add(matching);
                }
                return lst;
            }

            @Override
            public ItemStack getOutput(ShapelessOreRecipe recipe)
            {
                return recipe.getRecipeOutput();
            }
        });
    }

    //16 is an item, 4 is a margin. it's very simple, yea?
    //4 + 16 + 4 + 16 + 4 + 16 + 8 + 16 + 4 = 88
    //4 + 16 + 4 + 16 + 4 + 16 + 4 = 64

    protected IRecipe recipe;

    protected ItemDrawable[][] inDraws;
    protected ItemDrawable outDraw;

    protected int[][] xIn = new int[3][3], yIn = new int[3][3];
    protected int xOut, yOut;

    protected int mouseX, mouseY;

    public CraftingControl(IRecipe recipe)
    {
        if(containsHandler(recipe.getClass()))
        {
            this.recipe = recipe;

            setWidth(88);
            setHeight(64);

            xOut = 68;
            yOut = 24;

            for(int row = 0; row < 3; row++)
            {
                for(int column = 0; column < 3; column++)
                {
                    xIn[row][column] = 16 * column + 4 * (column + 1);
                    yIn[row][column] = 16 * row + 4 * (row + 1);
                }
            }

            IRecipeHandler handler = getHandler(recipe.getClass());

            outDraw = new ItemDrawable(handler.getOutput(recipe));
            List<List<ItemStack>> lst = handler.getInputs(recipe);

            inDraws = new ItemDrawable[lst.size()][];
            for(int i = 0; i < lst.size(); i++)
            {
                List<ItemStack> l = lst.get(i);
                ItemDrawable[] arr = new ItemDrawable[l.size()];
                for (int j = 0; j < l.size(); j++)
                {
                    arr[j] = new ItemDrawable(l.get(j));
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
        //todo render the texture here
        IGuiScreen gs = getGuiScreen();
        for(int row = 0; row < 3; row++)
        {
            for(int column = 0; column < 3; column++)
            {
                ItemDrawable[] arr = inDraws[row * 3 + column];
                arr[(int)(System.currentTimeMillis() / 1000 % arr.length)].draw(gs, xIn[row][column], yIn[row][column]);
            }
        }
        outDraw.draw(gs, xOut, yOut);
        for(int row = 0; row < 3; row++)
        {
            for (int column = 0; column < 3; column++)
            {
                ItemDrawable[] arr = inDraws[row * 3 + column];
                ItemDrawable draw = arr[(int)(System.currentTimeMillis() / 1000 % arr.length)];
                if(!draw.stack.isEmpty())
                    draw.drawTooltip(gs, xIn[row][column], yIn[row][column], mouseX, mouseY);
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
