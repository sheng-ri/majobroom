package com.innky.majobroom.network;

import com.innky.majobroom.ModMajoBroom;
import com.innky.majobroom.entity.MajoBroomEntity;
import com.innky.majobroom.registry.EntityTypeRegistry;
import com.innky.majobroom.registry.ItemRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record SummonBroomPack() implements CustomPacketPayload {

    static final ResourceLocation ID = new ResourceLocation(ModMajoBroom.MODID, "summon_broom");

    public SummonBroomPack(FriendlyByteBuf buf) {
        this();
    }

    @Override
    public void write(FriendlyByteBuf pBuffer) {

    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handle(SummonBroomPack summonBroomPack, PlayPayloadContext ctx) {
        if (ctx.player().isEmpty()) return;

        // main thread
        ctx.workHandler().submitAsync(() -> {
            final var player = ctx.player().get();
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

    private static void summonBroom(Player playerEntity, ItemStack itemStack) {
        MajoBroomEntity broomEntity = new MajoBroomEntity(EntityTypeRegistry.majoBroom.get(), playerEntity.level());
//        broomEntity.setYHeadRot(playerEntity.getYHeadRot());
        broomEntity.setYRot(playerEntity.getYRot());
        broomEntity.setPos(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ());
        broomEntity.setControlMode(itemStack.getOrCreateTag().getBoolean("controlMode"));
        playerEntity.level().addFreshEntity(broomEntity);
        if (!playerEntity.isCreative()) {
            itemStack.shrink(1);
        }
        playerEntity.startRiding(broomEntity);

    }
}
