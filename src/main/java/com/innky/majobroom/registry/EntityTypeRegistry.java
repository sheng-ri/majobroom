package com.innky.majobroom.registry;

import com.innky.majobroom.entity.MajoBroomEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class EntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, "majobroom");
    public static DeferredHolder<EntityType<?>, EntityType<MajoBroomEntity>> majoBroom = ENTITY_TYPES.register("majo_broom", () ->
            EntityType.Builder.of(MajoBroomEntity::new, MobCategory.MISC).sized(1.0f, 0.5f).build("majo_broom")
    );
}