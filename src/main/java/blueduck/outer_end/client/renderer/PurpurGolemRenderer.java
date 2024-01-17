package blueduck.outer_end.client.renderer;

import blueduck.outer_end.client.model.PurpurGolemModel;
import blueduck.outer_end.entity.PurpurGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PurpurGolemRenderer extends MobRenderer<PurpurGolem, PurpurGolemModel<PurpurGolem>>  {
    public PurpurGolemRenderer(EntityRendererProvider.Context context) {
        super(context,  new PurpurGolemModel<>(context.bakeLayer(PurpurGolemModel.LAYER_LOCATION)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(PurpurGolem p_114482_) {
        return new ResourceLocation("outer_end:textures/entity/purpur_golem.png");
    }
}
