package com.innky.majobroom.item;

import com.innky.majobroom.registry.ItemRegistry;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;


public class ModArmorMaterial {
    public static final Holder<ArmorMaterial> CLOTH = register(
            "majo_cloth",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 5);
                map.put(ArmorItem.Type.HELMET, 2);
                //map.put(ArmorItem.Type.BODY, 3);
            }),
            20,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F,
            0.0F,
            () -> Ingredient.of(ItemRegistry.broomItem.get()));

    private static Holder<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> p_324599_,
            int p_324319_,
            Holder<SoundEvent> p_324145_,
            float p_323494_,
            float p_324549_,
            Supplier<Ingredient> p_323845_
    ) {
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
            enummap.put(armoritem$type, p_324599_.get(armoritem$type));
        }

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                new ResourceLocation(name),
                new ArmorMaterial(enummap, p_324319_, p_324145_, p_323845_, List.of(
                        new ArmorMaterial.Layer(new ResourceLocation("leather"), "", false)
//                        , new ArmorMaterial.Layer(new ResourceLocation("leather"), "_overlay", false)
                ), p_323494_, p_324549_)
        );
    }
}