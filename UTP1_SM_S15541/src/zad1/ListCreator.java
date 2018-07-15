/**
 *
 *  @author Szarek Marcin S15541
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.List;

public class ListCreator<T, S> { // Uwaga: klasa musi być sparametrtyzowana

	List<T> list = new ArrayList<>();

	public static <T, S> ListCreator collectFrom(List<T> coll) {
		ListCreator<T, S> lc = new ListCreator<>();
		lc.list.addAll(coll);
		return lc;
	}

	ListCreator when(Selector<T> filter) {
		ListCreator lc = new ListCreator();
		for (T x : this.list) {
			boolean check = filter.select(x);
			if (check == true) {
				lc.list.add(x);
			}
		}
		return lc;
	}

	ArrayList<S> mapEvery(Mapper<T, S> mapping) {
		ArrayList<S> result = new ArrayList<>();
		for (T x : this.list) {
			result.add(mapping.map(x));
		}
		return result;
	}

}
