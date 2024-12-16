package net.pinto.mythandmetal.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.pinto.mythandmetal.block.ModBlocks;

public class CustomGrassBlock extends GrassBlock {
    public CustomGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        BlockState aboveState = world.getBlockState(pos.above());

        boolean isTransparent = aboveState.propagatesSkylightDown(world, pos.above());
        int lightLevel = world.getMaxLocalRawBrightness(pos.above());
        if (!isTransparent || lightLevel < 4) {
            world.setBlock(pos, ModBlocks.ASH_DIRT.get().defaultBlockState(), 2);
        } else {
            super.randomTick(state, world, pos,  random);
        }
    }

}
