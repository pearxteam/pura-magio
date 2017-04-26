package net.pearx.purmag.common.infofield.steps;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

/**
 * Created by mrAppleXZ on 26.04.17 14:16.
 */
public class IRSCollectSimple extends IRSBase implements IIfResearchStepCollect
{
    public ItemStack stack;
    public boolean showStack;

    public IRSCollectSimple(ItemStack stack, String unloc, boolean showStack)
    {
        this.stack = stack;
        this.showStack = showStack;
        setUnlocalizedName(unloc);
    }

    @Override
    public ItemStack getDisplayStack()
    {
        return stack;
    }

    @Override
    public boolean isSuitable(ItemStack stack)
    {
        return stack.getItem() == this.stack.getItem() && stack.getMetadata() == this.stack.getMetadata();
    }

    @Override
    public boolean showStack()
    {
        return showStack;
    }
}
