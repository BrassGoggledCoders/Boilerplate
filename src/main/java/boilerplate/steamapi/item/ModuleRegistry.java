package boilerplate.steamapi.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ModuleRegistry
{
	private static HashMap<String, IModule> modules = new HashMap<String, IModule>();
	private static HashMap<String, ArrayList> moduleIncompatibilities = new HashMap<String, ArrayList>();

	public static void registerModule(IModule module)
	{
		modules.put(module.getModuleId(), module);
	}

	public static IModule getModule(String id)
	{
		return modules.get(id);
	}

	public static void setModuleIncompatibilities(IModule module, String[] incompatibilities)
	{
		ArrayList list = new ArrayList(Arrays.asList(incompatibilities));
		System.out.print("RegistryArray:" + list);
		moduleIncompatibilities.put(module.getModuleId(), list);
	}

	public static ArrayList getModuleIncompatibilities(String id)
	{
		return moduleIncompatibilities.get(id);
	}
}
