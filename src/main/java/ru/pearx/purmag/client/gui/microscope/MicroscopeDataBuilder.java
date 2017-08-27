package ru.pearx.purmag.client.gui.microscope;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by mrAppleXZ on 18.08.17 21:26.
 */
@SideOnly(Side.CLIENT)
public class MicroscopeDataBuilder
{
    private List<Entry> entries = new ArrayList<>();

    public List<Entry> getEntries()
    {
        return entries;
    }

    public void register(Entry e)
    {
        getEntries().add(e);
    }

    public List<String> build(ItemStack stack)
    {
        List<String> lst = new ArrayList<>();
        for (Entry e : entries)
        {
            e.addInformation(lst, stack);
        }
        return lst;
    }

    public void setup()
    {
        register((list, stack) ->
        {
            list.add(stack.getDisplayName());
            list.add(I18n.format("microscope.text.stackSize", stack.getMaxStackSize()));
            if (stack.getItem().isRepairable())
                list.add(I18n.format("microscope.text.repairable"));
            if (stack.isItemEnchantable())
                list.add(I18n.format("microscope.text.enchantable", stack.getItem().getItemEnchantability()));
        });
    }

    @SideOnly(Side.CLIENT)
    public interface Entry
    {
        void addInformation(List<String> list, ItemStack stack);
    }
}
