package com.innky.majobroom;

import com.innky.majobroom.registry.NetworkRegistry;
import com.innky.majobroom.registry.ComponentRegistry;
import com.innky.majobroom.registry.EntityTypeRegistry;
import com.innky.majobroom.registry.ItemRegistry;
import com.innky.majobroom.registry.KeyboardRegistry;
import com.innky.majobroom.utills.Config;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;


@Mod(ModMajoBroom.MODID)
public class ModMajoBroom {

    public static final String MODID = "majobroom";
    public static final String VERSION = "2.0.2";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ModMajoBroom(IEventBus eventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        ItemRegistry.register(eventBus);
        EntityTypeRegistry.register(eventBus);
        ComponentRegistry.register(eventBus);

        eventBus.register(NetworkRegistry.class);
        eventBus.register(KeyboardRegistry.class);
    }
}
