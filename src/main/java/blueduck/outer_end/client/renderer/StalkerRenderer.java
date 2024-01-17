package blueduck.outer_end.client.renderer;

import blueduck.outer_end.client.model.EntombedModel;
import blueduck.outer_end.client.model.StalkerModel;
import blueduck.outer_end.entity.Entombed;
import blueduck.outer_end.entity.Stalker;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class StalkerRenderer extends MobRenderer<Stalker, StalkerModel<Stalker>>  {
    public StalkerRenderer(EntityRendererProvider.Context context) {
        super(context,  new StalkerModel<>(context.bakeLayer(StalkerModel.LAYER_LOCATION)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(Stalker entity) {
        ResourceLocation id = new ResourceLocation("outer_end:textures/entity/stalker_" + entity.getColor() + ".png");
        return id.getPath().equals("textures/entity/stalker_.png") ? new ResourceLocation("outer_end:textures/entity/stalker.png") : id;
    }
    @Override
    public void render(Stalker entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        if (entityIn.getAge() <= 0) {
            matrixStackIn.scale(0.5f, 0.5f, 0.5f);
        }
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.popPose();
    }

}
