package net.pearx.purmag.common.infofield.steps;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.if_tablet.steps.IRSReadPapyrusRenderer;
import net.pearx.purmag.client.guis.if_tablet.steps.IRSRenderer;
import net.pearx.purmag.common.items.ItemRegistry;

/**
 * Created by mrAppleXZ on 30.05.17 9:33.
 */
public class IRSReadPapyrus extends IRSBase
{
    public String id;

    public IRSReadPapyrus(String id)
    {
        this.id = id;
    }

    @Override
    public String getUnlocalizedDescription()
    {
        return "read_papyrus";
    }

    @Override
    public String getUnlocalizedName()
    {
        return "read_papyrus";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getDescription()
    {
        return I18n.format("if_step." + getUnlocalizedName() + ".desc", ItemRegistry.papyrus.getPapyrus(id).getDisplayName());
    }

    public boolean isSuitable(ItemStack stack)
    {
        if(stack.hasTagCompound())
        {
            if(stack.getTagCompound().hasKey("papyrus_id"))
            {
                if(stack.getTagCompound().getString("papyrus_id").equals(id))
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRSRenderer getRenderer()
    {
        return new IRSReadPapyrusRenderer(this);
    }
}
