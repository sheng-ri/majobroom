package com.innky.majobroom.network;

import com.innky.majobroom.ModMajoBroom;
import com.innky.majobroom.MajoBroomEntity;
import com.innky.majobroom.registry.EntityTypeRegistry;
import com.innky.majobroom.registry.ItemRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.innky.majobroom.registry.ComponentRegistry.CONTROL_MODE;

public record SummonBroomPack() implements CustomPacketPayload {

    public static final Type<SummonBroomPack> TYPE = new Type<>(new ResourceLocation(ModMajoBroom.MODID, "summon_broom"));

    public static final SummonBroomPack INSTANCE = new SummonBroomPack();
    public static final StreamCodec<FriendlyByteBuf, SummonBroomPack> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext ctx) {
        // main thread
        ctx.enqueueWork(() -> {
            final var player = ctx.player();
            if (!player.isPassenger()) {
                for (ItemStack item : player.getInventory().items) {
                    if (item.is(ItemRegistry.broomItem.get()) || player.isCreative()) {
                        summonBroom(player, item);
                        break;
                    }
                }
            }
        });
    }

    private static void summonBroom(Player player, ItemStack itemStack) {
        MajoBroomEntity broomEntity = new MajoBroomEntity(EntityTypeRegistry.majoBroom.get(), player.level());
//        broomEntity.setYHeadRot(playerEntity.getYHeadRot());
        broomEntity.setYRot(player.getYRot());
        broomEntity.setPos(player.getX(), player.getY(), player.getZ());
        broomEntity.setControlMode(itemStack.getOrDefault(CONTROL_MODE,false));
        player.level().addFreshEntity(broomEntity);
        if (!player.isCreative()) {
            itemStack.shrink(1);
        }
        player.startRiding(broomEntity);
    }
}