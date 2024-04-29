package com.innky.majobroom.item;

import com.innky.majobroom.ModMajoBroom;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.innky.majobroom.item.ModArmorMaterial.CLOTH;

public class MajoWearableItem extends ArmorItem {

    public static final ResourceLocation MAJO_CLOTH_TEXTURE = new ResourceLocation("majobroom", "jsonmodels/textures/majo_cloth.png");
    public static final ResourceLocation MAJO_HAT_TEXTURE = new ResourceLocation("majobroom", "jsonmodels/textures/majo_hat_overlay.png");

    public MajoWearableItem(Type p_266831_ ) {
        super(CLOTH, p_266831_, new Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(40)));
    }

    // TODO: CHECK it can dye.
//    @Override
//    public int getColor(ItemStack stack) {
//        CompoundTag compoundnbt = stack.get(ArmorMaterial.CODEC)
//        return compoundnbt != null && compoundnbt.contains("color", 99) ? compoundnbt.getInt("color") : 0xdda3c7;
//    }
    public int getColor(ItemStack stack) {
        return 0xdda3c7;
    }

//    @Override
//    public void onArmorTick(ItemStack stack, Level world, Player player) {
////        player.addPotionEffect(new EffectInstance(Effects.SPEED,60,3));
//    }


    @Override
    public @NotNull Object getRenderPropertiesInternal() {

        return new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                return Modelinit.modelMap.get(itemStack.getDescriptionId().substring(15));
            }
        };
    }

    /*
        overlay for dye
     */
//    @Override
//    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
//        if (stack.getDescriptionId().contains("cloth")) {
//            return MAJO_CLOTH_TEXTURE;
//        } else {
//            return MAJO_HAT_TEXTURE;
//        }
//    }
}
