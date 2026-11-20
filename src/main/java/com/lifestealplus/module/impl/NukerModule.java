package com.lifestealplus.module.impl;

import com.lifestealplus.module.MiningModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

/**
 * Clears the blocks immediately around the player.
 */
public class NukerModule extends MiningModule {
	private static final int RADIUS = 2;
	private static final int MAX_BLOCKS = 32;

	public NukerModule() {
		super("nuker");
	}

	@Override
	protected List<BlockPos> findTargets(MinecraftClient client) {
		BlockPos center = client.player.getBlockPos();
		List<BlockPos> targets = new ArrayList<>();

		for (BlockPos pos : BlockPos.iterate(
				center.add(-RADIUS, -1, -RADIUS),
				center.add(RADIUS, 2, RADIUS))) {
			if (!client.world.getBlockState(pos).isAir()) {
				targets.add(pos.toImmutable());
			}

			if (targets.size() >= MAX_BLOCKS) {
				break;
			}
		}

		return targets;
	}
}
