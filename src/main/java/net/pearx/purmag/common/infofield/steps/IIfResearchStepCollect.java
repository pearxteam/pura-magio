package net.pearx.purmag.common.infofield.steps;

import net.minecraft.item.ItemStack;

/**
 * Created by mrAppleXZ on 25.04.17 16:15.
 */
public interface IIfResearchStepCollect extends IIfResearchStep
{
    /**
     * Gets an ItemStack that will be displayed on the research page. Return null if showStack() is always returns false.
     */
    ItemStack getDisplayStack();

    /**
     * Is given ItemStack suitable for this research step?
     * @param stack ItemStack to check.
     * @return If true - this ItemStack is pretty suitable and research step can be completed.
     */
    boolean isSuitable(ItemStack stack);

    /**
     * Does the research step page shows an ItemStack and its name?
     * @return If true - the step page will show ItemStack's icon and its display name.
     */
    boolean showStack();
}
