package ru.pearx.purmag.common.structure;

import ru.pearx.carbide.mc.common.structure.processors.StructureProcessor;

/*
 * Created by mrAppleXZ on 05.10.17 21:17.
 */
public class PMStructureProcessorRegistry
{
    public static void setup()
    {
        StructureProcessor.REGISTRY.register(new CodeStorageProcessor());
    }
}
