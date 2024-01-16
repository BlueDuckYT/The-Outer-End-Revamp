package blueduck.outer_end.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;

public class EnderDoublePlant extends DoublePlantBlock {
    public EnderDoublePlant(Properties p_52861_) {
        super(p_52861_);
    }
    public boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
        return p_51042_.isFaceSturdy(p_51043_, p_51044_.below(), Direction.UP);
    }
}
