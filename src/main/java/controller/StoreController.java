package controller;

import java.io.IOException;
import java.util.ArrayList;

import repository.StoreRepository;
import model.Product;

public class StoreController { 
	StoreRepository repo =new StoreRepository();
	public void readProducts(String f){
		try {
			repo.readFile(f);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void addProduct(Product p) throws Exception{
		try {

			String s = repo.addNewProduct(p);
			if (!s.equals("success")){

				throw new Exception("NU merge");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public ArrayList<Product> getProductsCategory(String cat){
		return repo.getProductsCategory(cat);
	}
	
	public ArrayList<Product> stockSituationProduct(String pname){
		return repo.stockSituationProduct(pname);
	}
	public ArrayList<Product> stockSituation(){
		return repo.stockSituation();
	}
	public void EmptyList() {repo.EmptyList();}
}
