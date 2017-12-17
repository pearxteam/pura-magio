package ru.pearx.purmag.common.compat.jei.magibench;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.magibench.MagibenchRegistry;

/*
 * Created by mrAppleXZ on 02.11.17 18:39.
 */
public class MagibenchRecipeCategory implements IRecipeCategory<AbstractMagibenchRecipeWrapper>
{
    public static final String ID = Utils.gRL("magibench").toString();
    public static final int WIDTH = 144;
    public static final int HEIGHT = 94;

    private IDrawable bg;

    public MagibenchRecipeCategory(IGuiHelper help)
    {
        this.bg = help.createBlankDrawable(WIDTH, HEIGHT);
    }

    @Override
    public String getUid()
    {
        return ID;
    }

    @Override
    public String getTitle()
    {
        return I18n.translateToLocal("misc.jei.magibench.name");
    }

    @Override
    public String getModName()
    {
        return PurMag.NAME;
    }

    @Override
    public IDrawable getBackground()
    {
        return bg;
    }

    @Override
    public void setRecipe(IRecipeLayout lay, AbstractMagibenchRecipeWrapper wrapper, IIngredients ingredients)
    {
        if(wrapper instanceof MagibenchShapelessRecipeWrapper)
            lay.setShapeless();
        IGuiItemStackGroup st = lay.getItemStacks();
        MagibenchRegistry.Tier t = PurMag.INSTANCE.getMagibenchRegistry().getTier(wrapper.recipe.getTier());
        int index = 0;
        st.init(index, false, t.getGuiResultX() - t.getGuiJeiStartX() - 1, t.getGuiResultY() - t.getGuiJeiStartY() - 1);
        st.set(index, ingredients.getOutputs(ItemStack.class).get(0));
        index++;

        for(int y = 0; y < t.getHeight(); y++)
        {
            for(int x = 0; x < t.getWidth(); x++)
            {
                st.init(index, true, t.getGuiGridX() - t.getGuiJeiStartX() + 18*x - 1, t.getGuiGridY() - t.getGuiJeiStartY() + 18*y - 1);
                st.set(index, ingredients.getInputs(ItemStack.class).get(index - 1));
                index++;
            }
        }
    }
}
