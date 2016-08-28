package xyz.brassgoggledcoders.boilerplate.client.guis.components;

public interface IComponent {
	int getXPos();

	int getYPos();

	int getScale();

	float getXOffset();

	float getYOffset();

	void render(float mouseX, float mouseY);
}
