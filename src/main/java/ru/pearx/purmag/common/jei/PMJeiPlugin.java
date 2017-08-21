package ru.pearx.purmag.common.jei;

import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;

/*
 * Created by mrAppleXZ on 14.08.17 11:42.
 */
public class PMJeiPlugin implements IModPlugin
{
    public static IJeiRuntime runtime;

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
    {
        runtime = jeiRuntime;
    }
}
