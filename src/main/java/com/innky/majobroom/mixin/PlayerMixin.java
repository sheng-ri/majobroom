package com.innky.majobroom.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {


    protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    //    @Inject(method = "tick",  at = @At(value = "HEAD"))
//    private void tick(CallbackInfo ci){
//        this.getAbilities().flying = this.getVehicle() instanceof MajoBroom;
//    }

    /*
        Player now using lerp for standing eye height.
        TODO: ???
     */
//    @Inject(method = "getStandingEyeHeight", at = @At(value = "HEAD"), cancellable = true)
//    private void getStandingEyeHeight(Pose p_36259_, EntityDimensions p_36260_, CallbackInfoReturnable<Float> cir) {
//        if (this.getVehicle() instanceof MajoBroomEntity) {
//            cir.setReturnValue(1.62F);
//        }
//
//    }


}
