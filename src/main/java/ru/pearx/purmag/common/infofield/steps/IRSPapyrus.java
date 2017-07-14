package ru.pearx.purmag.common.infofield.steps;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.common.items.ItemRegistry;

/**
 * Created by mrAppleXZ on 07.06.17 21:16.
 */
public class IRSPapyrus extends IRSBase
{
    public String id;

    public IRSPapyrus(String id)
    {
        this.id = id;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getDescription()
    {
        return I18n.format("if_step." + getUnlocalizedName() + ".desc", ItemRegistry.papyrus.getPapyrus(id).getDisplayName());
    }

    public boolean isSuitable(ItemStack stack)
    {
        return ItemRegistry.papyrus.getId(stack).equals(id);
    }
}
