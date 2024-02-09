package blueduck.outer_end.registry;

import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.feature.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OuterEndFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TheOuterEnd.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> AZURE_TREE_FEATURE = FEATURES.register("azure_tree_feature", () -> new AzureTreeFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> AZURE_BERRY_VINES_FEATURE = FEATURES.register("azure_berry_vines", () -> new AzureBerryVineFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> AZURE_VINES_FEATURE = FEATURES.register("azure_vines", () -> new AzureVinesFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CRYSTAL_SPIKE_FEATURE = FEATURES.register("crystal_spike_feature", () -> new CrystalSpikeFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> DEAD_RAINBOW_FEATURE = FEATURES.register("dead_rainbow_feature", () -> new DeadRainbowFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CRAG_MOON_FEATURE = FEATURES.register("crag_moon_feature", () -> new CragMoonFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CRAG_BOULDER_FEATURE = FEATURES.register("crag_boulder_feature", () -> new CragBoulderFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> STROMATOLITE_FEATURE = FEATURES.register("stromatolite_feature", () -> new StromatoliteFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> HALITE_FEATURE = FEATURES.register("halite_feature", () -> new HaliteFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> PRIMORDIAL_POOL_FEATURE = FEATURES.register("primordial_pool_feature", () -> new PrimordialPoolFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FROZEN_GEODE_FEATURE = FEATURES.register("frozen_geode_feature", () -> new FrozenGeodeFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> ANCIENT_ICICLE_FEATURE = FEATURES.register("ancient_icicle_feature", () -> new AncientIcicleFeature(NoneFeatureConfiguration.CODEC));

}
