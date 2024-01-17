package blueduck.outer_end.events;

import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.client.model.*;
import blueduck.outer_end.client.renderer.*;
import blueduck.outer_end.registry.OuterEndBlockEntities;
import blueduck.outer_end.registry.OuterEndBlocks;
import blueduck.outer_end.registry.OuterEndEntities;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TheOuterEnd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PurpurGolemModel.LAYER_LOCATION, PurpurGolemModel::createBodyLayer);
        event.registerLayerDefinition(HimmeliteModel.LAYER_LOCATION, HimmeliteModel::createBodyLayer);
        event.registerLayerDefinition(EntombedModel.LAYER_LOCATION, EntombedModel::createBodyLayer);
        event.registerLayerDefinition(StalkerModel.LAYER_LOCATION, StalkerModel::createBodyLayer);
        event.registerLayerDefinition(SpectraflyModel.LAYER_LOCATION, SpectraflyModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OuterEndEntities.PURPUR_GOLEM.get(), PurpurGolemRenderer::new);
        event.registerEntityRenderer(OuterEndEntities.HIMMELITE.get(), HimmeliteRenderer::new);
        event.registerEntityRenderer(OuterEndEntities.ENTOMBED.get(), EntombedRenderer::new);
        event.registerEntityRenderer(OuterEndEntities.STALKER.get(), StalkerRenderer::new);
        event.registerEntityRenderer(OuterEndEntities.SPECTRAFLY.get(), SpectraflyRenderer::new);

        event.registerBlockEntityRenderer(OuterEndBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(OuterEndBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        Sheets.addWoodType(OuterEndBlocks.AZURE);
    }

}
