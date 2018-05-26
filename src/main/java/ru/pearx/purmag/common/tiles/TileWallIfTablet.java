package ru.pearx.purmag.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import ru.pearx.carbide.mc.common.nbt.serialization.NBTSerializer;
import ru.pearx.carbide.mc.common.tiles.syncable.TileSyncableComposite;

/*
 * Created by mrAppleXZ on 09.07.17 10:23.
 */
public class TileWallIfTablet extends TileSyncableComposite
{
    public static final String NBT_TIER = "tier";

    private int tier;

    public TileWallIfTablet()
    {
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_TIER, int.class, this::setTier, this::getTier));
    }

    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier)
    {
        this.tier = tier;
        markDirty();
    }

    public void setTierAndSync(EntityPlayer p, int tier)
    {
        setTier(tier);
        sendUpdates(p, NBT_TIER);
    }
}
