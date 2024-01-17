package blueduck.outer_end.feature;

import blueduck.outer_end.feature.helpers.ShapeUtil;
import blueduck.outer_end.registry.OuterEndBlocks;
import com.mojang.serialization.Codec;
import com.sun.jna.platform.win32.WinBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class DeadRainbowFeature extends Feature<NoneFeatureConfiguration> {
    public DeadRainbowFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    private static ChunkAccess chunkGenerating = null;
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        List<BlockPos> points = ShapeUtil.generateCircle((float) context.random().nextInt(12) + 6);
        double angle = context.random().nextDouble() * 2 * Math.PI;
        double heightModifier = context.random().nextDouble() + 1;
        double thicknessModifier = context.random().nextDouble() + .5;

        WorldGenLevel level = context.level();

        if (context.origin().getY() > 80) {
            return false;
        }

        for (BlockPos point : points) {
            BlockPos pointPos = new BlockPos((int) (context.origin().getX() + Math.sin(angle) * point.getX()), (int) (context.origin().getY() + point.getZ() * heightModifier), (int) (context.origin().getZ() + Math.cos(angle) * point.getX()));

            List<BlockPos> points2 = ShapeUtil.generateSolidSphere((float) ((context.random().nextInt(2) + 2) * thicknessModifier));
            for (BlockPos point2 : points2) {
                BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() + point2.getY(), pointPos.getZ() + point2.getZ());
                level.setBlock(pointPos2, OuterEndBlocks.VIOLITE.get().defaultBlockState(), Block.UPDATE_NONE);
            }

        }



        return true;


    }

}
