package net.pearx.purmag.common.blocks;

import net.minecraft.util.IStringSerializable;

/**
 * Created by mrAppleXZ on 21.04.17 21:32.
 */
public enum EnumHalf implements IStringSerializable
{
    UP,
    DOWN;

    @Override
    public String getName()
    {
        return toString();
    }
}