package com.lifestealplus.module.impl;

import com.lifestealplus.module.MiningModule;
import com.lifestealplus.util.BlockUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

/**
 * Mines a player-height tunnel: the block in front plus the one above it.
 */
public class HeightTunnelModule extends MiningModule {
	public HeightTunnelModule() {
		super("height_tunnel");
	}

	@Override
	protected List<BlockPos> findTargets(MinecraftClient client) {
		BlockPos center = BlockUtil.findBlockInFront(client, 4.5D);

		if (center == null) {
			return List.of();
		}

		List<BlockPos> targets = new ArrayList<>();
		targets.add(center);

		BlockPos above = center.up();

		if (!BlockUtil.isAir(client, above)) {
			targets.add(above);
		}

		return targets;
	}
}
