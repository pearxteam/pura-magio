package ru.pearx.purmag.common.infofield.steps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import ru.pearx.purmag.client.gui.if_tablet.steps.IRSInteractRenderer;
import ru.pearx.purmag.client.gui.if_tablet.steps.IRSRenderer;

import java.util.List;

/*
 * Created by mrAppleXZ on 13.10.17 20:24.
 */
public class IRSBlockInteract extends IRSBase
{
    public interface Predicate
    {
        boolean apply(EntityPlayer player, EnumHand hand, BlockPos pos, EnumFacing face, Vec3d hitVec, World world);
    }

    private Predicate predicate;
    private List<Pair<IBlockState, ItemStack>> renderStates;

    public IRSBlockInteract(Predicate predicate, List<Pair<IBlockState, ItemStack>> renderStates)
    {
        setPredicate(predicate);
        setRenderStates(renderStates);
    }

    public IRSBlockInteract(List<Pair<IBlockState, ItemStack>> renderStates)
    {
        setPredicate((player, hand, pos, face, hit, world) ->
        {
            IBlockState state = world.getBlockState(pos);
            for (Pair<IBlockState, ItemStack> rend : renderStates)
            {
                if(state.getBlock() == rend.getLeft().getBlock())
                    return true;
            }
            return false;
        });
        setRenderStates(renderStates);
    }

    public Predicate getPredicate()
    {
        return predicate;
    }

    public void setPredicate(Predicate predicate)
    {
        this.predicate = predicate;
    }

    public List<Pair<IBlockState, ItemStack>> getRenderStates()
    {
        return renderStates;
    }

    public void setRenderStates(List<Pair<IBlockState, ItemStack>> renderStates)
    {
        this.renderStates = renderStates;
    }

    public boolean apply(EntityPlayer player, EnumHand hand, BlockPos pos, EnumFacing face, Vec3d hitVec, World world)
    {
        return getPredicate().apply(player, hand, pos, face, hitVec, world);
    }

    @Override
    public String getUnlocalizedName()
    {
        return "block_interact";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRSRenderer getRenderer()
    {
        return new IRSInteractRenderer(this);
    }
}
