package com.mamiri263.obsidianbucket;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObsidianBucket implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("obsidianBucket");

    public static final Item OBSIDIAN_BUCKET =
            Registry.register(Registries.ITEM, new Identifier("wynnu", "obsidian_bucket"),
                    new Item(new FabricItemSettings().maxCount(1)));
    public static final Item MOLTEN_OBSIDIAN =
            Registry.register(Registries.ITEM, new Identifier("wynnu", "molten_obsidian"),
                    new Item(new FabricItemSettings()));
    public static final Block MOLTEN_OBSIDIAN_BLOCK =
            Registry.register(Registries.BLOCK, new Identifier("wynnu", "molten_obsidian_block"),
                new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).luminance(10).hardness(40f)));

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier("wynnu", "molten_obsidian_block"), new BlockItem(MOLTEN_OBSIDIAN_BLOCK, new FabricItemSettings()));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            content.addAfter(Items.MILK_BUCKET, OBSIDIAN_BUCKET);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.addAfter(Items.CRYING_OBSIDIAN, MOLTEN_OBSIDIAN_BLOCK);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter(Items.IRON_INGOT, MOLTEN_OBSIDIAN);
        });


        LOGGER.info("Minecraft obsidian bucket is working!");
    }
}