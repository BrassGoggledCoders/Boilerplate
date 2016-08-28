package xyz.brassgoggledcoders.boilerplate.client.guis.components;

public abstract class ComponentBase implements IComponent {
	private int xPos;
	private int yPos;
	private int scale;
	private float xOffset;
	private float yOffset;

	public ComponentBase(int xPos, int yPos, float xOffset, float yOffset) {
		this(xPos, yPos, 1, xOffset, yOffset);
	}

	public ComponentBase(int xPos, int yPos, int scale, float xOffset, float yOffset) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.scale = scale;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	@Override
	public int getXPos() {
		return xPos;
	}

	@Override
	public int getYPos() {
		return yPos;
	}

	@Override
	public int getScale() {
		return scale;
	}

	@Override
	public float getXOffset() {
		return xOffset;
	}

	@Override
	public float getYOffset() {
		return yOffset;
	}

	@Override
	public abstract void render(float mouseX, float mouseY);
}
