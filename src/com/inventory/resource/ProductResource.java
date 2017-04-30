package com.inventory.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.inventory.product.Product;
import com.inventory.service.ProductService;




@Path("/inventory")
public class ProductResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	
	
	ProductService productService;

	public ProductResource() {
		productService = new ProductService();
	}
	
	public ProductResource(UriInfo inUriInfo,Request inRequest ,String id) {
		this.uriInfo = inUriInfo;
		this.request = inRequest;
		this.id = id;
		productService = new ProductService();

	}

	

	@GET
	@Path("/list")
	@Produces({MediaType.APPLICATION_XML,  MediaType.APPLICATION_JSON})
	public Response getAllProducts() {		
		
		
		 List<Product> products = new ArrayList<Product>();

         for( Map.Entry<String,Product> e : productService.getAllProducts().entrySet() )
         {	 
        	 products.add(e.getValue());
         }
         
         GenericEntity<List<Product>> productList = new GenericEntity<List<Product>>(products) {
         };

         return Response.ok(productList).build();
		
	}

	

    @GET
	@Path("/add/{id}/{name}/{type}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,MediaType.TEXT_XML })
	public Response AddProduct(@PathParam("id") String id,@PathParam("name") String name,@PathParam("type") String type) throws IOException {
		if(id!=null && id.length()>0){
    
		Product product = new Product(id, name, type);
    	
    	if(productService.getProductByid(product)==null){
		productService.addProduct(product);
		return Response.status(200).entity(product).build();
    	}
    	return Response.status(200).entity(product) .build();
		}
	
		return Response.status(200).entity("Enter Values :").build();
		
		//return Response.ok(response).build();
		
		
	}
    
    
    @PUT    
    @Path("/update")
    @Consumes({MediaType.APPLICATION_JSON ,MediaType.TEXT_XML ,MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,MediaType.TEXT_XML })
   	public Response updateProductDetails(JAXBElement<Product> prodt) throws IOException {
   		
    	Product prod=prodt.getValue();
    	
    	System.out.println(" put update------------>  "+prod.getId() +"name-->"+prod.getName() +"type"+prod.getType());
    	
   		Product product = new Product(prod.getId(), prod.getName() , prod.getType());
   		product=productService.updateProduct(product);  
 
   		
   		List<Product> products = productService.getListProducts(productService.getAllProducts());
       
        
        GenericEntity<List<Product>> productList = new GenericEntity<List<Product>>(products) {
        };
   		return Response.status(200).entity(productList) .build();
   		
   	}
    
    @PUT    
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON ,MediaType.TEXT_XML ,MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,MediaType.TEXT_XML })
   	public Response addProductDetails(JAXBElement<Product> prodt) throws IOException {
   		
    	Product prod=prodt.getValue();
    	
    	System.out.println(" put add------------>  "+prod.getId() +"name-->"+prod.getName() +"type"+prod.getType());
    	
   		Product product = new Product(prod.getId(), prod.getName() , prod.getType());
   		product=productService.addProduct(product);  
 
   		
   		List<Product> products = productService.getListProducts(productService.getAllProducts());
       
        
        GenericEntity<List<Product>> productList = new GenericEntity<List<Product>>(products) {
        };
   		return Response.status(200).entity(productList) .build();
   		
   	}
    
    @GET
    @Path("/update/{id}/{name}/{type}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,MediaType.TEXT_XML })
   	public Response updateProduct(@PathParam("id") String id,@PathParam("name") String name,@PathParam("type") String type) throws IOException {
   		
    	System.out.println(" update------------>  "+id +"name-->"+name +"type"+type);
    	
   		Product product = new Product(id, name, type);
   		product=productService.updateProduct(product);  
 
   		
   		List<Product> products = productService.getListProducts(productService.getAllProducts());
       
        
        GenericEntity<List<Product>> productList = new GenericEntity<List<Product>>(products) {
        };
   		return Response.status(200).entity(productList) .build();
   		
   	}
    
    @GET
    @Path("/delete/{id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,MediaType.TEXT_XML })
   	public Response deleteProduct(@PathParam("id")String id) throws IOException {
   		Product product = new Product(id,"","");
   		productService.deleteProduct(product);
   	 List<Product> products = new ArrayList<Product>();

     for( Map.Entry<String,Product> e : productService.getAllProducts().entrySet() )
     {	 
    	 products.add(e.getValue());
     }
     
     GenericEntity<List<Product>> productList = new GenericEntity<List<Product>>(products) {
     };

     return Response.ok(productList).build();
   		
   	}
    
    @DELETE
    @Path("/delete")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,MediaType.TEXT_XML })
   	public Response deleteProductById(JAXBElement<Product> prodt) throws IOException {
    	Product prod=prodt.getValue();
    	System.out.println(" deleteProductById------------>  "+prod.getId());
   		Product product = new Product(prod.getId(),"","");
   		productService.deleteProduct(product);
   	 List<Product> products = new ArrayList<Product>();

     for( Map.Entry<String,Product> e : productService.getAllProducts().entrySet() )
     {	 
    	 products.add(e.getValue());
     }
     
     GenericEntity<List<Product>> productList = new GenericEntity<List<Product>>(products) {
     };

     return Response.ok(productList).build();
   		
   	}
    
    @GET
    @Path("/deleteAll")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,MediaType.TEXT_XML })
   	public Response deleteAllProduct() throws IOException {
    	
    	System.out.println(" deleteAllProducts------------>  ");
   		
   		
   	 List<Product> products = productService.deleteAllProduct();
    
     
     GenericEntity<List<Product>> productList = new GenericEntity<List<Product>>(products) {
     };

     return Response.ok(productList).build();
   		
   	}
    
    @DELETE
    @Path("/deleteAll")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,MediaType.TEXT_XML })
   	public Response deleteAllProductS() throws IOException {
    	
    	System.out.println(" DELETEAllProducts------------>  ");
   		
   		
   	 List<Product> products = productService.deleteAllProduct();
    
     
     GenericEntity<List<Product>> productList = new GenericEntity<List<Product>>(products) {
     };

     return Response.ok(productList).build();
   		
   	}
    
   

	

}