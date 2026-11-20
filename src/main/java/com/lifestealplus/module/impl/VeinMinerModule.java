package com.lifestealplus.module.impl;

import com.lifestealplus.module.MiningModule;
import com.lifestealplus.util.BlockUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

import java.util.List;

/**
 * Mines every connected block of the same type as the one in front (ores, mostly).
 */
public class VeinMinerModule extends MiningModule {
	private static final int MAX_BLOCKS = 64;

	public VeinMinerModule() {
		super("vein_miner");
	}

	@Override
	protected List<BlockPos> findTargets(MinecraftClient client) {
		BlockPos target = BlockUtil.findBlockInFront(client, 4.5D);

		if (target == null) {
			return List.of();
		}

		return BlockUtil.connectedBlocks(client, target, MAX_BLOCKS);
	}
}
