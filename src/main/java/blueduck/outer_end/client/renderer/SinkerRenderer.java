package blueduck.outer_end.client.renderer;

import blueduck.outer_end.client.model.SinkerModel;
import blueduck.outer_end.entity.Sinker;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SinkerRenderer extends MobRenderer<Sinker, SinkerModel<Sinker>>  {
    public SinkerRenderer(EntityRendererProvider.Context context) {
        super(context,  new SinkerModel<>(context.bakeLayer(SinkerModel.LAYER_LOCATION)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(Sinker p_114482_) {
        return new ResourceLocation("outer_end:textures/entity/sinker.png");
    }

    protected void setupRotations(Sinker pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        pPoseStack.translate(0.0F, 0.4F, 0.0F);
    }
}
