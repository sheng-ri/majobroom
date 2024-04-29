package com.innky.majobroom.registry;

import com.innky.majobroom.BroomRenderer;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@SuppressWarnings("unused")
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegistry {

    public static final int DEFAULT_COLOR = FastColor.ARGB32.color(221, 163, 199);

    @SubscribeEvent
    public static void onClientSetUpEvent(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeRegistry.majoBroom.get(), BroomRenderer::new);
    }

    @SubscribeEvent
    public static void onClientSetUpEvent(RegisterColorHandlersEvent.Item event) {
        // Now don't need because dye components?
        event.register((stack, color) -> color > 0 ? -1 : DyedItemColor.getOrDefault(stack, DEFAULT_COLOR), ItemRegistry.HAT);
    }
}