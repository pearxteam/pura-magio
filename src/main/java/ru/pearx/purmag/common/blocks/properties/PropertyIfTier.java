package ru.pearx.purmag.common.blocks.properties;

import net.minecraftforge.common.property.IUnlistedProperty;
import ru.pearx.purmag.PurMag;

/*
 * Created by mrAppleXZ on 09.07.17 13:36.
 */
public class PropertyIfTier implements IUnlistedProperty<Integer>
{
    private String name;

    public PropertyIfTier(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public boolean isValid(Integer value)
    {
        return PurMag.INSTANCE.getIfRegistry().getTier(value) != null;
    }

    @Override
    public Class<Integer> getType()
    {
        return Integer.class;
    }

    @Override
    public String valueToString(Integer value)
    {
        return value.toString();
    }
}
