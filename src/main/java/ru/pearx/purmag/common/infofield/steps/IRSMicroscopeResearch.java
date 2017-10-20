package ru.pearx.purmag.common.infofield.steps;

import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.if_tablet.steps.IRSIngredientRenderer;
import ru.pearx.purmag.client.gui.if_tablet.steps.IRSRenderer;

/*
 * Created by mrAppleXZ on 13.10.17 17:52.
 */
public class IRSMicroscopeResearch extends IRSIngredient
{
    private boolean[][] pattern;

    public IRSMicroscopeResearch(Ingredient ingredient, boolean[][] pattern)
    {
        setPattern(pattern);
        setIngredient(ingredient);
        setUnlocalizedDescription("microscope_research");
    }

    public boolean[][] getPattern()
    {
        return pattern;
    }

    public void setPattern(boolean[][] pattern)
    {
        this.pattern = pattern;
    }

    @Override
    public String getUnlocalizedName()
    {
        return "microscope_research";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRSRenderer getRenderer()
    {
        return new IRSIngredientRenderer(this);
    }

    @Override
    public boolean shouldShowStack()
    {
        return true;
    }
}
