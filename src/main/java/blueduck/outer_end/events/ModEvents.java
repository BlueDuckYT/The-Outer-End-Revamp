package blueduck.outer_end.events;

import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.entity.*;
import blueduck.outer_end.registry.OuterEndEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = TheOuterEnd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(OuterEndEntities.PURPUR_GOLEM.get(), PurpurGolem.createAttributes().build());
        event.put(OuterEndEntities.HIMMELITE.get(), Himmelite.createAttributes().build());
        event.put(OuterEndEntities.ENTOMBED.get(), Entombed.createAttributes().build());
        event.put(OuterEndEntities.STALKER.get(), Stalker.createAttributes().build());
        event.put(OuterEndEntities.SPECTRAFLY.get(), Spectrafly.createAttributes().build());
        event.put(OuterEndEntities.SINKER.get(), Sinker.createAttributes().build());

    }

    @SubscribeEvent
    public static void registerPlacement(SpawnPlacementRegisterEvent event) {
        event.register(OuterEndEntities.PURPUR_GOLEM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, PurpurGolem::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(OuterEndEntities.HIMMELITE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Himmelite::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(OuterEndEntities.ENTOMBED.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Entombed::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(OuterEndEntities.STALKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Stalker::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(OuterEndEntities.SPECTRAFLY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Spectrafly::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(OuterEndEntities.SINKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Sinker::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
    }




}
