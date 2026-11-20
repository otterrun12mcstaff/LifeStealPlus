package com.lifestealplus;

import com.lifestealplus.gui.ToolsScreen;
import com.lifestealplus.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class LifeStealPlusClient implements ClientModInitializer {
	public static final String MOD_ID = "lifestealplus";

	private static final ModuleManager MODULES = new ModuleManager();

	private KeyBinding openMenuKey;

	@Override
	public void onInitializeClient() {
		openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.lifestealplus.open_menu",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_MINUS,
				KeyBinding.Category.MISC
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openMenuKey.wasPressed()) {
				if (client.currentScreen == null && client.player != null) {
					client.setScreen(new ToolsScreen(MODULES));
				}
			}
			MODULES.tick(client);
		});
	}

	public static ModuleManager modules() {
		return MODULES;
	}
}
