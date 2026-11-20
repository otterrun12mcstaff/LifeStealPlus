package com.lifestealplus.module.impl;

import com.lifestealplus.module.Module;
import com.lifestealplus.util.BlockUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;

/**
 * Places a torch on the ground under the player while it is dark.
 */
public class TorchPlacerModule extends Module {
	private static final int MIN_LIGHT = 8;
	private static final int COOLDOWN_TICKS = 10;

	private int cooldown;

	public TorchPlacerModule() {
		super("torch_placer");
	}

	@Override
	protected void onEnable() {
		cooldown = 0;
	}

	@Override
	public void tick(MinecraftClient client) {
		if (client.player == null || client.world == null || client.interactionManager == null) {
			return;
		}

		if (cooldown > 0) {
			cooldown--;
			return;
		}

		BlockPos ground = BlockUtil.blockBelow(client);

		if (ground == null) {
			return;
		}

		BlockPos torchPos = ground.up();

		if (!client.world.getBlockState(torchPos).isAir()) {
			return;
		}

		if (client.world.getLightLevel(LightType.BLOCK, torchPos) >= MIN_LIGHT) {
			return;
		}

		int slot = findTorchSlot(client);

		if (slot < 0) {
			return;
		}

		client.player.getInventory().setSelectedSlot(slot);
		Vec3d hitPos = Vec3d.ofCenter(ground).add(0.0D, 0.5D, 0.0D);
		BlockHitResult hit = new BlockHitResult(hitPos, Direction.UP, ground, false);
		client.interactionManager.interactBlock(client.player, Hand.MAIN_HAND, hit);
		client.player.swingHand(Hand.MAIN_HAND);
		cooldown = COOLDOWN_TICKS;
	}

	private int findTorchSlot(MinecraftClient client) {
		for (int slot = 0; slot < 9; slot++) {
			ItemStack stack = client.player.getInventory().getStack(slot);

			if (stack.isOf(Items.TORCH) || stack.isOf(Items.SOUL_TORCH)) {
				return slot;
			}
		}

		return -1;
	}
}
