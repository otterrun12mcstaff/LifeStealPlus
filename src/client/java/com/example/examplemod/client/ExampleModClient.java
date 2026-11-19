package com.example.examplemod.client;

import net.fabricmc.api.ClientModInitializer;

import com.example.examplemod.ExampleMod;

public class ExampleModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ExampleMod.LOGGER.info("Hello from the client side!");
	}
}
