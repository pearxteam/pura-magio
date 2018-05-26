package ru.pearx.purmag.common.infofield.steps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.carbide.mc.common.structure.blockarray.BlockArrayEntry;
import ru.pearx.purmag.client.gui.if_tablet.steps.IRSBlockInteractRenderer;
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
    private List<BlockArrayEntry> renderStates;

    public IRSBlockInteract(Predicate predicate, List<BlockArrayEntry> renderStates)
    {
        setPredicate(predicate);
        setRenderStates(renderStates);
    }

    public IRSBlockInteract(List<BlockArrayEntry> renderStates)
    {
        this((player, hand, pos, face, hit, world) ->
        {
            IBlockState state = world.getBlockState(pos);
            for (BlockArrayEntry entr : renderStates)
            {
                if(state.getBlock() == entr.getState().getBlock())
                    return true;
            }
            return false;
        }, renderStates);
    }

    public Predicate getPredicate()
    {
        return predicate;
    }

    public void setPredicate(Predicate predicate)
    {
        this.predicate = predicate;
    }

    public List<BlockArrayEntry> getRenderStates()
    {
        return renderStates;
    }

    public void setRenderStates(List<BlockArrayEntry> renderStates)
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
    public String getUnlocalizedDescription()
    {
        return super.getUnlocalizedDescription() != null ? super.getUnlocalizedDescription() : getRenderStates().size() > 1 ? "block_interact.mult" : "block_interact.single";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRSRenderer getRenderer()
    {
        return new IRSBlockInteractRenderer(this);
    }
}
