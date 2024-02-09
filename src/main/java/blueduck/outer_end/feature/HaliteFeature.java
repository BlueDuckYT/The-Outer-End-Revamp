package blueduck.outer_end.feature;

import blueduck.outer_end.feature.helpers.ShapeUtil;
import blueduck.outer_end.registry.OuterEndBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class HaliteFeature extends Feature<NoneFeatureConfiguration> {


    public HaliteFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if ((context.level().isEmptyBlock(context.origin()))) {

            if (context.origin().getY() < 20 || !context.level().getBlockState(context.origin().below()).is(Blocks.END_STONE)) {
                return false;
            }
            int height = 4 + context.random().nextInt(10);
            Direction[] offsets = new Direction[] { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

            for (int i = 0; i < height; i++) {
                if (context.level().getBlockState(context.origin().offset(0,i,0)).is(Blocks.AIR) || !context.level().getBlockState(context.origin().offset(0,i,0)).isCollisionShapeFullBlock(context.level(), context.origin().offset(0,i,0)) && context.random().nextDouble() > 0.05) {
                    context.level().setBlock(context.origin().offset(0, i, 0), OuterEndBlocks.HALITE.get().defaultBlockState(), Block.UPDATE_NONE);
                }
            }
            for (Direction dir : offsets) {
                for (int i = 0; i < context.random().nextInt(height); i++) {
                    if (context.level().getBlockState(context.origin().offset(dir.getNormal()).offset(0,i,0)).is(Blocks.AIR) || !context.level().getBlockState(context.origin().offset(dir.getNormal()).offset(0,i,0)).isCollisionShapeFullBlock(context.level(), context.origin().offset(dir.getNormal()).offset(0,i,0)) && context.random().nextDouble() > 0.05) {
                        context.level().setBlock(context.origin().offset(dir.getNormal()).offset(0, i, 0), OuterEndBlocks.HALITE.get().defaultBlockState(), Block.UPDATE_NONE);
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }


}
