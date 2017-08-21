package ru.pearx.purmag.client.infofield.pages;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPFurnaceRenderer;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPRenderer;

/*
 * Created by mrAppleXZ on 14.08.17 12:53.
 */
@SideOnly(Side.CLIENT)
public class IfPageFurnace implements IIfPage
{
    /**
     * Item list with inputs. Recommended size: 1-3.
     */
    private NonNullList<ItemStack> inputs;

    public IfPageFurnace(ItemStack... inputs)
    {
        this.inputs = NonNullList.from(ItemStack.EMPTY, inputs);
    }

    @Override
    public IPRenderer getRenderer()
    {
        return new IPFurnaceRenderer(this);
    }

    public NonNullList<ItemStack> getInputs()
    {
        return inputs;
    }

    public void setInputs(NonNullList<ItemStack> inputs)
    {
        this.inputs = inputs;
    }

    public ItemStack getInput(int i)
    {
        return inputs.get(i);
    }

    public ItemStack getOutput(int i)
    {
        return FurnaceRecipes.instance().getSmeltingResult(getInput(i));
    }


}
