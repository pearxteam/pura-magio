package net.pearx.purmag.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.ClientUtils;
import net.pearx.purmag.infofield.IFTier;
import net.pearx.purmag.sip.SipTypeRegistry;

/**
 * Created by mrAppleXZ on 08.04.17 18:46.
 */
public class ItemRegistry
{
    public static Item crystal;
    public static Item crystal_shard;
    public static Item crystal_cutter;
    public static Item if_tablet;

    public static void setup()
    {
        crystal = new ItemBlockCrystal();
        GameRegistry.register(crystal);

        crystal_shard = new ItemCrystalShard();
        GameRegistry.register(crystal_shard);

        crystal_cutter = new ItemCrystalCutter();
        GameRegistry.register(crystal_cutter);

        if_tablet = new ItemIfTablet();
        GameRegistry.register(if_tablet);
    }

    @SideOnly(Side.CLIENT)
    public static void setupModels()
    {
        ClientUtils.setModelLocation(crystal);
        ClientUtils.setModelLocation(crystal_shard);
        ClientUtils.setModelLocation(crystal_cutter);
        for(IFTier t : PurMag.instance.if_registry.tiers)
        {
            ClientUtils.setModelLocation(if_tablet, t.getTier());
        }
    }

    public static ItemStack getDefaultCrystal()
    {
        ItemStack stack = new ItemStack(crystal);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("type", SipTypeRegistry.def);
        stack.setTagCompound(tag);
        return stack;
    }

}
