package ru.pearx.purmag.common.sif;

import net.minecraft.nbt.NBTTagCompound;

/*
 * Created by mrAppleXZ on 27.05.18 12:21.
 */
public abstract class SifStorage implements ISifStorage
{
    private float power;

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setFloat("power", getPower());
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        setPower(nbt.getFloat("power"));
    }

    @Override
    public float getPower()
    {
        return power;
    }

    @Override
    public void setPower(float value)
    {
        power = value;
        markDirty();
    }
}
