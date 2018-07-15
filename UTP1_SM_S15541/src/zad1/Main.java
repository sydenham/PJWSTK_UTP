/**
 *
 *  @author Szarek Marcin S15541
 *
 */

package zad1;



import java.util.*;

public class Main {
  public Main() {
    List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);
    System.out.println(test1(src1));

    List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv");
    System.out.println(test2(src2));
  }

  public List<Integer> test1(List<Integer> src) {
    Selector <Integer> sel = new Selector<Integer>() {
		public boolean select(Integer x) {
			if (x < 10) {
				return true;
			} else {
				return false;
			}
		}
	};

    Mapper <Integer, Integer> map = new Mapper<Integer, Integer>() {
		public Integer map(Integer x) {
			return x + 10;
		}
	};

    return ListCreator.<Integer, Integer> collectFrom(src).when(sel).mapEvery(map);
  }

  public List<Integer> test2(List<String> src) {
    Selector <String> sel = new Selector<String>() {
		public boolean select(String x) {
			if (x.toString().length() > 3) {
				return true;
			} else {
				return false;
			}
		}
	};

    Mapper <String, Integer> map = new Mapper<String, Integer>() {
		public Integer map(String x) {
			return x.toString().length() + 10;
		}
	};

    return ListCreator.<String, Integer> collectFrom(src).when(sel).mapEvery(map);
  }

  public static void main(String[] args) {
    new Main();
  }
}
