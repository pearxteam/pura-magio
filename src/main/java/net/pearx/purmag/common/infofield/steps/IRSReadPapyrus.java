package net.pearx.purmag.common.infofield.steps;

import net.minecraft.item.ItemStack;

/**
 * Created by mrAppleXZ on 30.05.17 9:33.
 */
public class IRSReadPapyrus extends IRSBase
{
    private String id;

    public IRSReadPapyrus(String id)
    {
        this.id = id;
    }

    @Override
    public String getUnlocalizedName()
    {
        return "read_papyrus";
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
}
