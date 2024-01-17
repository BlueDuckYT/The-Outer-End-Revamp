package blueduck.outer_end.client.renderer;

import blueduck.outer_end.client.model.HimmeliteModel;
import blueduck.outer_end.client.model.PurpurGolemModel;
import blueduck.outer_end.entity.Himmelite;
import blueduck.outer_end.entity.PurpurGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HimmeliteRenderer extends MobRenderer<Himmelite, HimmeliteModel<Himmelite>>  {
    public HimmeliteRenderer(EntityRendererProvider.Context context) {
        super(context,  new HimmeliteModel<>(context.bakeLayer(HimmeliteModel.LAYER_LOCATION)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(Himmelite p_114482_) {
        return new ResourceLocation("outer_end:textures/entity/himmelite.png");
    }
}
