package blueduck.outer_end.block;



import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class EnderGrassBlock extends Block implements net.minecraftforge.common.IPlantable {
	private final Supplier<BlockState> diesTo;
	private final Function<Random, BlockState> vegitationGetter;
	
//	@Override
//	public boolean canGrow(Level worldIn, BlockPos pos, BlockState state, boolean isClient) {
//		return true;
//	}
//
//	@Override
//	public boolean canUseBonemeal(Level worldIn, Random rand, BlockPos pos, BlockState state) {
//		return true;
//	}
	
//	@Override
//	public void grow(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
//		for (int i = 0; i < rand.nextInt(3) + 3; i++) {
//			int xOff = rand.nextInt(5) * (rand.nextBoolean() ? 1 : -1);
//			int yOff = rand.nextInt(1) * (rand.nextBoolean() ? 1 : -1);
//			int zOff = rand.nextInt(5) * (rand.nextBoolean() ? 1 : -1);
//			if (worldIn.getBlockState(pos.add(xOff, yOff, zOff)).getBlock().equals(this.getBlock())) {
//				if (worldIn.getBlockState(pos.add(xOff, yOff + 1, zOff)).getBlock().equals(Blocks.AIR)) {
//					worldIn.setBlockState(pos.add(xOff, yOff + 1, zOff), vegitationGetter.apply(rand));
//				}
//			}
//		}
//	}
	
	public EnderGrassBlock(Properties properties, Supplier<BlockState> diesTo, Function<Random, BlockState> vegitationGetter) {
		super(properties);
		this.diesTo = diesTo;
		this.vegitationGetter = vegitationGetter;
	}

	
//	@Override
//	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
//		super.randomTick(state, worldIn, pos, random);
//		//TODO: get this forge patch code working (helps prevent lag from excess chunk loading)
////		if (!worldIn.isAreaLoaded(pos, 3)) return;
//		if (worldIn.getBlockState(pos.up()).isSolid())
//			worldIn.setBlockState(pos, diesTo.get());
//
//		for (int i = 0; i < 4; ++i) {
//			BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
//			if (worldIn.getBlockState(blockpos).isIn(Blocks.END_STONE)) {
//				if (!worldIn.getBlockState(blockpos.up()).isSolid()) {
//					ResourceLocation biome = worldIn.getBiome(blockpos).getRegistryName();
//					if (biome != null && biome.toString().equals("outer_end:azure_forest") || worldIn.getLight(pos.up()) >= 10) {
//						worldIn.setBlockState(blockpos, state);
//					}
//				}
//			}
//		}
//	}

	@Override
	public BlockState getPlant(BlockGetter level, BlockPos pos) {
		return null;
	}
}
