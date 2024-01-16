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
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> AZURE_VINES_FEATURE = FEATURES.register("azure_vines", () -> new AzureVinesFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CRYSTAL_SPIKE_FEATURE = FEATURES.register("crystal_spike_feature", () -> new CrystalSpikeFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> DEAD_RAINBOW_FEATURE = FEATURES.register("dead_rainbow_feature", () -> new DeadRainbowFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CRAG_SPHERE_FEATURE = FEATURES.register("crag_sphere_feature", () -> new CragSphereFeature(NoneFeatureConfiguration.CODEC));


}
