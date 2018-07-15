/**
 *
 *  @author Szarek Marcin S15541
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class CustomersPurchaseSortFind {

	ArrayList<Purchase> list = new ArrayList<>();

	public void readFile(String path) {
		try {
			BufferedReader file = new BufferedReader(new FileReader(path));
			String dataRow = file.readLine();
			while (dataRow != null) {
				String[] values = dataRow.split(";");
				list.add(new Purchase(values[0], values[1], values[2], Double.parseDouble(values[3]),
						Double.parseDouble(values[4])));
				dataRow = file.readLine();
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showSortedBy(String sorter) {
		ArrayList<Purchase> showList = new ArrayList<>(list);
		if (sorter.equals("Nazwiska")) {
			showList.sort(new Comparator<Purchase>() {
				@Override
				public int compare(Purchase arg0, Purchase arg1) {
					if (arg0.getName().compareTo(arg1.getName()) != 0) {
						return arg0.getName().compareTo(arg1.getName());
					} else
						return arg0.getId().compareTo(arg1.getId());
				}
			});
			System.out.println(sorter);
			for (Purchase purchase : showList) {
				System.out.println(purchase.toString());
			}
			System.out.println();
		} else if (sorter.equals("Koszty")){
			showList.sort(new Comparator<Purchase>() {
				@Override
				public int compare(Purchase arg0, Purchase arg1) {
					if (arg1.getExpense().compareTo(arg0.getExpense()) != 0) {
						return arg1.getExpense().compareTo(arg0.getExpense());
					} else
						return arg0.getId().compareTo(arg1.getId());
				}
			});
			System.out.println(sorter);
			for (Purchase purchase : showList) {
				System.out.println(purchase.toString() + " (koszt: " + purchase.getExpense() + ")");
			}
			System.out.println();
		} else {
			System.out.println("Nierozpoznana komenda");
		}
	}
	
	public void showPurchaseFor(String id) {
		System.out.println("Klient " + id);
		for(Purchase purchase : list) 
			if(purchase.getId().equals(id)) 
				System.out.println(purchase);
		System.out.println();	
	}

}
