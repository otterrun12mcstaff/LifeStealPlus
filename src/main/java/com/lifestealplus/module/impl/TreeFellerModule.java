package com.lifestealplus.module.impl;

import com.lifestealplus.module.MiningModule;
import com.lifestealplus.util.BlockUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;

import java.util.List;

/**
 * Fells a whole tree when the player looks at a log.
 */
public class TreeFellerModule extends MiningModule {
	private static final int MAX_BLOCKS = 128;

	public TreeFellerModule() {
		super("tree_feller");
	}

	@Override
	protected List<BlockPos> findTargets(MinecraftClient client) {
		BlockPos target = BlockUtil.findBlockInFront(client, 4.5D);

		if (target == null || !client.world.getBlockState(target).isIn(BlockTags.LOGS)) {
			return List.of();
		}

		return BlockUtil.connectedBlocks(client, target, MAX_BLOCKS);
	}
}
