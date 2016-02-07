package boilerplate.common;

public class ModInfo implements IModInfo
{
	public final static String ID = "boilerplate";
	public final static String NAME = "Boilerplate Library";
	public final static String VERSION = "@VERSION@";

	@Override
	public String getID()
	{
		return ID;
	}

	@Override
	public String getName()
	{
		return NAME;
	}

	@Override
	public String getVersion()
	{
		return VERSION;
	}

	@Override
	public String getPrefix()
	{
		return ID + ":";
	}

	@Override
	public String getClientProxyPath()
	{
		return "boilerplate.client.ClientProxy";
	}

	@Override
	public String getCommonProxyPath()
	{
		return "boilerplate.common.CommonProxy";
	}

}
