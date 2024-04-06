package com.innky.majobroom.mixin.unused;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Entity.class)
public abstract class EntityMixin {
//
//    @Shadow
//    @Nullable
//    public abstract Entity getVehicle();
//
//    @Inject(method = "isShiftKeyDown", at = @At(value = "HEAD"), cancellable = true)
//    private void isShiftKeyDown(CallbackInfoReturnable<Boolean> cir) {
//        if (this.getVehicle() instanceof MajoBroom) {
////            cir.setReturnValue(false);
//        }
//    }
//
//    @Inject(method = "isInWater", at = @At(value = "HEAD"), cancellable = true)
//    private void isInWater(CallbackInfoReturnable<Boolean> cir) {
//        if (this.getVehicle() instanceof MajoBroom) {
//            cir.setReturnValue(true);
//        }
//
//
//    }
}
