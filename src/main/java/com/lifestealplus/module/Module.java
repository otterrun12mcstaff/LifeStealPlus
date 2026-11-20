package com.lifestealplus.module;

import net.minecraft.client.MinecraftClient;

/**
 * A toggleable feature shown as one button in the tools screen.
 */
public abstract class Module {
	private final String id;
	private final String nameKey;
	private boolean enabled;

	protected Module(String id) {
		this.id = id;
		this.nameKey = "lifestealplus.module." + id;
	}

	public String getId() {
		return id;
	}

	public String getNameKey() {
		return nameKey;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		if (this.enabled == enabled) {
			return;
		}

		this.enabled = enabled;

		if (enabled) {
			onEnable();
		} else {
			onDisable();
		}
	}

	public void toggle() {
		setEnabled(!enabled);
	}

	protected void onEnable() {
	}

	protected void onDisable() {
	}

	public void tick(MinecraftClient client) {
	}
}
