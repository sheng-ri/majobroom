package com.innky.majobroom;

import com.innky.majobroom.network.Network;
import com.innky.majobroom.registry.EntityTypeRegistry;
import com.innky.majobroom.registry.ItemRegistry;
import com.innky.majobroom.registry.KeyboardRegistry;
import com.innky.majobroom.utills.Config;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;


@Mod(ModMajoBroom.MODID)
public class ModMajoBroom {

    public static final String MODID = "majobroom";
    public static final String VERSION = "1.0";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ModMajoBroom(IEventBus eventBus) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        ItemRegistry.registry(eventBus);

        EntityTypeRegistry.ENTITY_TYPES.register(eventBus);
        eventBus.register(Network.class);
        eventBus.register(KeyboardRegistry.class);
    }
}
