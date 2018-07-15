package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T, S> {
	
	List<T> list = new ArrayList<>();

	public static <T, S> ListCreator collectFrom(List<T> coll) {
		ListCreator<T, S> lc = new ListCreator<>();
		lc.list.addAll(coll);
		return lc;
	}

	ListCreator when(Predicate<T> filter) {
		ListCreator lc = new ListCreator();
		for (T x : this.list) {
			boolean check = filter.test(x);
			if (check == true) {
				lc.list.add(x);
			}
		}
		return lc;
	}

	ArrayList<S> mapEvery(Function<T, S> mapping) {
		ArrayList<S> result = new ArrayList<>();
		for (T x : this.list) {
			result.add(mapping.apply(x));
		}
		return result;
	}

}
