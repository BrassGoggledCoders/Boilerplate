package boilerplate.steamapi.item;

import java.util.ArrayList;
import java.util.HashMap;

import scala.actors.threadpool.Arrays;

public class ModuleRegistry
{
	private static HashMap<String, IArmorModule> modules = new HashMap<String, IArmorModule>();
	private static HashMap<String, ArrayList> moduleIncompatibilities = new HashMap<String, ArrayList>();

	public static void registerModule(IArmorModule module)
	{
		modules.put(module.getModuleId(), module);
	}

	public static IArmorModule getModule(String id)
	{
		return modules.get(id);
	}

	public static void setModuleIncompatibilities(IArmorModule module, String[] incompatibilities)
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
