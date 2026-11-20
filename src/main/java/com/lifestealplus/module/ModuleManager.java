package com.lifestealplus.module;

import com.lifestealplus.module.impl.AutoFarmModule;
import com.lifestealplus.module.impl.HeightTunnelModule;
import com.lifestealplus.module.impl.MinerModule;
import com.lifestealplus.module.impl.NukerModule;
import com.lifestealplus.module.impl.ShaftModule;
import com.lifestealplus.module.impl.StaircaseModule;
import com.lifestealplus.module.impl.TorchPlacerModule;
import com.lifestealplus.module.impl.TreeFellerModule;
import com.lifestealplus.module.impl.TunnelModule;
import com.lifestealplus.module.impl.VeinMinerModule;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public final class ModuleManager {
	private final List<Module> modules = List.of(
			new MinerModule(),
			new TunnelModule(),
			new HeightTunnelModule(),
			new ShaftModule(),
			new StaircaseModule(),
			new VeinMinerModule(),
			new TreeFellerModule(),
			new AutoFarmModule(),
			new NukerModule(),
			new TorchPlacerModule()
	);

	public List<Module> getModules() {
		return modules;
	}

	public void tick(MinecraftClient client) {
		if (client.player == null || client.world == null) {
			return;
		}

		for (Module module : modules) {
			if (module.isEnabled()) {
				module.tick(client);
			}
		}
	}
}
