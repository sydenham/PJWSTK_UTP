package zad1;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;

public class XList<T> extends ArrayList{
	
	
	public XList(Collection<T> coll) {
		for (T elem : coll) {
			this.add(elem);
		}
	}
	public XList(T... coll) {
		for (T elem : coll) {
			this.add(elem);
		}
	}
	
	public interface Consumer<Integer> {
		void accept(Integer e, int i);
	}
	
	public static <T>XList<T> of(Collection<T> coll){
		XList<T> list = new XList<>(coll);
		return list;
	}
	public static <T>XList<T> of(T... coll){
		XList<T> list = new XList<>(coll);
		return list;
	}
	public static XList<String> charsOf(String s){
		XList<String> list = new XList<String>(Arrays.asList(s.split("")));
		return list;
	}
	public static XList<String> tokensOf(String s){
		XList<String> list = new XList<String>(Arrays.asList(s.split("\\s+")));
		return list;
	}
	public static XList<String> tokensOf(String s, String sep){
		XList<String> list = new XList<String>(Arrays.asList(s.split(sep)));
		return list;
	}
	public XList<T> union(Collection<T> coll){
		XList<T> list = new XList<>(this);
		list.addAll(coll);
		return list;
	}
	public XList<T> union(T... coll){
		XList<T> list = new XList<>(this);
		for (T elem : coll) {
			list.add(elem);
		}
		return list;
	}
	public XList<T> diff(Collection<T> coll){
		XList<T> list = new XList<>(this);
		list.removeAll(coll);
		return list;
	}
	public XList<T> unique() {
		XList<T> current = new XList<>(this);
		XList<T> list = new XList<>();
		for (Object elem : current) {
			if (!list.contains(elem)) {
				list.add(elem);
			}
		}
		return list;
	}
	public XList<T> unique2() {
		XList<T> list = new XList<>(new LinkedHashSet<>(this));
		return list;
	}
	public XList<String> collect(Function<XList<String>, String> mapping) {
		XList<String> current = new XList<>(this);
		XList<String> result = new XList<>();
		for (Object x : current) {
			result.add(mapping.apply( (XList<String>) x));
		}
		return result;
	}
	public String join(){
		StringBuilder sb = new StringBuilder();
		this.stream().map(x ->sb.append(x)).collect(Collectors.toList());
		return sb.toString();
	}
	public String join(String sep){
		StringBuilder sb = new StringBuilder();
		this.stream().map( x -> sb.append(x).append(sep)).collect(Collectors.toList());
		return sb.deleteCharAt(sb.length()-1).toString();
	}
	
	
	public XList<XList<String>> combine() {
		XList<XList<String>> result = new XList<>();
		Iterator iter = this.iterator();
		while (iter.hasNext()) {
			Iterator inIter = ((Collection<String>) iter.next()).iterator();
			if (result.isEmpty()) {
				while (inIter.hasNext()) {
					result.add(new XList(inIter.next()));
				}
			} else {
				XList<XList<String>> tempresult = new XList<>();
				while (inIter.hasNext()) {
					String newItem = (String) inIter.next();
					for (Object previousResultItem: result) {
						XList newItemList = new XList();
						newItemList.addAll((XList) previousResultItem);
						newItemList.add(newItem);
						tempresult.add(newItemList);
					}
				}
				result = tempresult;
			}
		}
		return result;
	}
	/*
	public XList<XList<String>> combine() {
		XList<XList<String>> result = new XList<>();
		Iterator iter = this.iterator();
		while (iter.hasNext()) {
			Iterator inIter = ((Collection<String>) iter.next()).iterator();
			if (result.isEmpty()) {
				while (inIter.hasNext()) {
					result.add(inIter.next());
				}
			} else {
				XList<String> temp = new XList<>();
				temp.addAll(result);
				result.clear();
				while (inIter.hasNext()) {
					String nextString = new String();
					nextString = (String) inIter.next();
					for(Object currentLine : temp) {
						XList<String> line = new XList<>();
						line.add(currentLine);
						line.add(nextString);
						result.add(line);
					}
				}
			}
		}
		return result;
	}
	*/
	/*
	public void forEachWithIndex(BiFunction<Integer, Integer, Object> mapping){
		AtomicInteger index = new AtomicInteger();
		this.stream().forEach((x) -> {mapping.apply( (Integer) x, index.getAndIncrement());});		
	}
	*/

	void forEachWithIndex(Consumer<Integer> map) {
		int i = 0;
		while( i < this.size()) {
			map.accept((Integer) this.get(i), i); 
			i++;
		}
	}
	
	/*public void forEachWithIndex2(BiConsumer<Integer, Integer> mapping){
		AtomicInteger index = new AtomicInteger();
		new XList(this).stream().forEach(x -> {mapping.accept( (Integer) x, index.getAndIncrement());});		
	}*/
	
	/*public void forEachWithIndex(BiConsumer<Integer, Integer> mapping){
		XList<Integer> aaa = (XList<Integer>) IntStream.range(0, this.size()).mapToObj(x ->  mapping.accept( (Integer) this.get(x), x)).collect(Collectors.toList());
		this.clear();
		this.addAll(aaa);
	}*/
}

