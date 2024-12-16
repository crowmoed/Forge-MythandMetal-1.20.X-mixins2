package net.pinto.mythandmetal.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;
import net.pinto.mythandmetal.block.ModBlocks;
import java.util.Set;

public class ModBlockLootDrops extends BlockLootSubProvider {

    public ModBlockLootDrops() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.ENCHANTED_GRASS.get());
        this.dropSelf(ModBlocks.ASHBURNT_LOG.get());
        this.dropSelf(ModBlocks.ENCHANTED_LOG.get());
        this.dropSelf(ModBlocks.ASH_SAPLING.get());
        this.dropSelf(ModBlocks.ASH_DIRT.get());
        this.dropOther(ModBlocks.ASH_GRASS.get(), Blocks.DIRT);
        this.dropSelf(ModBlocks.ASH_LOG.get());

        this.add(ModBlocks.ENCHANTED_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.ASH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.ASH_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.ASH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));


        this.dropSelf(ModBlocks.ASH_SAPLING.get());
        this.dropSelf(ModBlocks.ENCHANTED_SAPLING.get());

    }





    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
