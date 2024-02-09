package blueduck.outer_end.client.renderer;

import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.entity.CrystalArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CobaltArrowRenderer extends ArrowRenderer<CrystalArrow> {
    public static final ResourceLocation SPECTRAL_ARROW_LOCATION = new ResourceLocation(TheOuterEnd.MODID, "textures/entity/cobalt_crystal_arrow.png");

    public CobaltArrowRenderer(EntityRendererProvider.Context p_174399_) {
        super(p_174399_);
    }

    public ResourceLocation getTextureLocation(CrystalArrow p_116001_) {
        return SPECTRAL_ARROW_LOCATION;
    }
}