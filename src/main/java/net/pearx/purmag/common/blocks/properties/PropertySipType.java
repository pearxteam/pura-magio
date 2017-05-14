package net.pearx.purmag.common.blocks.properties;

import com.google.common.base.Optional;
import net.minecraft.block.properties.PropertyHelper;
import net.pearx.purmag.PurMag;

import java.util.Collection;

/**
 * Created by mrAppleXZ on 13.05.17 19:38.
 */
public class PropertySipType extends PropertyHelper<String>
{
    protected PropertySipType(String name, Class<String> valueClass)
    {
        super(name, valueClass);
    }

    public static PropertySipType create()
    {
        return new PropertySipType("sip_type", String.class);
    }

    @Override
    public Collection<String> getAllowedValues()
    {
        return PurMag.instance.sip.allowedValues;
    }

    @Override
    public Optional<String> parseValue(String value)
    {
        return Optional.of(value);
    }

    @Override
    public String getName(String value)
    {
        return value;
    }
}
