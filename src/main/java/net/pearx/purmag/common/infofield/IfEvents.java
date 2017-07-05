package net.pearx.purmag.common.infofield;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import net.pearx.purmag.common.infofield.steps.IIfResearchStep;
import net.pearx.purmag.common.infofield.steps.IRSCollect;
import net.pearx.purmag.common.infofield.steps.IRSKillEntity;
import org.apache.commons.lang3.tuple.Pair;

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
            EntityPlayerMP p = (EntityPlayerMP) e.getEntityPlayer();
            for (Pair<IfEntry, IRSCollect> pair : PurMag.INSTANCE.if_registry.getAllResearchableSteps(IRSCollect.class, p))
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
            for (Pair<IfEntry, IRSKillEntity> pair : PurMag.INSTANCE.if_registry.getAllResearchableSteps(IRSKillEntity.class, p))
            {
                if (pair.getRight().clazz == e.getEntity().getClass())
                {
                    p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).unlockStepAndSync(pair.getLeft().getId(), p);
                }
            }
        }
    }
}
