package net.pearx.purmag.client.models;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

/**
 * Created by mrAppleXZ on 17.05.17 8:04.
 */
@SideOnly(Side.CLIENT)
public class StandardModels
{
    public static class Crystal extends OvModelBase implements IPerspectiveAwareModel
    {
        public Crystal()
        {
            setBaseModel(new ResourceLocation(PurMag.ModId, "block/crystal.obj"));
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            return Pair.of(this, new TRSRTransformation(new Vector3f(0, -0.25f, 0), null, new Vector3f(0.45f, 0.45f, 0.45f), null).getMatrix());
        }
    }

    public static class CrystalGlass extends CTMModelBase
    {
        public CrystalGlass()
        {
            setBaseTexture(new ResourceLocation(PurMag.ModId, "blocks/crystal_glass"));
        }
    }
}
