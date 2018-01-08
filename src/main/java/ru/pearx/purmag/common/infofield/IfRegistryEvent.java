package ru.pearx.purmag.common.infofield;

import net.minecraftforge.fml.common.eventhandler.Event;

/*
 * Created by mrAppleXZ on 07.01.18 15:30.
 */
public abstract class IfRegistryEvent extends Event
{
    private IfRegistry registry;

    public IfRegistryEvent(IfRegistry registry)
    {
        this.registry = registry;
    }

    public IfRegistry getRegistry()
    {
        return registry;
    }

    public static class Setup extends IfRegistryEvent
    {
        public Setup(IfRegistry registry)
        {
            super(registry);
        }
    }

    public static class SetupClient extends IfRegistryEvent
    {
        public SetupClient(IfRegistry registry)
        {
            super(registry);
        }
    }
}
