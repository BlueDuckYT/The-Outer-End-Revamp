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
            () -> EntityType.Builder.of(Stalker::new, MobCategory.MONSTER).sized(0.9f, 1.9f)
                    .build(new ResourceLocation(TheOuterEnd.MODID, "stalker").toString()));
    public static final RegistryObject<EntityType<Spectrafly>> SPECTRAFLY = ENTITIES.register("spectrafly",
            () -> EntityType.Builder.of(Spectrafly::new, MobCategory.CREATURE).sized(0.9f, 0.9f)
                    .build(new ResourceLocation(TheOuterEnd.MODID, "spectrafly").toString()));


    public static final RegistryObject<EntityType<Sinker>> SINKER = ENTITIES.register("sinker",
            () -> EntityType.Builder.of(Sinker::new, MobCategory.MONSTER).sized(1.0f, 1.6f)
                    .build(new ResourceLocation(TheOuterEnd.MODID, "sinker").toString()));





    public static final RegistryObject<EntityType<SinkerShuriken>> SINKER_SHURIKEN = ENTITIES.register("sinker_shuriken",
            () -> EntityType.Builder.<SinkerShuriken>of(SinkerShuriken::new, MobCategory.MISC).sized(0.5f, 0.5f)
                    .build(new ResourceLocation(TheOuterEnd.MODID, "sinker_shuriken").toString()));

    public static final RegistryObject<EntityType<SinkerLaser>> SINKER_LASER = ENTITIES.register("sinker_laser",
            () -> EntityType.Builder.<SinkerLaser>of(SinkerLaser::new, MobCategory.MISC).sized(0.5f, 0.5f)
                    .build(new ResourceLocation(TheOuterEnd.MODID, "sinker_laser").toString()));

    public static final RegistryObject<EntityType<CrystalArrow>> ROSE_CRYSTAL_ARROW = ENTITIES.register("rose_crystal_arrow",
            () -> EntityType.Builder.<CrystalArrow>of(CrystalArrow::new, MobCategory.MISC).sized(0.5f, 0.5f)
                    .build(new ResourceLocation(TheOuterEnd.MODID, "rose_crystal_arrow").toString()));
    public static final RegistryObject<EntityType<CrystalArrow>> MINT_CRYSTAL_ARROW = ENTITIES.register("mint_crystal_arrow",
            () -> EntityType.Builder.<CrystalArrow>of(CrystalArrow::new, MobCategory.MISC).sized(0.5f, 0.5f)
                    .build(new ResourceLocation(TheOuterEnd.MODID, "mint_crystal_arrow").toString()));
    public static final RegistryObject<EntityType<CrystalArrow>> COBALT_CRYSTAL_ARROW = ENTITIES.register("cobalt_crystal_arrow",
            () -> EntityType.Builder.<CrystalArrow>of(CrystalArrow::new, MobCategory.MISC).sized(0.5f, 0.5f)
                    .build(new ResourceLocation(TheOuterEnd.MODID, "cobalt_crystal_arrow").toString()));


}
