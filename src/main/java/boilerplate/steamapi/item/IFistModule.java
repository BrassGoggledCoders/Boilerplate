package boilerplate.steamapi.item;

public interface IFistModule extends IModule
{
	// The type of effect (see below)
	public EnumModuleEffectType getModuleEffectType();

	//
	public static enum EnumModuleEffectType
	{
		RIGHTCLICK, ATTACK
	}
}
