package xyz.brassgoggledcoders.boilerplate.manual;

public class KnowledgeType {

	public final String id;
	// TODO Figure out what to use in place of EnumChatFormatting
	// public final EnumChatFormatting color;
	public final boolean autoUnlock;

	public KnowledgeType(String id/* , EnumChatFormatting color */, boolean autoUnlock) {
		this.id = id;
		// this.color = color;
		this.autoUnlock = autoUnlock;
	}

	public String getUnlocalizedName() {
		return "botania.knowledge." + id;
	}
}
