package com.lifestealplus.module.impl;

import com.lifestealplus.module.MiningModule;
import com.lifestealplus.util.BlockUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

/**
 * Digs a descending staircase: the block below the player and the block in front.
 */
public class StaircaseModule extends MiningModule {
	public StaircaseModule() {
		super("staircase");
	}

	@Override
	protected List<BlockPos> findTargets(MinecraftClient client) {
		List<BlockPos> targets = new ArrayList<>();
		BlockPos below = BlockUtil.blockBelow(client);
		BlockPos front = BlockUtil.findBlockInFront(client, 3.0D);

		if (below != null) {
			targets.add(below);
		}

		if (front != null && !targets.contains(front)) {
			targets.add(front);
		}

		return targets;
	}
}
