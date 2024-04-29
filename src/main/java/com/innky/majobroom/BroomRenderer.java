package com.innky.majobroom;

import com.innky.majobroom.model.JsonBroomModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BroomRenderer extends EntityRenderer<MajoBroomEntity> {
    private EntityModel<MajoBroomEntity> broomModel;

    public BroomRenderer(EntityRendererProvider.Context context) {
        super(context);
        broomModel = new JsonBroomModel("jsonmodels/brooms/Broom");
    }

    @Override
    public ResourceLocation getTextureLocation(MajoBroomEntity entity) {
        ResourceLocation resourceLocation = new ResourceLocation("majobroom", "textures/entity/broom.png");
        return resourceLocation;
    }


    @Override
    public void render(MajoBroomEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        float yaw = entityIn.getViewYRot(partialTicks);
        float pitch = entityIn.getViewXRot(partialTicks);
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Axis.XP.rotationDegrees(pitch));
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(90 - yaw));
        matrixStackIn.pushPose();
        float fl = entityIn.getRealFl(partialTicks);
        matrixStackIn.translate(0, 0.1 * fl, 0);
        VertexConsumer iVertexBuilder = bufferIn.getBuffer(this.broomModel.renderType(this.getTextureLocation(entityIn)));
        broomModel.renderToBuffer(matrixStackIn, iVertexBuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.popPose();
        matrixStackIn.popPose();
    }


}
