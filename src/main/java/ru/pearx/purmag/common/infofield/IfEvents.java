package ru.pearx.purmag.common.infofield;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.tuple.Pair;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.infofield.steps.IRSCollect;
import ru.pearx.purmag.common.infofield.steps.IRSKillEntity;

/**
 * Created by mrAppleXZ on 25.06.17 11:57.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID)
public class IfEvents
{
    @SubscribeEvent
    public static void onPickup(EntityItemPickupEvent e)
    {
        if (e.getEntityPlayer() instanceof EntityPlayerMP)
        {
            EntityPlayerMP p = (EntityPlayerMP) e.getEntityPlayer();
            for (Pair<IfEntry, IRSCollect> pair : PurMag.INSTANCE.getIfRegistry().getAllResearchableSteps(IRSCollect.class, p))
            {
                if (pair.getRight().isSuitable(e.getItem().getItem()))
                {
                    p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).unlockStepAndSync(pair.getLeft().getId(), p);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onKillEntity(LivingDeathEvent e)
    {
        if (e.getSource().getTrueSource() instanceof EntityPlayerMP)
        {
            EntityPlayerMP p = (EntityPlayerMP) e.getSource().getTrueSource();
            for (Pair<IfEntry, IRSKillEntity> pair : PurMag.INSTANCE.getIfRegistry().getAllResearchableSteps(IRSKillEntity.class, p))
            {
                if (pair.getRight().clazz == e.getEntity().getClass())
                {
                    p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).unlockStepAndSync(pair.getLeft().getId(), p);
                }
            }
        }
    }
}
