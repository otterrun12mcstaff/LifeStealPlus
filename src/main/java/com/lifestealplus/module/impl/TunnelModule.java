package com.lifestealplus.module.impl;

import com.lifestealplus.module.MiningModule;
import com.lifestealplus.util.BlockUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Mines a 3x3 face of blocks in front of the player.
 */
public class TunnelModule extends MiningModule {
	public TunnelModule() {
		super("tunnel");
	}

	@Override
	protected List<BlockPos> findTargets(MinecraftClient client) {
		BlockPos center = BlockUtil.findBlockInFront(client, 4.5D);

		if (center == null) {
			return List.of();
		}

		Direction facing = client.player.getHorizontalFacing();
		List<BlockPos> targets = new ArrayList<>();

		for (int side = -1; side <= 1; side++) {
			for (int up = -1; up <= 1; up++) {
				BlockPos pos = facing.getAxis() == Direction.Axis.Z
						? center.add(side, up, 0)
						: center.add(0, up, side);

				if (!BlockUtil.isAir(client, pos)) {
					targets.add(pos);
				}
			}
		}

		return targets;
	}
}
