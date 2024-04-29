package com.innky.majobroom.registry;

import com.innky.majobroom.ModMajoBroom;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ComponentsRegistry {

    public static final DeferredRegister.DataComponents MAJO_COMPOENTS = DeferredRegister.createDataComponents(ModMajoBroom.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> CONTROL_MODE =
            MAJO_COMPOENTS.registerComponentType(
                    "control_mode",
                    (builder) -> builder.persistent(Codec.BOOL)
            );

    public static void register(IEventBus eventBus) {
        MAJO_COMPOENTS.register(eventBus);
    }
}
