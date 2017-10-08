package ru.pearx.purmag.common.loot_tables;

import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;

/*
 * Created by mrAppleXZ on 25.08.17 10:52.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID)
public class LootEvents
{
    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent e)
    {
        if (e.getName().equals(Utils.getResourceLocation("chests/lab")))
            PurMag.INSTANCE.getPapyrusRegistry().addNotImportantToTable(e.getTable());
    }
}
