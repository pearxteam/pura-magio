package net.pearx.purmag.common.infofield.playerdata;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;

/**
 * Created by mrAppleXZ on 22.04.17 16:51.
 */
public interface IIfEntryStore extends INBTSerializable<NBTTagCompound>
{
    HashMap<String, Integer> getMap();
    int getSteps(String id);
    void setSteps(String id, int st);
    boolean isFullyUnlocked(String id);
    void sync(EntityPlayer p);
}
