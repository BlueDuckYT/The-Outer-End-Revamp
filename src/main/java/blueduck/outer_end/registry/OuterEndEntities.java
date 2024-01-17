package blueduck.outer_end.registry;

import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OuterEndEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
            TheOuterEnd.MODID);

    public static final RegistryObject<EntityType<PurpurGolem>> PURPUR_GOLEM = ENTITIES.register("purpur_golem",
            () -> EntityType.Builder.of(PurpurGolem::new, MobCategory.MONSTER).sized(1.4f, 2.7f)
                    .build(new ResourceLocation(TheOuterEnd.MODID, "purpur_golem").toString()));
    public static final RegistryObject<EntityType<Himmelite>> HIMMELITE = ENTITIES.register("himmelite",
            () -> EntityType.Builder.of(Himmelite::new, MobCategory.MONSTER).sized(0.9f, 0.9f)
                    .build(new ResourceLocation(TheOuterEnd.MODID, "himmelite").toString()));

    public static final RegistryObject<EntityType<Entombed>> ENTOMBED = ENTITIES.register("entombed",
            () -> EntityType.Builder.of(Entombed::new, MobCategory.MONSTER).sized(1.2f, 2.4f)
                    .build(new ResourceLocation(TheOuterEnd.MODID, "entombed").toString()));
    public static final RegistryObject<EntityType<Stalker>> STALKER = ENTITIES.register("stalker",
            () -> EntityType.Builder.of(Stalker::new, MobCategory.CREATURE).sized(1.0f, 1.9f)
                    .build(new ResourceLocation(TheOuterEnd.MODID, "stalker").toString()));
    public static final RegistryObject<EntityType<Spectrafly>> SPECTRAFLY = ENTITIES.register("spectrafly",
            () -> EntityType.Builder.of(Spectrafly::new, MobCategory.CREATURE).sized(0.9f, 0.9f)
                    .build(new ResourceLocation(TheOuterEnd.MODID, "spectrafly").toString()));


}
