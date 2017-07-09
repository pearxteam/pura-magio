package net.pearx.purmag.client.models.processors;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/*
 * Created by mrAppleXZ on 07.07.17 9:00.
 */
@SideOnly(Side.CLIENT)
public interface IQuadProcessor
{
    void process(List<BakedQuad> quads, @Nullable IBlockState state, @Nullable EnumFacing side, long rand);
}
