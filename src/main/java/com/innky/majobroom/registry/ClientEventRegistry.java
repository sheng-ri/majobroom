package com.innky.majobroom.registry;

import com.innky.majobroom.armors.MajoWearableItem;
import com.innky.majobroom.entity.renderer.BroomRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColors;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventRegistry {
    @SubscribeEvent
    public static void onClientSetUpEvent(EntityRenderersEvent.RegisterRenderers event) {
//        System.out.println(111);
//        RegistryObject a = EntityTypeRegistry.majoBroom;
//        IForgeRegistryEntry b = a.get();
        event.registerEntityRenderer(EntityTypeRegistry.majoBroom.get(), BroomRenderer::new);
        ItemColors itemColors = Minecraft.getInstance().itemColors;
        itemColors.register((stack, color) -> {
            return color > 0 ? -1 : ((MajoWearableItem) stack.getItem()).getColor(stack);
        }, ItemRegistry.itemMap.get("majo_hat").get());
    }

}