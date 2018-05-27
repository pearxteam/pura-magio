package ru.pearx.purmag.common.sif;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/*
 * Created by mrAppleXZ on 27.05.18 11:51.
 */
public interface ISifStorage extends INBTSerializable<NBTTagCompound>
{
    float getPower();

    void setPower(float value);

    void markDirty();
}
