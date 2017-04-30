package net.pearx.purmag.client.models;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

/**
 * Created by mrAppleXZ on 09.04.17 15:25.
 */
@SideOnly(Side.CLIENT)
public class CrystalModel extends ObjModelBase implements IPerspectiveAwareModel
{
    public CrystalModel()
    {
        setObj("block/crystal.obj");
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
    {
        return Pair.of(this, new TRSRTransformation(new Vector3f(0, -0.25f, 0), null, new Vector3f(0.45f, 0.45f, 0.45f), null).getMatrix());
    }
}
