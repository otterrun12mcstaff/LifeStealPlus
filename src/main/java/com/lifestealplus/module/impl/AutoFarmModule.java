package com.lifestealplus.module.impl;

import com.lifestealplus.module.MiningModule;
import net.minecraft.block.CropBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

/**
 * Harvests mature crops around the player.
 */
public class AutoFarmModule extends MiningModule {
	private static final int RADIUS = 4;
	private static final int MAX_BLOCKS = 24;

	public AutoFarmModule() {
		super("auto_farm");
	}

	@Override
	protected List<BlockPos> findTargets(MinecraftClient client) {
		BlockPos center = client.player.getBlockPos();
		List<BlockPos> targets = new ArrayList<>();

		for (BlockPos pos : BlockPos.iterate(
				center.add(-RADIUS, -2, -RADIUS),
				center.add(RADIUS, 2, RADIUS))) {
			BlockState state = client.world.getBlockState(pos);

			if (state.getBlock() instanceof CropBlock crop && crop.isMature(state)) {
				targets.add(pos.toImmutable());
			}

			if (targets.size() >= MAX_BLOCKS) {
				break;
			}
		}

		return targets;
	}
}
