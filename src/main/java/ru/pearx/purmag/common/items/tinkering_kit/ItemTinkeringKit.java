package ru.pearx.purmag.common.items.tinkering_kit;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.common.structure.multiblock.Multiblock;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.blocks.multiblock.PMMultiblock;
import ru.pearx.purmag.common.items.base.ItemToolBase;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.CPacketSpawnMultiblockParticles;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/*
 * Created by mrAppleXZ on 14.11.17 9:06.
 */
public class ItemTinkeringKit extends ItemToolBase
{
    public static final String CLASS = Utils.gRL(" ").toString();
    private Set<String> toolClasses = ImmutableSet.of(CLASS);
    private int tier;

    public ItemTinkeringKit(ToolMaterial mat, int tier)
    {
        super(0, 0, mat, Collections.emptySet());
        setTier(tier);
        setMaxDamage(toolMaterial.getMaxUses() / 8);
        attackDamage *= 0.25f;
        setFull3D();
    }
    public ItemTinkeringKit(ToolMaterial mat, int tier, String name)
    {
        super(0, 0, mat, Collections.emptySet(), name);
        setTier(tier);
        setMaxDamage(toolMaterial.getMaxUses() / 8);
        attackDamage *= 0.25f;
        setFull3D();
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        for(Multiblock mb : Multiblock.REGISTRY)
        {
            if(mb instanceof PMMultiblock)
            {
                Optional<Rotation> rot = mb.checkMultiblock(worldIn, pos, player, hand);
                if (rot.isPresent())
                {
                    player.getHeldItem(hand).damageItem(1, player);
                    if(!worldIn.isRemote)
                        mb.form(worldIn, pos, rot.get(), player);
                    return EnumActionResult.PASS;
                }
            }
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack)
    {
        return toolClasses;
    }

    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier)
    {
        this.tier = tier;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(I18n.format("item.purmag.tinkering_kit.tooltip", getTierDisplayName(getTier())));
    }

    @SideOnly(Side.CLIENT)
    public static String getTierDisplayName(int tier)
    {
        return I18n.format("item.purmag.tinkering_kit.tier." + tier);
    }
}
