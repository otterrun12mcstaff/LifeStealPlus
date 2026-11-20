package com.lifestealplus.module;

import com.lifestealplus.util.BlockBreaker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

import java.util.List;

/**
 * Base class for modules that break a list of blocks, one at a time.
 */
public abstract class MiningModule extends Module {
	private final BlockBreaker breaker = new BlockBreaker();

	protected MiningModule(String id) {
		super(id);
	}

	@Override
	protected void onEnable() {
		breaker.clear();
	}

	@Override
	protected void onDisable() {
		breaker.clear();
	}

	@Override
	public void tick(MinecraftClient client) {
		if (client.player == null || client.world == null || client.interactionManager == null) {
			return;
		}

		if (!breaker.isBusy()) {
			List<BlockPos> targets = findTargets(client);

			if (!targets.isEmpty()) {
				breaker.queue(targets);
			}
		}

		breaker.tick(client);
	}

	protected abstract List<BlockPos> findTargets(MinecraftClient client);
}
