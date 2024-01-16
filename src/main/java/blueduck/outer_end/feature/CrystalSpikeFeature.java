package blueduck.outer_end.feature;

import blueduck.outer_end.feature.helpers.ShapeUtil;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;
import java.util.Optional;

public class CrystalSpikeFeature extends Feature<NoneFeatureConfiguration> {


    public CrystalSpikeFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if ((context.level().isEmptyBlock(context.origin()))) {
            Optional<Block> optional = BuiltInRegistries.BLOCK.getTag(TagKey.create(Registries.BLOCK, new ResourceLocation("outer_end:crystal_blocks"))).flatMap((p_224980_) -> {
                return p_224980_.getRandomElement(context.random());
            }).map(Holder::value);
            int height = 8 + context.random().nextInt(25);
            int diameter = 3 + context.random().nextInt(13);
            int xOffset = -9 + context.random().nextInt(18);
            int zOffset = -9 + context.random().nextInt(18);

            List<BlockPos> points = ShapeUtil.generateSolidCircle((float) diameter / 2);

            for (BlockPos point : points) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY(), context.origin().getZ() + point.getZ());

                if (context.level().getBlockState(pointPos.below()).isAir()) {
                    if (context.random().nextBoolean() && context.random().nextBoolean()) {
                        return place(new FeaturePlaceContext<>(context.topFeature(), context.level(), context.chunkGenerator(), context.random(), context.origin().below(), context.config()));
                    }
                    return false;
                }
            }

            for (BlockPos point : points) {
                placeCrystalinShape(context.level(), context.origin().offset(point.getX(), 0, point.getZ()), context.origin().offset(xOffset, height, zOffset), context.random(), context.config(), optional.get());
            }

            return true;
        } else {
            return false;
        }
    }

    private void placeCrystalinShape(WorldGenLevel world, BlockPos startPos, BlockPos endPos, RandomSource random, NoneFeatureConfiguration config, Block blockToPlace) {
        List<BlockPos> line = ShapeUtil.generateLine(startPos, endPos);

        for (int i = 0; i < line.size(); i++) {
            BlockPos pos = line.get(i);
            if (blockToPlace != null)
                world.setBlock(new BlockPos(pos), blockToPlace.defaultBlockState(), 2);
        }
    }
}
