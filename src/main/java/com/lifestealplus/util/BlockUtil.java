package com.lifestealplus.util;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class BlockUtil {
	private BlockUtil() {
	}

	/**
	 * Walks along the player's view ray and returns the first block that is not air.
	 */
	public static BlockPos findBlockInFront(MinecraftClient client, double maxDistance) {
		Vec3d eye = client.player.getEyePos();
		Vec3d direction = client.player.getRotationVec(1.0F);

		for (double distance = 0.5D; distance <= maxDistance; distance += 0.25D) {
			BlockPos pos = BlockPos.ofFloored(eye.add(direction.multiply(distance)));

			if (!client.world.getBlockState(pos).isAir()) {
				return pos;
			}
		}

		return null;
	}

	/**
	 * Returns the first block below the player's feet.
	 */
	public static BlockPos blockBelow(MinecraftClient client) {
		BlockPos feet = BlockPos.ofFloored(client.player.getX(), client.player.getY() - 0.1D, client.player.getZ());

		for (int offset = 0; offset < 4; offset++) {
			BlockPos pos = feet.down(offset);

			if (!client.world.getBlockState(pos).isAir()) {
				return pos;
			}
		}

		return null;
	}

	/**
	 * Collects blocks of the same type that are connected to {@code start}.
	 */
	public static List<BlockPos> connectedBlocks(MinecraftClient client, BlockPos start, int limit) {
		Block block = client.world.getBlockState(start).getBlock();
		List<BlockPos> found = new ArrayList<>();
		Set<BlockPos> seen = new HashSet<>();
		Deque<BlockPos> open = new ArrayDeque<>();

		open.add(start);
		seen.add(start);

		while (!open.isEmpty() && found.size() < limit) {
			BlockPos pos = open.poll();
			found.add(pos);

			for (Direction direction : Direction.values()) {
				BlockPos neighbour = pos.offset(direction);

				if (seen.add(neighbour) && client.world.getBlockState(neighbour).getBlock() == block) {
					open.add(neighbour);
				}
			}
		}

		return found;
	}

	public static boolean isAir(MinecraftClient client, BlockPos pos) {
		return client.world.getBlockState(pos).isAir();
	}
}
