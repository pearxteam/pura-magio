package net.pearx.purmag.client.guis.drawables;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 23.04.17 20:15.
 */
@SideOnly(Side.CLIENT)
public class BigItemDrawable extends ItemDrawable
{
    public BigItemDrawable(ItemStack stack)
    {
        super(stack, 1.5f);
    }
}
