package com.innky.majobroom.registry;

import com.innky.majobroom.ModMajoBroom;
import com.innky.majobroom.item.MajoWearableItem;
import com.innky.majobroom.item.Modelinit;
import com.innky.majobroom.item.BroomItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforgespi.Environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    public static DeferredRegister.Items ITEMS = DeferredRegister.createItems("majobroom");

    public static DeferredItem<Item> broomItem = ITEMS.register(
            "broom_item", () -> new BroomItem(new Item.Properties()));

    public static Map<String, DeferredItem<Item>> itemMap = new HashMap<>();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModMajoBroom.MODID);
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> MOD_TAB;

    public static void register(IEventBus eventBus) {
        boolean isRemote = Environment.get().getDist() == Dist.DEDICATED_SERVER;

        itemMap.put("majo_hat", ITEMS.register("majo_hat", () -> new MajoWearableItem(ArmorItem.Type.HELMET)));
        itemMap.put("majo_cloth", ITEMS.register("majo_cloth", () -> new MajoWearableItem(ArmorItem.Type.CHESTPLATE)));
        Modelinit.reg("majo_hat", isRemote);
        Modelinit.reg("majo_cloth", isRemote);

        MOD_TAB = CREATIVE_MODE_TABS.register("majo_group", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.majo_group")) //The language key for the title of your CreativeModeTab
                .withTabsBefore(CreativeModeTabs.COMBAT)
                .icon(() -> ItemRegistry.broomItem.get().getDefaultInstance())
                .displayItems((parameters, output) -> {
                    for (DeferredItem<Item> holder : itemMap.values()) {
                        output.accept(holder.get());
                    }
                }).build());

        CREATIVE_MODE_TABS.register(eventBus);
        ITEMS.register(eventBus);
    }
}

