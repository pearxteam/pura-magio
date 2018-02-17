package ru.pearx.purmag.common.blocks.multiblock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.pearx.libmc.common.structure.blockarray.BlockArray;
import ru.pearx.libmc.common.structure.multiblock.Multiblock;
import ru.pearx.purmag.common.PMCreativeTab;
import ru.pearx.purmag.common.items.tinkering_kit.ItemTinkeringKit;

import javax.annotation.Nullable;
import java.util.Optional;

/*
 * Created by mrAppleXZ on 01.01.18 20:26.
 */
public class PMMultiblock extends Multiblock
{
    private int tier;

    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier)
    {
        this.tier = tier;
    }

    @Override
    public Optional<Rotation> checkMultiblock(World w, BlockPos pos, @Nullable EntityPlayer p, @Nullable EnumHand hand)
    {
        if(p != null && hand != null)
        {
            ItemStack st = p.getHeldItem(hand);
            if(!(st.getItem() instanceof ItemTinkeringKit && ((ItemTinkeringKit) st.getItem()).getTier() >= getTier()))
            {
                return Optional.empty();
            }
        }
        return super.checkMultiblock(w, pos, p, hand);
    }
}
