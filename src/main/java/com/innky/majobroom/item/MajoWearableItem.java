package com.innky.majobroom.item;

import com.innky.majobroom.registry.ClothModels;
import com.innky.majobroom.registry.ItemRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import static com.innky.majobroom.item.ModArmorMaterial.CLOTH;

public class MajoWearableItem extends ArmorItem {

    public MajoWearableItem(Type p_266831_ ) {
        super(CLOTH, p_266831_, new Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(40)));
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
                if (itemStack.is(ItemRegistry.HAT)) {
                    return ClothModels.HAT;
                } else return ClothModels.CLOTH;
            }
        };
    }
}
