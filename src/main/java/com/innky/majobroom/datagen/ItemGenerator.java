package com.innky.majobroom.datagen;

import com.innky.majobroom.registry.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public class ItemGenerator extends ItemTagsProvider {
    public ItemGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags) {
        super(pOutput, pLookupProvider, pBlockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.DYEABLE)
                .add(ItemRegistry.HAT.get(), ItemRegistry.CLOTH.get());
    }
}
