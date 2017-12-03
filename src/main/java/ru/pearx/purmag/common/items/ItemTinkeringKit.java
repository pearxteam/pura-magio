package ru.pearx.purmag.common.items;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.pearx.libmc.PXLMC;
import ru.pearx.libmc.client.particle.PXParticle;
import ru.pearx.libmc.client.particle.ParticleEngine;
import ru.pearx.libmc.common.structure.multiblock.Multiblock;
import ru.pearx.purmag.client.particle.ParticleMultiblock;
import ru.pearx.purmag.common.SoundRegistry;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.CPacketSpawnMultiblockParticles;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/*
 * Created by mrAppleXZ on 14.11.17 9:06.
 */
public class ItemTinkeringKit extends ItemToolBase
{
    public static final String CLASS = Utils.gRL("tinkering_kit").toString();
    private Set<String> toolClasses = ImmutableSet.of(CLASS);

    public ItemTinkeringKit(ToolMaterial mat)
    {
        super(0, 0, mat, Collections.emptySet());
        setMaxDamage(toolMaterial.getMaxUses() / 8);
        attackDamage *= 0.25f;
    }
    public ItemTinkeringKit(ToolMaterial mat, String name)
    {
        super(0, 0, mat, Collections.emptySet(), name);
        setMaxDamage(toolMaterial.getMaxUses() / 8);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        for(Multiblock mb : Multiblock.REGISTRY)
        {
            Optional<Rotation> rot = mb.tryForm(worldIn, pos, player);
            if(rot.isPresent())
            {
                player.getHeldItem(hand).damageItem(1, player);
                if(!worldIn.isRemote)
                {
                    NetworkManager.sendToAllAround(new CPacketSpawnMultiblockParticles(pos, mb.getRegistryName(), rot.get()), pos.getX(), pos.getY(), pos.getZ(), worldIn.provider.getDimension(), 256);
                }
                return EnumActionResult.PASS;
            }
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack)
    {
        return toolClasses;
    }
}
