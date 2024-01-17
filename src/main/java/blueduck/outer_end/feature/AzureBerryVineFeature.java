package blueduck.outer_end.feature;

import blueduck.outer_end.block.AzureBerryVineBlock;
import blueduck.outer_end.registry.OuterEndBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class AzureBerryVineFeature extends Feature<NoneFeatureConfiguration> {
    public AzureBerryVineFeature(Codec<NoneFeatureConfiguration> p_66219_) {
        super(p_66219_);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159956_) {
        int i = 0;
        WorldGenLevel worldgenlevel = p_159956_.level();
        BlockPos blockpos = p_159956_.origin();
        RandomSource randomsource = p_159956_.random();
        int j = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR, blockpos.getX(), blockpos.getZ());
        BlockPos blockpos1 = new BlockPos(blockpos.getX(), j, blockpos.getZ());
        if (worldgenlevel.getBlockState(blockpos1).is(Blocks.AIR)) {
            BlockState blockstate = OuterEndBlocks.AZURE_BERRY_VINE_TOP.get().defaultBlockState();
            BlockState blockstate1 = OuterEndBlocks.AZURE_BERRY_VINE.get().defaultBlockState();
            int k = 1 + randomsource.nextInt(10);

            for(int l = 0; l <= k; ++l) {
                if (worldgenlevel.getBlockState(blockpos1).is(Blocks.AIR) && worldgenlevel.getBlockState(blockpos1.above()).is(Blocks.AIR) && blockstate1.canSurvive(worldgenlevel, blockpos1)) {
                    if (l == k) {
                        worldgenlevel.setBlock(blockpos1, blockstate, 2);
                        ++i;
                    } else {
                        worldgenlevel.setBlock(blockpos1, blockstate1, 2);
                    }
                } else if (l > 0) {
                    BlockPos blockpos2 = blockpos1.below();
                    if (blockstate.canSurvive(worldgenlevel, blockpos2) && !worldgenlevel.getBlockState(blockpos2.below()).is(OuterEndBlocks.AZURE_BERRY_VINE.get())) {
                        worldgenlevel.setBlock(blockpos2.below(), blockstate.setValue(AzureBerryVineBlock.AGE, Integer.valueOf(randomsource.nextInt(2) + 1)), Block.UPDATE_ALL_IMMEDIATE);
                        ++i;
                    }
                    break;
                }

                blockpos1 = blockpos1.above();
            }
        }

        return i > 0;
    }
}