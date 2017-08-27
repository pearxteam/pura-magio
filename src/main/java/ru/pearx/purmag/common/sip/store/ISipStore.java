package ru.pearx.purmag.common.sip.store;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;
import java.util.Map;

/**
 * Created by mrAppleXZ on 23.05.17 9:23.
 */
public interface ISipStore extends INBTSerializable<NBTTagCompound>
{
    int get(String type);

    int getMax(String type);

    boolean canAdd(String type, int count);

    int add(String type, int count);

    boolean canRemove(String type, int count);

    void remove(String type, int count);

    Map<String, Integer> getStored();

    void clear();

    List<String> getAllowedTypes();
}
