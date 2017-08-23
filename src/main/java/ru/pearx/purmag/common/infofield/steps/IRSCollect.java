package ru.pearx.purmag.common.infofield.steps;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.if_tablet.steps.IRSCollectRenderer;
import ru.pearx.purmag.client.gui.if_tablet.steps.IRSRenderer;
import ru.pearx.purmag.common.PMCreativeTab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 26.04.17 14:16.
 */
public class IRSCollect extends IRSBase
{
    public boolean checkNbt;
    public boolean checkMeta;
    private ItemStack stack;
    private boolean showStack;

    public IRSCollect(ItemStack stack, String unlocDesc, boolean showStack, boolean checkMeta, boolean checkNbt)
    {
        this.stack = stack;
        this.showStack = showStack;
        setUnlocalizedDescription(unlocDesc);
        this.checkMeta = checkMeta;
        this.checkNbt = checkNbt;
    }

    public IRSCollect(ItemStack stack, String unlocDesc, boolean showStack)
    {
        this(stack, unlocDesc, showStack, false, false);
    }

    public ItemStack getStack()
    {
        return stack;
    }

    public boolean isSuitable(ItemStack stack)
    {
        boolean itemCond = stack.getItem() == this.stack.getItem();
        boolean metaCond = !checkMeta || stack.getMetadata() == this.stack.getMetadata();
        boolean nbtCond = !checkNbt || this.stack.getTagCompound().equals(stack.getTagCompound());

        return itemCond && metaCond && nbtCond;
    }

    public boolean getShowStack()
    {
        return showStack;
    }

    @SideOnly(Side.CLIENT)
    public List<ItemStack> getStacksToRender()
    {
        NonNullList<ItemStack> lst = NonNullList.create();
        stack.getItem().getSubItems(stack.getItem().getCreativeTab() == null ? PMCreativeTab.INSTANCE : stack.getItem().getCreativeTab(), lst);

        ArrayList<ItemStack> out = new ArrayList<>();
        for (ItemStack stack : lst)
        {
            if (isSuitable(stack))
                out.add(stack);
        }
        return out;
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
        return new IRSCollectRenderer(this);
    }
}
