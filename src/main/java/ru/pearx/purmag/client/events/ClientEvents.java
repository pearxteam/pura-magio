package ru.pearx.purmag.client.events;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import ru.pearx.purmag.PurMag;

/**
 * Created by mrAppleXZ on 25.06.17 11:54.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID, value = Side.CLIENT)
public class ClientEvents
{
    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent e)
    {
        //todo [RELEASE] REMOVE DEBUG PRINT!
        int[] ids = OreDictionary.getOreIDs(e.getItemStack());
        for (int id : ids)
        {
            e.getToolTip().add(OreDictionary.getOreName(id));
        }
    }
}
