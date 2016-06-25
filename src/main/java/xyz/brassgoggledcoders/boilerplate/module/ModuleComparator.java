package xyz.brassgoggledcoders.boilerplate.module;

import xyz.brassgoggledcoders.boilerplate.Boilerplate;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.IDependency;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.ModuleDependency;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModuleComparator implements Comparator<IModule> {
	@Override
	public int compare(IModule module1, IModule module2) {
		int compareResult = 0;
		List<IDependency> module1Deps = new ArrayList<>();
		List<IDependency> module2Deps = new ArrayList<>();

		if(module1 != null) {
			module1Deps = module1.getDependencies(module1Deps);
		} else {
			compareResult = 1;
		}

		if(module2 != null) {
			module2Deps = module2.getDependencies(module2Deps);
		} else {
			compareResult = -1;
		}

		if(compareResult == 0) {
			boolean module1IsDepTo2 = false;
			boolean module2IsDepTo1 = false;

			for(IDependency dependency: module1Deps) {
				if(dependency instanceof ModuleDependency) {
					module1IsDepTo2 |= ((ModuleDependency) dependency).moduleName.equals(module2.getName());
				}
			}

			for(IDependency dependency: module2Deps) {
				if(dependency instanceof ModuleDependency) {
					module2IsDepTo1 |= ((ModuleDependency) dependency).moduleName.equals(module1.getName());
				}
			}

			if(module1IsDepTo2 && module2IsDepTo1) {
				Boilerplate.instance.getLogger().fatal("CIRCULAR DEPENDENCIES FOUND. THINKS MAY GO WRONG");
			} else if(module1IsDepTo2) {
				compareResult = 1;
			} else if(module2IsDepTo1) {
				compareResult = -1;
			}
		}

		return compareResult;
	}
}
