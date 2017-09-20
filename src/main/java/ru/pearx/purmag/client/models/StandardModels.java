package ru.pearx.purmag.client.models;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.*;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import ru.pearx.libmc.client.models.*;
import ru.pearx.libmc.client.models.processors.FacingProcessor;
import ru.pearx.libmc.client.models.processors.IVertexProcessor;
import ru.pearx.libmc.client.models.processors.TintProcessor;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.blocks.AbstractWallIfTablet;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.List;

/**
 * Created by mrAppleXZ on 17.05.17 8:04.
 */
@SideOnly(Side.CLIENT)
public class StandardModels
{
    public static class Crystal extends OvModel
    {
        private Matrix4f mat = new TRSRTransformation(new Vector3f(0, -0.25f, 0), null, new Vector3f(0.45f, 0.45f, 0.45f), new Quat4f(0, -0.20944f, 0, 1)).getMatrix();

        public Crystal()
        {
            setBaseModel(new ResourceLocation(PurMag.MODID, "obj/crystal.obj"));
            quadProcessors.add(new TintProcessor(0));
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            return Pair.of(this, mat);
        }
    }

    public static class CrystalGlass extends ConnectedModel
    {
        public CrystalGlass()
        {
            setBaseTexture(new ResourceLocation(PurMag.MODID, "blocks/crystal_glass"));
            quadProcessors.add(new TintProcessor(0));
        }
    }

    public static class LuminousCrystalGlass extends ConnectedModel
    {
        public LuminousCrystalGlass()
        {
            setBaseTexture(new ResourceLocation(PurMag.MODID, "blocks/luminous_crystal_glass"));
            quadProcessors.add(new TintProcessor(0));
        }
    }

    public static class Glove extends OvModel
    {
        private Matrix4f mat_fp = new TRSRTransformation(new Vector3f(0f, 0.2f, -0.1f), new Quat4f(0, 0, 45, 1), null, new Quat4f(0, 0, 180, 1)).getMatrix();
        private Matrix4f mat_gui = new TRSRTransformation(new Vector3f(0.15f, 0.15f, 0), null, new Vector3f(1.2f, 1.2f, 1.2f), new Quat4f(-1.64061f, -4.50295f, 0, 1)).getMatrix();

        public Glove()
        {
            setBaseModel(Utils.getResourceLocation("obj/glove.obj"));
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            if (cameraTransformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND || cameraTransformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
            {
                return Pair.of(this, mat_fp);
            }
            if (cameraTransformType == ItemCameraTransforms.TransformType.GUI)
            {
                return Pair.of(this, mat_gui);
            }
            return Pair.of(this, null);
        }
    }

    public static class TranslationDesk extends OvModel
    {
        private Matrix4f mat_gui = new TRSRTransformation(null, null, new Vector3f(0.6f, 0.6f, 0.6f), new Quat4f(0.523599f, 3.92699f, 1.5708f, 1)).getMatrix();
        private Matrix4f mat = new TRSRTransformation(null, null, new Vector3f(0.375f, 0.375f, 0.375f), null).getMatrix();

        public TranslationDesk()
        {
            vertexProcessors.add(new FacingProcessor());
            setBaseModel(Utils.getResourceLocation("obj/translation_desk.obj"));
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            if (cameraTransformType == ItemCameraTransforms.TransformType.GUI)
                return Pair.of(this, mat_gui);
            return Pair.of(this, mat);
        }
    }

    public static class CrystalSmall extends OvModel
    {
        private Matrix4f mat_gui = new TRSRTransformation(new Vector3f(0, 0.35f, 0), null, new Vector3f(1.5f, 1.5f, 1.5f), new Quat4f(0.349066f, 0.20944f, 0, 1)).getMatrix();
        private Matrix4f mat = new TRSRTransformation(new Vector3f(0, 0.35f, 0), null, null, null).getMatrix();

        public CrystalSmall()
        {
            setBaseModel(Utils.getResourceLocation("obj/crystal_small.obj"));
            quadProcessors.add(new TintProcessor(0));
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            if (cameraTransformType == ItemCameraTransforms.TransformType.GUI)
                return Pair.of(this, mat_gui);
            return Pair.of(this, mat);
        }
    }

    public static class WallIfTablet extends OvModel
    {
        public Matrix4f mat = new TRSRTransformation(new Vector3f(0, 0, -0.3f), null, new Vector3f(0.625f, 0.625f, 0.625f), null).getMatrix();
        public Matrix4f mat_gui = new TRSRTransformation(new Vector3f(0.3f, -0.2f, 0), TRSRTransformation.quatFromXYZDegrees(new Vector3f(30, 225, 0)), null, null).getMatrix();

        public WallIfTablet()
        {
            setBaseModel(Utils.getResourceLocation("obj/wall_if_tablet.obj"));
            vertexProcessors.add(new FacingProcessor());
            vertexProcessors.add(new IVertexProcessor()
            {
                TextureAtlasSprite sprite;

                @Override
                public void preProcess(List<BakedQuad> quads, @Nullable IBlockState state, @Nullable EnumFacing side, long rand, IPXModel model)
                {
                    int tier;
                    if (state != null && state instanceof IExtendedBlockState)
                        tier = ((IExtendedBlockState) state).getValue(AbstractWallIfTablet.IF_TIER);
                    else
                        tier = getStack().getMetadata();
                    String s = PurMag.INSTANCE.getIfRegistry().getTier(tier).getWallTabletTexture().toString();
                    sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(s);
                }

                @Override
                public void processQuad(UnpackedBakedQuad.Builder bld, BakedQuad quad, @Nullable IBlockState state, @Nullable EnumFacing side, long rand, IPXModel model)
                {
                    bld.setTexture(sprite);
                }

                @Override
                public float[] processVertex(UnpackedBakedQuad.Builder bld, BakedQuad quad, float[] data, int vert, int element, @Nullable IBlockState state, @Nullable EnumFacing side, long rand, IPXModel model)
                {
                    if (bld.getVertexFormat().getElement(element).getUsage() == VertexFormatElement.EnumUsage.UV)
                    {
                        data[0] = sprite.getInterpolatedU(quad.getSprite().getUnInterpolatedU(data[0]));
                        data[1] = sprite.getInterpolatedV(quad.getSprite().getUnInterpolatedV(data[1]));
                    }
                    return data;
                }

                @Override
                public boolean processState()
                {
                    return true;
                }

                @Override
                public boolean processStack()
                {
                    return true;
                }
            });
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            switch (cameraTransformType)
            {
                case GUI:
                    return Pair.of(this, mat_gui);
            }
            return Pair.of(this, mat);
        }
    }

    public static class Microscope extends OvModel
    {
        public Microscope()
        {
            setBaseModel(Utils.getResourceLocation("obj/microscope.obj"));
            vertexProcessors.add(new FacingProcessor());
        }
    }

    public static class Test extends OvModel
    {
        public Test()
        {
            setBaseModel(Utils.getResourceLocation("obj/test.obj"));
        }
    }

    public static class CodeStorage extends OvModel
    {
        public CodeStorage()
        {
            setBaseModel(Utils.getResourceLocation("obj/code_storage.obj"));
        }

        public static class Top extends CodeStorage
        {
            private ModelStateHide hide = new ModelStateHide("top", "gauge");
            @Override
            public IModelState getModelState(IPXModel th, IModel model)
            {
                return hide;
            }
        }

        public static class Body extends CodeStorage
        {
            private ModelStateHide hide = new ModelStateHide("body", "screenandkeyboard");
            @Override
            public IModelState getModelState(IPXModel th, IModel model)
            {
                return hide;
            }
        }
    }
}
