package assessment_hibernate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int opt = 0;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		List<Product> pl = new ArrayList<>();
		do {
			System.out.println("______________________________");
			System.out.println();
			System.out.println("             MENU");
			System.out.println("______________________________");
			System.out.println("Select one of the following by typing [1, 2, 3 or 4]");
			System.out.println("1. Add Product in the inventory");
			System.out.println("2. Update Product in the inventory");
			System.out.println("3. Delete Product from the inventory");
			System.out.println("4. Display Product List");
			System.out.println("5. Exit");
			int o = Integer.parseInt(br.readLine());
			switch (o) {
			case 1:
				float price = 0;
				int quan = 0;
				System.out.println("Enter Product ID");
				String idst = br.readLine();
				if (idst.length() >= 3) {
					System.out.println("Enter Product Name");
					String name = br.readLine();
					System.out.println("Enter Product Description");
					String descr = br.readLine();
					System.out.println("Enter Product Price");
					String pr = br.readLine();
					if (pr.matches("\\d+(\\.\\d+)?") && pr.contains(".")) {
						price = Float.parseFloat(pr);
					} else {
						System.err.println("Price must be in the decimal format");
						break;
					}
					System.out.println("Enter Product Quantity");
					String q = br.readLine();
					if (q.matches("[0-9]+")) {
						quan = Integer.parseInt(q);
					} else {
						System.err.println("Quantity can only have numbers");
						break;
					}
					Product p = new Product(idst, name, descr, price, quan);
					ProductDao pDao = new ProductDao();
					pDao.create(p);
					System.err.println("Product Added Successfully");
				} else {
					System.err.println("ID should have minimum 3 characters");
				}
				break;

			case 2:
				float pric = 0;
				int qua = 0;
				ProductDao pdaO = new ProductDao();
				System.out.println("Enter Product ID");
				String idstr = br.readLine();
				if (idstr.length() == 3) {
					System.out.println("What do you want to update? Select [1 or 2]");
					System.out.println("1. Product Price");
					System.out.println("2. Product Quantity");
					int c = Integer.parseInt(br.readLine());
					switch (c) {
					case 1:
						Product p1 = pdaO.find(idstr);
						if (p1 == null) {
							System.err.println("Product with enterd ID does not exist");
						} else {
							System.out.println("Enter the changed price");
							String pr = br.readLine();
							if (pr.matches("\\d+(\\.\\d+)?") && pr.contains(".")) {
								pric = Float.parseFloat(pr);
								p1.setPrice(pric);
								pdaO.update(p1);
								System.err.println("Price Updated Successfully");
							} else {
								System.err.println("Price must be in the decimal format");
								break;
							}
						}
						break;

					case 2:
						Product p2 = pdaO.find(idstr);
						if (p2 == null) {
							System.err.println("Product with enterd ID does not exist");
						} else {
							System.out.println("Enter the changed quantity");
							String qu = br.readLine();
							if (qu.matches("[0-9]+")) {
								qua = Integer.parseInt(qu);
								p2.setQuantity(qua);
								;
								pdaO.update(p2);
								System.err.println("Quantity Updated Successfully");
							} else {
								System.err.println("Quantity can only have numbers");
								break;
							}
						}
						break;
					}

				} else {
					System.err.println("ID must have atlest 3 chracters.");
				}
				break;
			case 3:
				System.out.println("Enter Product ID");
				String ids = br.readLine();
				ProductDao pdao = new ProductDao();
				Product pd = pdao.find(ids);
				if (pd == null) {
					System.err.println("Product with enterd ID does not exist.");
				} else {
					pdao.delete(ids);
					System.err.println("Product deleted Successfully");
				}
				break;

			case 4:
				ProductDao pdAo = new ProductDao();
				pl = pdAo.findAll();
				System.out.println("INVENTORY LIST");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("%5s | %50s | %50s | %50s | %50s |", "ID", "Product Name", "Price", "Quantity", "Description");
				System.out.println();
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				for (Product plo : pl) {
					System.out.printf("%5s | %50s | %50f | %50d | %50s |", plo.getId(), plo.getName(), plo.getPrice(),
							plo.getQuantity(), plo.getDescr());
					System.out.println();
					System.out.println(
							"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				}
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				break;

			case 5:
				System.out.println("Exiting");
				opt = -1;
				break;
			}
		} while (opt != -1);
	}

}
