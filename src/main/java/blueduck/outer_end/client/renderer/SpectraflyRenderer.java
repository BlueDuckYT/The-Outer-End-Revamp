package blueduck.outer_end.client.renderer;

import blueduck.outer_end.client.model.EntombedModel;
import blueduck.outer_end.client.model.SpectraflyModel;
import blueduck.outer_end.entity.Entombed;
import blueduck.outer_end.entity.Spectrafly;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SpectraflyRenderer extends MobRenderer<Spectrafly, SpectraflyModel<Spectrafly>>  {
    public SpectraflyRenderer(EntityRendererProvider.Context context) {
        super(context,  new SpectraflyModel<>(context.bakeLayer(SpectraflyModel.LAYER_LOCATION)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(Spectrafly p_114482_) {
        return new ResourceLocation("outer_end:textures/entity/glowdragon.png");
    }
}
