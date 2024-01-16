package blueduck.outer_end.registry;

import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.feature.AzureTreeFeature;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OuterEndFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TheOuterEnd.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> AZURE_TREE_FEATURE = FEATURES.register("azure_tree_feature", () -> new AzureTreeFeature(NoneFeatureConfiguration.CODEC));


}
