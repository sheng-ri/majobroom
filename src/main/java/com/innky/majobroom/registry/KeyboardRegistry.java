package com.innky.majobroom.registry;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;
import org.lwjgl.glfw.GLFW;

public class KeyboardRegistry {

    public static final KeyMapping SUMMON_KEY = new KeyMapping("key.summon",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "key.category.majobroom");

    @SubscribeEvent
    public static void onClientSetup(RegisterKeyMappingsEvent event) {
        event.register(SUMMON_KEY);
    }
}
