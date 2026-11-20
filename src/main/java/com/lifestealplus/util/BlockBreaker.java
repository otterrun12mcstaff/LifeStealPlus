package com.lifestealplus.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

/**
 * Breaks queued blocks one at a time, keeping the mining progress on a single block until it is gone.
 */
public final class BlockBreaker {
	private final Deque<BlockPos> queue = new ArrayDeque<>();
	private BlockPos current;

	public void clear() {
		queue.clear();
		current = null;
	}

	public boolean isBusy() {
		return current != null || !queue.isEmpty();
	}

	public void queue(Collection<BlockPos> positions) {
		for (BlockPos pos : positions) {
			if (!queue.contains(pos) && !pos.equals(current)) {
				queue.add(pos);
			}
		}
	}

	public void tick(MinecraftClient client) {
		if (client.player == null || client.world == null || client.interactionManager == null) {
			clear();
			return;
		}

		if (current != null && client.world.getBlockState(current).isAir()) {
			current = null;
		}

		if (current == null) {
			current = queue.poll();

			if (current == null) {
				return;
			}
		}

		Vec3d eye = client.player.getEyePos();
		Vec3d center = Vec3d.ofCenter(current);
		Direction side = Direction.getFacing(eye.x - center.x, eye.y - center.y, eye.z - center.z);

		client.interactionManager.updateBlockBreakingProgress(current, side);
		client.player.swingHand(Hand.MAIN_HAND);
	}
}
