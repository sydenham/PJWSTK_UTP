package zad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Consumer;

public class ProgLang<K, V> extends LinkedHashMap<String, LinkedHashSet<String>> {

	public ProgLang(String path) throws IOException {
		getData(path);
	}

	public ProgLang() {
	}

	public void getData(String path) throws IOException {
		StringTokenizer st;
		BufferedReader TSVFile = new BufferedReader(new FileReader(path));
		String dataRow = TSVFile.readLine();

		while (dataRow != null) {
			st = new StringTokenizer(dataRow, "\t");
			LinkedHashSet<String> dataArray = new LinkedHashSet<String>();
			String key = st.nextElement().toString();
			this.put(key, dataArray);
			while (st.hasMoreElements()) {
				dataArray.add(st.nextElement().toString());
			}
			dataRow = TSVFile.readLine();
		}
		TSVFile.close();
	}

	public static <K, V> Map<K, V> sorted(Map<K, V> map, Comparator<Map.Entry<K, V>> comp) {
		List<Map.Entry<K, V>> entries = new ArrayList<>(map.entrySet());
		entries.sort(comp);
		Map<K, V> resMap = new LinkedHashMap<>();
		entries.forEach(e -> resMap.put(e.getKey(), e.getValue()));
		return resMap;
	}

	public static <K, V> Map<K, V> filtered(Map<K, V> map, Consumer<Map.Entry<K, V>> consum) {
		Map<K, V> result = map;
		new LinkedHashMap<>(map).entrySet().stream().forEach(x -> consum.accept(x));
		return result;
	}

	public ProgLang<String, LinkedHashSet<String>> getLangsMap() {
		ProgLang<String, LinkedHashSet<String>> map = new ProgLang<>();
		map.putAll(this);
		return map;
	}

	public ProgLang<String, LinkedHashSet<String>> getProgsMap() {
		ProgLang<String, LinkedHashSet<String>> map = new ProgLang<>();
		for (Map.Entry<String, LinkedHashSet<String>> entry : this.entrySet()) {
			Iterator<String> programmists = entry.getValue().iterator();
			while (programmists.hasNext()) {
				String programmist = new String(programmists.next());
				if (!map.containsKey(programmist)) {
					map.put(programmist, new LinkedHashSet<>());
					map.get(programmist).add(entry.getKey());
				} else {
					map.get(programmist).add(entry.getKey());
				}
			}
		}
		return map;
	}

	public ProgLang<String, LinkedHashSet<String>> getLangsMapSortedByNumOfProgs() {
		Map<String, LinkedHashSet<String>> map = sorted(this, (e1, e2) -> {
			int i = (int) e2.getValue().size() - e1.getValue().size();
			if (i != 0) {
				return i;
			} else {
				return e1.getKey().compareToIgnoreCase(e2.getKey());
			}
		});
		ProgLang<String, LinkedHashSet<String>> res = new ProgLang<>();
		res.putAll(map);
		return res;
	}

	public ProgLang<String, LinkedHashSet<String>> getProgsMapSortedByNumOfLangs() {
		ProgLang<String, LinkedHashSet<String>> map = this.getProgsMap().getLangsMapSortedByNumOfProgs();
		return map;
	}

	public ProgLang<String, LinkedHashSet<String>> getProgsMapForNumOfLangsGreaterThan(int noOfLang) {
		ProgLang<String, LinkedHashSet<String>> map = this.getProgsMap();
		filtered(map, (e1) -> {
			if (e1.getValue().size() <= noOfLang)
				map.remove(e1.getKey());
		});
		return map;
	}

}

