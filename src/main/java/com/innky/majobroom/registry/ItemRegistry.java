package com.innky.majobroom.registry;

import com.innky.majobroom.ModMajoBroom;
import com.innky.majobroom.armors.MajoWearableItem;
import com.innky.majobroom.armors.ModArmorMaterial;
import com.innky.majobroom.armors.Modelinit;
import com.innky.majobroom.item.BroomItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    public static DeferredRegister.Items ITEMS;
    public static DeferredItem<Item> broomItem;
    public static Map<String, DeferredItem<Item>> itemMap = new HashMap<>();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModMajoBroom.MODID);
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> MOD_TAB;

    public static void registry(IEventBus eventBus) {
        ITEMS = DeferredRegister.createItems("majobroom");
        broomItem = ITEMS.register("broom_item", () ->
                new BroomItem(new Item.Properties())
        );
        itemMap.put("majobroom",broomItem);
        boolean isRemote = true;
        try {
            System.out.println(HumanoidModel.class);
        } catch (NoClassDefFoundError e) {
            isRemote = false;
        }
        try {
            InputStream in = ModMajoBroom.class.getClassLoader().getResourceAsStream("/assets/majobroom/jsonmodels/model_list.txt");
            if (in != null) {
                BufferedReader bf = new BufferedReader(new InputStreamReader(in));
                String temp;
                while ((temp = bf.readLine()) != null) {
                    String[] results = temp.split(",");
                    switch (results[1]) {
                        case "3":
                            itemMap.put(results[0], ITEMS.register(results[0], () -> new MajoWearableItem(ModArmorMaterial.CLOTH, ArmorItem.Type.HELMET, new Item.Properties())));
                            break;
                        case "2":
                            itemMap.put(results[0], ITEMS.register(results[0], () -> new MajoWearableItem(ModArmorMaterial.CLOTH, ArmorItem.Type.CHESTPLATE, new Item.Properties())));
                            break;
                        case "1":
                            itemMap.put(results[0], ITEMS.register(results[0], () -> new MajoWearableItem(ModArmorMaterial.CLOTH, ArmorItem.Type.LEGGINGS, new Item.Properties())));
                            break;
                        case "0":
                            itemMap.put(results[0], ITEMS.register(results[0], () -> new MajoWearableItem(ModArmorMaterial.CLOTH, ArmorItem.Type.BOOTS, new Item.Properties())));
                            break;
                    }
                    Modelinit.reg(results[0], isRemote);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

