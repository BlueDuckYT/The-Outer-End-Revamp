package blueduck.outer_end.feature.helpers;

import blueduck.outer_end.registry.OuterEndFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class AzureTreeGrower extends AbstractTreeGrower {
    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_256119_, boolean p_256536_) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation("outer_end:azure_tree_feature"));

    }
}