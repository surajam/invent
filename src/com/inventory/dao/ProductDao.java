package com.inventory.dao;

import com.inventory.product.Product;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ProductDao {
	instance;

	private Map<String, Product> products = new HashMap<String, Product>();
	
	private ProductDao() {
	
	}
   public Product addProduct(Product product) {

		
		if(product!=null){
		products.put(product.getId(), product);
		
		return product;
		}
		return null;

	}
   public Product deleteProduct(Product product) {

		if(product!=null){
		products.remove(product.getId());
		
		return product;
		}
		return null;
	}
   public List<Product> deleteAllProduct() {
	   
	   System.out.println ("1 products size-->"+products.size());

	   for( Map.Entry<String,Product> e : products.entrySet() )
	     {	 
		   System.out.println ("delete All key-->"+e.getKey());
	    	 products.remove(e.getKey());
	     }
	 //  return (List<Product>) (products.values());
	   
	   System.out.println ("products size-->"+products.size());

List<Product> productList = Collections.list(Collections.enumeration(products.values()));

return productList;
 	}
 
   
   public Product updateProduct(Product product) {

	   if(product!=null){
		   
		   if(products.get(product.getId())!=null){
		   
			   products.put(product.getId(),product);
		   }else{
			   System.out.println ("prod id not exists-->"+product.getId());
			   
		   }

		return product;
		}
		return null;

	}
   
   
   
   
   public Product getProductById(Product product) {

	   if(product!=null && products.get(product.getId())!=null){
		   Product productByid=products.get(product.getId());

		return productByid;
		}
		return null;

	}

public Map<String, Product> getAllProducts() {
	return products;
}

	
}
