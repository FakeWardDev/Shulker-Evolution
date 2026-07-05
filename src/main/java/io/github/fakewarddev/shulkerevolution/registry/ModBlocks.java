package io.github.fakewarddev.shulkerevolution.registry;

import io.github.fakewarddev.shulkerevolution.ShulkerEvolution;
import io.github.fakewarddev.shulkerevolution.block.TestBlock;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class ModBlocks {

    private ModBlocks() {
    }

    private static ResourceKey<Block> blockKey(String name) {
        return ResourceKey.create(
                Registries.BLOCK,
                ShulkerEvolution.id(name)
        );
    }

    private static ResourceKey<Item> itemKey(String name) {
        return ResourceKey.create(
                Registries.ITEM,
                ShulkerEvolution.id(name)
        );
    }

    public static final Block TEST_BLOCK = register(
            "test_block",
            new TestBlock(
                    BlockBehaviour.Properties.of()
                            .strength(2.0F)
            )
    );

    private static Block register(String name, Block block) {

        ResourceKey<Block> blockKey = blockKey(name);
        ResourceKey<Item> itemKey = itemKey(name);

        Registry.register(
                BuiltInRegistries.BLOCK,
                blockKey,
                block
        );

        Registry.register(
                BuiltInRegistries.ITEM,
                itemKey,
                new BlockItem(
                        block,
                        new Item.Properties()
                                .setId(itemKey)
                                .useBlockDescriptionPrefix()
                )
        );

        return block;
    }

    public static void initialize() {
        ShulkerEvolution.LOGGER.info("Registering Mod Blocks");
    }
}