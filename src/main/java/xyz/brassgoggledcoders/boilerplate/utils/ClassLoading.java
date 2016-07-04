package xyz.brassgoggledcoders.boilerplate.utils;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.relauncher.Side;
import xyz.brassgoggledcoders.boilerplate.proxies.CommonProxy;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClassLoading {
	public static CommonProxy createProxy(String clientString, String serverString) {
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		String proxyString = side == Side.CLIENT ? clientString : serverString;
		Object proxyObject = createObjectInstance(proxyString);
		if(proxyObject instanceof CommonProxy) {
			return (CommonProxy) proxyObject;
		}
		return null;
	}

	public static <T> T createInstanceOf(Class<T> tClass, String path) {
		Object object = createObjectInstance(path);
		if(object != null) {
			return tClass.cast(object);
		}
		return null;
	}

	public static Object createObjectInstance(String path) {
		try {
			Class classToGrab;
			classToGrab = Class.forName(path);
			return createObjectInstance(classToGrab);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		Utils.attemptLogErrorToCurrentMod(path + " did not initialize. Something's gonna break.");
		return null;
	}

	public static Object createObjectInstance(Class clazz) {
		try {
			return clazz.newInstance();
		} catch(InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		Utils.attemptLogErrorToCurrentMod(clazz.getName() + " did not initialize. Something's gonna break.");
		return null;
	}

	public static <T> List<T> getInstances(@Nonnull ASMDataTable asmDataTable, Class annotationClass,
			Class<T> instanceClass) {
		String annotationClassName = annotationClass.getCanonicalName();
		Set<ASMDataTable.ASMData> asmDatas = asmDataTable.getAll(annotationClassName);
		List<T> instances = new ArrayList<>();
		for(ASMDataTable.ASMData asmData : asmDatas) {
			try {
				Class<?> asmClass = Class.forName(asmData.getClassName());
				Class<? extends T> asmInstanceClass = asmClass.asSubclass(instanceClass);
				T instance = asmInstanceClass.newInstance();
				instances.add(instance);
			} catch(ClassNotFoundException | IllegalAccessException | InstantiationException e) {
				Utils.attemptLogErrorToCurrentMod("Failed to load: " + asmData.getClassName());
			}
		}
		return instances;
	}
}
