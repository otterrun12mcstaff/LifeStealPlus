package com.lifestealplus.module.impl;

import com.lifestealplus.module.MiningModule;
import com.lifestealplus.util.BlockUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

import java.util.List;

/**
 * Mines the single block the player is looking at.
 */
public class MinerModule extends MiningModule {
	public MinerModule() {
		super("miner");
	}

	@Override
	protected List<BlockPos> findTargets(MinecraftClient client) {
		BlockPos target = BlockUtil.findBlockInFront(client, 4.5D);
		return target == null ? List.of() : List.of(target);
	}
}
