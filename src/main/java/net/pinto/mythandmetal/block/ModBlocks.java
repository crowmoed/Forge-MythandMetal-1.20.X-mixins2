package net.pinto.mythandmetal.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pinto.mythandmetal.MythandMetal;
import net.pinto.mythandmetal.block.custom.CustomDirtBlock;
import net.pinto.mythandmetal.block.custom.CustomGrassBlock;
import net.pinto.mythandmetal.block.custom.CustomRotatingBlock;
import net.pinto.mythandmetal.item.ModItems;
import net.pinto.mythandmetal.worldgen.tree.AshTreeGrower;
import net.pinto.mythandmetal.worldgen.tree.EnchantedTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MythandMetal.MOD_ID);



    public static final RegistryObject<Block> ASH_DIRT = registerBlock("ash_dirt",
            () -> new CustomDirtBlock(BlockBehaviour.Properties.copy(Blocks.DIRT).randomTicks()));

    public static final RegistryObject<Block> ASH_GRASS = registerBlock("ash_grass",
            () -> new CustomGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));

    public static final RegistryObject<Block> ENCHANTED_GRASS = registerBlock("enchanted_grass",
            () -> new CustomGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));


    public static final RegistryObject<Block> ASH_LOG = registerBlock("ash_log",
            () -> new CustomRotatingBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.0F).lightLevel((state) -> 12).randomTicks()));

    public static final RegistryObject<Block> ASHBURNT_LOG = registerBlock("ashburnt_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.0F).lightLevel((state) -> 12)));


    public static final RegistryObject<Block> ENCHANTED_LOG = registerBlock("enchanted_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.0F)));

    public static final RegistryObject<Block> ASH_LEAVES = registerBlock("ash_leaves",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).mapColor(MapColor.PLANT).strength(0.2F).randomTicks().noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> ENCHANTED_LEAVES = registerBlock("enchanted_leaves",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).mapColor(MapColor.PLANT).strength(0.2F).randomTicks().noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY)));


    public static final RegistryObject<Block> ENCHANTED_SAPLING = registerBlock("enchanted_sapling",
            () -> new SaplingBlock(new EnchantedTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> ASH_SAPLING = registerBlock("ash_sapling",
            () -> new SaplingBlock(new AshTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }




    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    private static RotatedPillarBlock log(MapColor pTopMapColor, MapColor pSideMapColor) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor((p_152624_) -> {
            return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? pTopMapColor : pSideMapColor;
        }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    }
}
