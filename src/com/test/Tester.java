package com.test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

import com.inventory.product.Product;

public class Tester {
	
	public ClientConfig config = new ClientConfig();
	public Client client = ClientBuilder.newClient(config);
	public WebTarget service = client.target("http://localhost:8080/invent/rest");
	
	
	
	@Test 
	public void testREST(){
		 // create one todo
     
        String AddExpectedResponse="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><product><id>1</id><name>prod1</name><type>typ</type></product>";
        String AddTwoExpectedResponse="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><product><id>2</id><name>prod2</name><type>typ</type></product>";
        String DeleteAllExpectedResponse="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><products></products>";
        String DeleteExpectedResponse="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><products></products>";
        
        String listExpectedResponse="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><products><product><id>1</id><name>prod1</name><type>typ</type></product><product><id>2</id><name>prod2</name><type>typ</type></product></products>";
        String xmlAddResponse="";
        String xmlResponse="";
        String deleteAllResponse=service.path("inventory/deleteAll").request().accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println ("Delete All Request-->"+deleteAllResponse);
       
     
        // Add://localhost:8080/invent/rest/inventory/add/1/test/typ
        
        xmlResponse= service.path("inventory/add").path("1/prod1/typ").request().accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println ("1 xmlAddResponse-->"+xmlResponse);
        assertEquals(xmlResponse,AddExpectedResponse);
        xmlResponse= service.path("inventory/add").path("2/prod2/typ").request().accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println ("2 xmlAddResponse-->"+xmlResponse);
        
        xmlResponse= service.path("inventory").path("list").request().accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println ("list-->"+xmlResponse);
        assertEquals(xmlResponse,listExpectedResponse);
       
      
        xmlResponse= service.path("inventory/update/1/product-edit-1/type1").request().accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println ("prod id 1 xmlUpdateResponse-->"+xmlResponse);
        
        assertEquals(xmlResponse, "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><products><product><id>1</id><name>product-edit-1</name><type>type1</type></product><product><id>2</id><name>prod2</name><type>typ</type></product></products>");
       
        
        xmlResponse= service.path("inventory/delete/1").request().accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println ("prod id 1 DeleteResponse-->"+xmlResponse);
        assertEquals(xmlResponse,"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><products><product><id>2</id><name>prod2</name><type>typ</type></product></products>");
        
        
       
     
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tester obj= new Tester();
		obj.testREST();
		

       

	}

}
