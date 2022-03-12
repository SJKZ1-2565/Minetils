package com.sjkz1.minetils.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.sjkz1.minetils.Minetils;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Wearable;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

@Mixin(ElytraItem.class)
public abstract class ElytraItemMixin extends Item
implements Wearable{
	@Override
	@Shadow @Nullable public abstract SoundEvent getEquipSound();

	public ElytraItemMixin(Settings settings) {
		super(settings);
	}

	/**
	 * @author
	 */
	@Override
	@Overwrite
	public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		ItemStack itemStack = playerEntity.getStackInHand(hand);
		EquipmentSlot equipmentSlot = LivingEntity.getPreferredEquipmentSlot(itemStack);
		ItemStack itemStack2 = playerEntity.getEquippedStack(equipmentSlot);
		if ((itemStack2.isEmpty() || (itemStack2.getItem() instanceof ArmorItem || itemStack2.getItem() instanceof ElytraItem) && Minetils.CONFIG.getConfig().SwapArmorAndElytra)) {
			playerEntity.equipStack(equipmentSlot, itemStack.copy());
			playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
			playerEntity.setStackInHand(hand,itemStack2);
			playerEntity.playSound(this.getEquipSound(),1.0f,1.0f);
			return TypedActionResult.success(itemStack, world.isClient());
		} else {
			return TypedActionResult.fail(itemStack);
		}
	}
}
