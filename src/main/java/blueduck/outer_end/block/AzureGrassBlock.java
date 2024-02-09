package blueduck.outer_end.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class AzureGrassBlock extends Block {
    public Supplier<Block> replaceBlock;
    public AzureGrassBlock(Properties properties, Supplier<Block> replace) {
        super(properties);
        replaceBlock = replace;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        if ((level.getBlockState(pos.above()).canOcclude()  && level.getBlockState(pos.above()).isFaceSturdy(level, pos.above(), Direction.DOWN)) || level.getBlockState(pos.above()).is(Blocks.LAVA)  || level.getBlockState(pos.above()).is(Blocks.WATER)) {
            level.setBlockAndUpdate(pos, replaceBlock.get().defaultBlockState());
        }
        else {

            level.setBlockAndUpdate(pos, state);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.tick(state, level, pos, random);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if ((level.getBlockState(pos.above()).canOcclude() && level.getBlockState(pos.above()).isFaceSturdy(level, pos.above(), Direction.DOWN)) || level.getBlockState(pos.above()).is(Blocks.LAVA) || level.getBlockState(pos.above()).is(Blocks.WATER)) {
            level.setBlockAndUpdate(pos, replaceBlock.get().defaultBlockState());
            level.updateNeighborsAt(pos.below(), this);
        }
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }





}