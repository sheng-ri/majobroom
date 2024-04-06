package com.innky.majobroom.event;

import com.innky.majobroom.item.BroomItem;
import com.innky.majobroom.network.RidePack;
import com.innky.majobroom.network.SummonBroomPack;
import com.innky.majobroom.registry.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

import static com.innky.majobroom.registry.KeyboardRegistry.SUMMON_KEY;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class KeyboardListener {

    @SubscribeEvent
    public static void onHandle(InputEvent.Key event) {
        if (SUMMON_KEY.isDown() && event.getAction() == GLFW.GLFW_PRESS) {

            Player playerEntity = Minecraft.getInstance().player;
            if (playerEntity != null) {
                if (playerEntity.isPassenger()) {
                    PacketDistributor.SERVER
                            .noArg()
                            .send(new RidePack(playerEntity.getVehicle().getId(), false));
                } else {
                    PacketDistributor.SERVER
                            .noArg()
                            .send(new SummonBroomPack());
                }
                for (ItemStack item : playerEntity.getInventory().items) {
                    if (item.is(ItemRegistry.broomItem.get()) || playerEntity.isCreative()) {
                        playerEntity.level().playSound(playerEntity, playerEntity.blockPosition(), SoundEvents.ENDER_EYE_LAUNCH, SoundSource.NEUTRAL, 10F, 1f);
                        BroomItem.addParticle(playerEntity.level(), playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), 30, 2, 1);
                        break;
                    }
                }
            }
        }
    }
}
