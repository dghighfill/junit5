package com.jug.junit5.sm.ex5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContainerFactory {

	private List<IContainer> containers = new ArrayList<IContainer>();

	public void setContainers(List<IContainer> containers) {
		this.containers = containers;
	}

	public IContainer getContainer(List<Ingredient> ingredients) throws ContainerNotFoundException {

		if (!ingredients.isEmpty() && ingredients.size() <= 3) {
			return getDinkyContainer();
		} else if (ingredients.size() > 3 && ingredients.size() <= 6) {
			return new Standard();
		} else if (ingredients.size() > 6 && ingredients.size() < 12) {
			return new Gargantuan();
		}

		throw new ContainerNotFoundException("Unable to create Smoothie Ninja Container");
	}

	public int howManyContainersAvailable() {
		return containers.size();
	}

	public int areDinkyContainersAvaiable() {
		int count = 0;
		for (IContainer iContainer : containers) {
			if (iContainer instanceof Dinky) {
				count++;
			}
		}
		return count;
	}

	public int areStandardContainersAvaiable() {
		int count = 0;
		for (IContainer iContainer : containers) {
			if (iContainer instanceof Standard) {
				count++;
			}
		}
		return count;
	}

	public int areGargantuanContainersAvaiable() {
		int count = 0;
		for (IContainer iContainer : containers) {
			if (iContainer instanceof Gargantuan) {
				count++;
			}
		}
		return count;
	}

	private IContainer getDinkyContainer() throws ContainerNotFoundException {
		IContainer dinkyContainer = null;
		for( Iterator<IContainer> iter = containers.iterator(); iter.hasNext(); ) {
			IContainer container = iter.next();
			if (container instanceof Dinky) {
				dinkyContainer = container;
				iter.remove();
				break;
			}
		}
		if (null == dinkyContainer) {
			throw new ContainerNotFoundException("Unable to create Dinky Smoothie Ninja Container",
					new Throwable("All containers are dirty"));
		}
		return dinkyContainer;

	}

}
