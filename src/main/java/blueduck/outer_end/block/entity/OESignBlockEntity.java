package blueduck.outer_end.block.entity;

import blueduck.outer_end.registry.OuterEndBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class OESignBlockEntity extends net.minecraft.world.level.block.entity.SignBlockEntity {
    public OESignBlockEntity(BlockPos p_155700_, BlockState p_155701_) {
        super(OuterEndBlockEntities.MOD_SIGN.get(), p_155700_, p_155701_);
    }

    @Override
    public BlockEntityType<?> getType() {
        return OuterEndBlockEntities.MOD_SIGN.get();
    }
}
