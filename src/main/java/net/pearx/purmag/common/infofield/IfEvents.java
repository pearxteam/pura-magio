package net.pearx.purmag.common.infofield;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import net.pearx.purmag.common.infofield.steps.IIfResearchStep;
import net.pearx.purmag.common.infofield.steps.IRSCollect;

/**
 * Created by mrAppleXZ on 25.06.17 11:57.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID)
public class IfEvents
{
    @SubscribeEvent
    public static void onPickup(EntityItemPickupEvent e)
    {
        if(e.getEntityPlayer() instanceof EntityPlayerMP)
        {
            EntityPlayerMP p = (EntityPlayerMP)e.getEntityPlayer();
            IIfEntryStore store = p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null);
            for (IfEntry entr : PurMag.instance.if_registry.entries)
            {
                int steps = store.getSteps(entr.getId());
                if(steps < entr.getSteps().size())
                {
                    IIfResearchStep step = entr.getSteps().get(steps);
                    if (step instanceof IRSCollect)
                    {
                        if (entr.isAvailableToResearch(p))
                        {
                            IRSCollect s = (IRSCollect) step;
                            if (s.isSuitable(e.getItem().getItem()))
                            {
                                store.unlockStepAndSync(entr.getId(), p);
                            }
                        }
                    }
                }
            }
        }
    }
}
