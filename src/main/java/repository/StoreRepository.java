package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.Product;

public class StoreRepository {
	private ArrayList<Product> allProducts=new ArrayList<Product>(); 	
	
	public ArrayList<Product> getAllProducts() {
		return allProducts;
	}

	public void setAllProducts(ArrayList<Product> list ){this.allProducts = list;}

	public void readFile(String fname) throws NumberFormatException, IOException{
		FileInputStream f=new FileInputStream(fname);
		DataInputStream in = new DataInputStream(f);
		BufferedReader buf =new BufferedReader(new InputStreamReader(in));
		String rd;
		String []line;
		while((rd=buf.readLine())!=null){
				line=rd.split(" ");
				if(line.length>=4) {
					this.allProducts.add(new Product(Integer.parseInt(line[0]), line[1], line[2], Integer.parseInt(line[3])));
				}
		}
		in.close();
	}
	public String addNewProduct(Product p) throws IOException{
		if(p.getCode()>0 && !illegal(p.getCategory()) && p.getQuantity()>=0 && p.getCode()<Integer.MAX_VALUE&&p.getQuantity()<Integer.MAX_VALUE&& !illegal(p.getName())){
			BufferedWriter out = new BufferedWriter(new FileWriter("products.txt",true));
			int k=1;
			for(Product i:allProducts){
				if(i.getCode()==p.getCode()){
					k=0;
				}
			}
			if(k==1){
				out.newLine();
				out.write(p.getCode()+" "+p.getName()+" "+p.getCategory()+" "+p.getQuantity());
				out.close();
				allProducts.add(p);
			}
			else{
				System.err.println("This code already exists");
				out.close();
				return("This code already exists");
			}
		}
		else{
			return("code q");
		}
		//System.out.println("Product added");
		return("success");
	}

	private boolean illegal(String name) {
		if(name.length()==0)
			return true;
		char c;
		for(int i=0;i<name.length();++i) {
			c = name.charAt(i);
			if (!((c <= 'z' && c >= 'a') || (c <= 'Z' && c >= 'A')|| (c <= '9' && c >= '1')))
				return true;
		}
		return false;
	}

	public ArrayList<Product> getProductsCategory(String cat){
		ArrayList<Product> cProducts=new ArrayList<Product>(); //1
		for(Product p:allProducts){ //2
			if(p.getCategory().compareTo(cat)==0){ //3
				cProducts.add(p); //4
			}
		}
		return cProducts; //5
	}
	
	public ArrayList<Product> stockSituationProduct(String pname){
		ArrayList<Product> prods=new ArrayList<Product>();
		for(Product p:allProducts)
			if(p.getName().compareTo(pname)==0)
				prods.add(p);
		return prods;
	}
	public ArrayList<Product> stockSituation() {
		return allProducts;
	}
	public void EmptyList(){this.allProducts.clear();}
	
}
