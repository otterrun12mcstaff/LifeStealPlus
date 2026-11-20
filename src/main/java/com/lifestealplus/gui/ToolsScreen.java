package com.lifestealplus.gui;

import com.lifestealplus.module.Module;
import com.lifestealplus.module.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.List;

/**
 * Grid of toggle buttons, one per module. Opened with the tools key.
 */
public class ToolsScreen extends Screen {
	private static final int COLUMNS = 2;
	private static final int BUTTON_WIDTH = 150;
	private static final int BUTTON_HEIGHT = 20;
	private static final int GAP = 6;
	private static final int TITLE_TOP = 30;

	private final ModuleManager manager;

	public ToolsScreen(ModuleManager manager) {
		super(Text.translatable("lifestealplus.screen.title"));
		this.manager = manager;
	}

	@Override
	protected void init() {
		List<Module> modules = manager.getModules();
		int rows = (modules.size() + COLUMNS - 1) / COLUMNS;
		int gridWidth = COLUMNS * BUTTON_WIDTH + (COLUMNS - 1) * GAP;
		int gridHeight = rows * BUTTON_HEIGHT + (rows - 1) * GAP;
		int startX = (this.width - gridWidth) / 2;
		int startY = (this.height - gridHeight) / 2 + 10;

		for (int index = 0; index < modules.size(); index++) {
			Module module = modules.get(index);
			int x = startX + (index % COLUMNS) * (BUTTON_WIDTH + GAP);
			int y = startY + (index / COLUMNS) * (BUTTON_HEIGHT + GAP);

			ButtonWidget button = ButtonWidget.builder(labelFor(module), pressed -> {
				module.toggle();
				pressed.setMessage(labelFor(module));
			}).dimensions(x, y, BUTTON_WIDTH, BUTTON_HEIGHT).build();

			addDrawableChild(button);
		}
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
		super.render(context, mouseX, mouseY, deltaTicks);
		context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, TITLE_TOP, 0xFFFFFF);
	}

	private static Text labelFor(Module module) {
		Text state = Text.translatable(module.isEnabled() ? "lifestealplus.state.on" : "lifestealplus.state.off");
		return Text.translatable(module.getNameKey()).append(Text.literal(": ")).append(state);
	}
}
