package blueduck.outer_end.client.renderer;

import blueduck.outer_end.client.model.EntombedModel;
import blueduck.outer_end.client.model.PurpurGolemModel;
import blueduck.outer_end.entity.Entombed;
import blueduck.outer_end.entity.PurpurGolem;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EntombedRenderer extends MobRenderer<Entombed, EntombedModel<Entombed>>  {
    public EntombedRenderer(EntityRendererProvider.Context context) {
        super(context,  new EntombedModel<>(context.bakeLayer(EntombedModel.LAYER_LOCATION)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(Entombed p_114482_) {
        return new ResourceLocation("outer_end:textures/entity/entombed.png");
    }
}
