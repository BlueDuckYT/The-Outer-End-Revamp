package blueduck.outer_end.events;

import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.client.model.*;
import blueduck.outer_end.client.renderer.*;
import blueduck.outer_end.entity.Sinker;
import blueduck.outer_end.registry.OuterEndBlockEntities;
import blueduck.outer_end.registry.OuterEndBlocks;
import blueduck.outer_end.registry.OuterEndEntities;
import blueduck.outer_end.registry.OuterEndItems;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.SpectralArrowRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TheOuterEnd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    public static void setup() {
        ItemPropertyFunction blockFn = (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
        ItemProperties.register(OuterEndItems.SHULKER_SHIELD.get(), new ResourceLocation("minecraft:blocking"), blockFn);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PurpurGolemModel.LAYER_LOCATION, PurpurGolemModel::createBodyLayer);
        event.registerLayerDefinition(HimmeliteModel.LAYER_LOCATION, HimmeliteModel::createBodyLayer);
        event.registerLayerDefinition(EntombedModel.LAYER_LOCATION, EntombedModel::createBodyLayer);
        event.registerLayerDefinition(StalkerModel.LAYER_LOCATION, StalkerModel::createBodyLayer);
        event.registerLayerDefinition(SpectraflyModel.LAYER_LOCATION, SpectraflyModel::createBodyLayer);
        event.registerLayerDefinition(SinkerModel.LAYER_LOCATION, SinkerModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OuterEndEntities.PURPUR_GOLEM.get(), PurpurGolemRenderer::new);
        event.registerEntityRenderer(OuterEndEntities.HIMMELITE.get(), HimmeliteRenderer::new);
        event.registerEntityRenderer(OuterEndEntities.ENTOMBED.get(), EntombedRenderer::new);
        event.registerEntityRenderer(OuterEndEntities.STALKER.get(), StalkerRenderer::new);
        event.registerEntityRenderer(OuterEndEntities.SPECTRAFLY.get(), SpectraflyRenderer::new);
        event.registerEntityRenderer(OuterEndEntities.SINKER.get(), SinkerRenderer::new);

        event.registerEntityRenderer(OuterEndEntities.SINKER_SHURIKEN.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(OuterEndEntities.SINKER_LASER.get(), SinkerLaserRenderer::new);
        event.registerEntityRenderer(OuterEndEntities.ROSE_CRYSTAL_ARROW.get(), RoseArrowRenderer::new);
        event.registerEntityRenderer(OuterEndEntities.MINT_CRYSTAL_ARROW.get(), MintArrowRenderer::new);
        event.registerEntityRenderer(OuterEndEntities.COBALT_CRYSTAL_ARROW.get(), CobaltArrowRenderer::new);

        event.registerBlockEntityRenderer(OuterEndBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(OuterEndBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        Sheets.addWoodType(OuterEndBlocks.AZURE);
    }

}
