package com.inventory.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inventory.dao.ProductDao;
import com.inventory.product.Product;

public class ProductService {

	ProductDao productDao;

	public ProductService() {
		productDao = ProductDao.instance;
	}

	public Product addProduct(Product product) {
		return productDao.addProduct(product);
		
	}

	public Product updateProduct(Product product) {
		return productDao.updateProduct(product);
	}
	
	public Product getProductByid(Product product) {
		return productDao.getProductById(product);
	}

	

	public Product deleteProduct(Product product) {
		return productDao.deleteProduct(product);
	}
	public List<Product> deleteAllProduct() {
		return productDao.deleteAllProduct();
	}
	
	
	
	public Map<String, Product> getAllProducts() {
		return productDao.getAllProducts();
	}
	
	

	public List<Product> getListProducts(Map<String, Product> allProducts) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
    	
        for( Map.Entry<String,Product> e : allProducts.entrySet() )
        {	 
       	 products.add(e.getValue());
        }
        
        return products;
	}

}
