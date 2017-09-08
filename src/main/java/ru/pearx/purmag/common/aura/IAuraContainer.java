package ru.pearx.purmag.common.aura;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.UUID;

/*
 * Created by mrAppleXZ on 30.08.17 18:29.
 */
public interface IAuraContainer extends INBTSerializable<NBTTagCompound>
{
    UUID getKey();

    void sync(EntityPlayerMP p);
}
