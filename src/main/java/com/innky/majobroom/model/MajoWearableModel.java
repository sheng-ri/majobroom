package com.innky.majobroom.model;

import com.innky.majobroom.jsonbean.GeomtryBean;
import com.innky.majobroom.utills.ModelJsonReader;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MajoWearableModel extends HumanoidModel<LivingEntity> {
    /*
    public final Map<String, ModelPart> bones;
    private final HashMap<String, GeomtryBean.BonesBean> bonesBean;*/
    public final ModelPart base;
    private final String model_name;
    private final Map<String, PartDefinition> bones = new HashMap<>();
    private final Map<String, GeomtryBean.BonesBean> bonesBean = new HashMap<>();


    public MajoWearableModel(String name) {
        super(HumanoidModel.createMesh(CubeDeformation.NONE, 0f).getRoot().bake(256, 256));
        this.base = getTexturedModelData(name).bakeRoot();
        this.model_name = name;
    }

    private float convertOrigin(GeomtryBean.BonesBean bones, GeomtryBean.BonesBean.CubesBean cubes, int index) {
        if (index == 1) {
            return bones.getPivot().get(index) - cubes.getOrigin().get(index) - cubes.getSize().get(index);
        } else {
            return cubes.getOrigin().get(index) - bones.getPivot().get(index);
        }
    }


    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        switch (model_name) {
            case "majo_hat" -> this.base.copyFrom(this.head);
            case "majo_cloth" -> {
                this.base.getChild("bigBody").copyFrom(this.body);
                this.base.getChild("epic").copyFrom(this.body);
                this.base.getChild("bone81").getChild("left").copyFrom(this.rightArm);
                this.base.getChild("bone81").getChild("right").copyFrom(this.leftArm);
                ModelPart dress = this.base.getChild("dress");
                dress.xRot = (this.leftLeg.xRot + this.rightLeg.xRot) / 2;
                dress.z = this.leftLeg.z;
                if (this.riding) {
                    dress.y = 10.0F;
                    dress.xRot = -1.04F;
                    this.base.getChild("bigBody").getChild("bone3").getChild("bone5").getChild("sithide1").visible = false;
                    this.base.getChild("bigBody").getChild("bone3").getChild("bone5").getChild("sithide2").visible = false;

                } else {
                    dress.y = 10.0F;
                    this.base.getChild("bigBody").getChild("bone3").getChild("bone5").getChild("sithide1").visible = true;
                    this.base.getChild("bigBody").getChild("bone3").getChild("bone5").getChild("sithide2").visible = true;
                }
            }
            case "majo_boots" -> {
                this.base.getChild("LeftLeg").copyFrom(this.leftLeg);
                this.base.getChild("RightLeg").copyFrom(this.rightLeg);
            }
        }

        this.base.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);

    }

    //            dress.rotationPointZ =this.getEntityModel().bipedLeftLeg.rotationPointZ;
    /*public void setRotationAngle(ModelPart modelPart, float x, float y, float z) {
        modelPart.rotateAngleX = x;
        modelPart.rotateAngleY = y;
        modelPart.rotateAngleZ = z;
    }*/


    public LayerDefinition getTexturedModelData(String path) {
        GeomtryBean model = ModelJsonReader.readJson("jsonmodels/" + path);


        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
//        modelPartData.addChild("sdasdsads", ModelPartBuilder.create().uv(0, 0).cuboid(-6F, 12F, -6F, 24F, 24F, 24F), ModelTransform.pivot(0F, 100F, 5F));
//
//        var another_cube = ModelPartBuilder.create().uv(0, 0).cuboid(-6F, 12F, -6F, 12F, 12F, 12F);
//        modelPartData.getChild("sdasdsads").addChild("aaa",another_cube,ModelTransform.pivot(0F, 1F, 1F));


        if (model != null) {

            for (GeomtryBean.BonesBean bone : model.getBones()) {
                CubeListBuilder newBone = CubeListBuilder.create();
                float x = 0, y = 0, z = 0;
                if (bone.getRotation() != null) {
                    x = 0.017453F * bone.getRotation().get(0);
                    y = 0.017453F * bone.getRotation().get(1);
                    z = 0.017453F * bone.getRotation().get(2);
                }

                if (bone.getCubes() != null) {
                    for (GeomtryBean.BonesBean.CubesBean cube : bone.getCubes()) {
                        newBone.texOffs(cube.getUv().get(0), cube.getUv().get(1)).addBox(this.convertOrigin(bone, cube, 0), this.convertOrigin(bone, cube, 1), this.convertOrigin(bone, cube, 2), cube.getSize().get(0), cube.getSize().get(1), cube.getSize().get(2), new CubeDeformation(cube.getInflate()));
                    }
                }

                PartDefinition nnnn;

                if (bone.getParent() != null) {
                    GeomtryBean.BonesBean parent = this.bonesBean.get(bone.getParent());
                    this.bones.get(bone.getParent()).addOrReplaceChild(bone.getName(), newBone, PartPose.offsetAndRotation(bone.getPivot().get(0) - parent.getPivot().get(0), parent.getPivot().get(1) - bone.getPivot().get(1), bone.getPivot().get(2) - parent.getPivot().get(2), x, y, z));
                    nnnn = this.bones.get(bone.getParent()).getChild(bone.getName());
                } else {
                    modelPartData.addOrReplaceChild(bone.getName(), newBone, PartPose.offsetAndRotation(bone.getPivot().get(0), 24.0F - bone.getPivot().get(1), bone.getPivot().get(2), x, y, z));
                    nnnn = modelPartData.getChild(bone.getName());
                }

                this.bones.put(bone.getName(), nnnn);
                this.bonesBean.put(bone.getName(), bone);
            }
        }

        return LayerDefinition.create(modelData, model.getTexturewidth(), model.getTextureheight());
    }


}
