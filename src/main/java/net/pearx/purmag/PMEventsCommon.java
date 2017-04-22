package net.pearx.purmag;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by mrAppleXZ on 21.04.17 16:41.
 */
public class PMEventsCommon
{
    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent e)
    {
        if(e.getItemStack().hasTagCompound())
        {
            if(e.getItemStack().getTagCompound().hasKey("sipStored"))
            {
                e.getToolTip().add(I18n.format("sip_tooltip") + e.getItemStack().getTagCompound().getInteger("sipStored"));
            }
        }
    }
}
