package net.pearx.purmag.client.models;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ISmartVariant;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.blocks.BlockCrystal;
import net.pearx.purmag.common.blocks.BlockCrystalGlass;
import net.pearx.purmag.common.blocks.controllers.CTMController;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mrAppleXZ on 14.05.17 22:10.
 */
@SideOnly(Side.CLIENT)
public class CTMModelBase extends OvModelBase
{
    private ResourceLocation baseTexture;

    public CTMModelBase()
    {
        setBaseModel(new ResourceLocation(PurMag.ModId, "block/cube_all_colored"));
    }

    public ResourceLocation getBaseTexture()
    {
        return baseTexture;
    }

    public void setBaseTexture(ResourceLocation baseTexture)
    {
        this.baseTexture = baseTexture;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand)
    {

        List<BakedQuad> quads = new ArrayList<>();
        for(BakedQuad baseQ : getBaked().getQuads(state, side, rand))
        {
            String digits = "0000";
            if(state instanceof IExtendedBlockState)
            {
                IExtendedBlockState ext = (IExtendedBlockState) state;
                switch (baseQ.getFace())
                {
                    case SOUTH:
                        digits = getConnectionsString(EnumFacing.UP, EnumFacing.EAST, EnumFacing.DOWN, EnumFacing.WEST, ext);
                        break;
                    case NORTH:
                        digits = getConnectionsString(EnumFacing.UP, EnumFacing.WEST, EnumFacing.DOWN, EnumFacing.EAST, ext);
                        break;
                    case UP:
                        digits = getConnectionsString(EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST, ext);
                        break;
                    case DOWN:
                        digits = getConnectionsString(EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.NORTH, EnumFacing.WEST, ext);
                        break;
                    case EAST:
                        digits = getConnectionsString(EnumFacing.UP, EnumFacing.NORTH, EnumFacing.DOWN, EnumFacing.SOUTH, ext);
                        break;
                    case WEST:
                        digits = getConnectionsString(EnumFacing.UP, EnumFacing.SOUTH, EnumFacing.DOWN, EnumFacing.NORTH, ext);
                        break;
                }
            }
            quads.add(new BakedQuadWNT(baseQ, getSprite(digits)));
        }
        return quads;
    }

    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return getSprite("0000");
    }

    public String getConnectionsString(EnumFacing north, EnumFacing east, EnumFacing south, EnumFacing west, IExtendedBlockState state)
    {
        try
        {
            return boolToShortString(state.getValue(CTMController.PROPS.get(north))) + boolToShortString(state.getValue(CTMController.PROPS.get(east))) + boolToShortString(state.getValue(CTMController.PROPS.get(south))) + boolToShortString(state.getValue(CTMController.PROPS.get(west)));
        }
        catch(Exception e)
        {
            return "0000";
        }
    }

    public String boolToShortString(Boolean bool)
    {
        return bool ? "1" : "0";
    }

    public TextureAtlasSprite getSprite(String digits)
    {
        return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(getBaseTexture().toString() + "/" + digits);
    }
}
