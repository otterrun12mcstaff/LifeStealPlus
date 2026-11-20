package com.lifestealplus.module.impl;

import com.lifestealplus.module.MiningModule;
import com.lifestealplus.util.BlockUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

import java.util.List;

/**
 * Digs straight down below the player.
 */
public class ShaftModule extends MiningModule {
	public ShaftModule() {
		super("shaft");
	}

	@Override
	protected List<BlockPos> findTargets(MinecraftClient client) {
		BlockPos below = BlockUtil.blockBelow(client);
		return below == null ? List.of() : List.of(below);
	}
}
