package ru.pearx.purmag.common.infofield.steps;

import net.minecraft.item.crafting.Ingredient;

/*
 * Created by mrAppleXZ on 13.10.17 18:38.
 */
public abstract class IRSIngredient extends IRSBase
{
    private Ingredient ing;

    public Ingredient getIngredient()
    {
        return ing;
    }

    public void setIngredient(Ingredient ing)
    {
        this.ing = ing;
    }

    public abstract boolean shouldShowStack();
}
