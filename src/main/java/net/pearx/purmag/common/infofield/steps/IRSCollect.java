package net.pearx.purmag.common.infofield.steps;

import net.minecraft.util.text.translation.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.GuiDrawableRegistry;
import net.pearx.purmag.client.guis.drawables.AnimatedDrawable;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;
import net.pearx.purmag.client.guis.if_tablet.steps.IRSCollectRenderer;
import net.pearx.purmag.client.guis.if_tablet.steps.IRSRenderer;

/**
 * Created by mrAppleXZ on 26.04.17 14:16.
 */
public class IRSCollect extends IRSBase implements IIfResearchStepCollect
{
    public ItemStack stack;
    public boolean showStack;

    public IRSCollect(ItemStack stack, String unlocDesc, boolean showStack)
    {
        this.stack = stack;
        this.showStack = showStack;
        setUnlocalizedDescription(unlocDesc);
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

    @Override
    public String getUnlocalizedName()
    {
        return "collect";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRSRenderer getRenderer()
    {
        return new IRSCollectRenderer();
    }
}
