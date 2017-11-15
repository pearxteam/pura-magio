package ru.pearx.purmag.common.items;

import net.minecraft.block.Block;
import ru.pearx.purmag.common.PMCreativeTab;
import ru.pearx.purmag.common.Utils;

import java.util.Set;

/*
 * Created by mrAppleXZ on 14.11.17 9:09.
 */
public class ItemToolBase extends ru.pearx.libmc.common.items.ItemToolBase
{
    protected ItemToolBase(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn)
    {
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
        setCreativeTab(PMCreativeTab.INSTANCE);
    }

    public ItemToolBase(ToolMaterial materialIn, Set<Block> effectiveBlocksIn)
    {
        super(materialIn, effectiveBlocksIn);
        setCreativeTab(PMCreativeTab.INSTANCE);
    }

    protected ItemToolBase(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn, String name)
    {
        this(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
        setRegistryName(name);
        setUnlocalizedName(Utils.getUnlocalizedName(name));
    }

    public ItemToolBase(ToolMaterial materialIn, Set<Block> effectiveBlocksIn, String name)
    {
        this(materialIn, effectiveBlocksIn);
        setRegistryName(name);
        setUnlocalizedName(Utils.getUnlocalizedName(name));
    }
}
