package com.project.onlinefoodorderingsystem.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.nio.file.Files;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.onlinefoodorderingsystem.exception.ResourceNotFoundException;
import com.project.onlinefoodorderingsystem.model.Category;
import com.project.onlinefoodorderingsystem.model.Company;
import com.project.onlinefoodorderingsystem.model.Employee;
import com.project.onlinefoodorderingsystem.model.Month;
import com.project.onlinefoodorderingsystem.model.Product;
import com.project.onlinefoodorderingsystem.model.Salary;
import com.project.onlinefoodorderingsystem.repository.ProductRepository;
import com.project.onlinefoodorderingsystem.services.FileUploadService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	public FileUploadService fileService;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/product")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/product/search/{name}")
	public List<Product> getProductByName(@PathVariable(value = "name") String productName) {
			return productRepository.serchUserByName(productName);
	}
	
	@GetMapping("/product/search-state/{state}")
	public List<Product> serchUserByState(@PathVariable(value = "state") String productState) {
			return productRepository.serchUserByState(productState);
	}
	
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
			throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
		return ResponseEntity.ok().body(product);
	}
	
	@GetMapping("/product/product-details/{id}")
	public ArrayList getProductDetailsById(@PathVariable(value = "id") Long productId)
	 {
		String SQL = "SELECT product, cat from product product, category cat WHERE category_id = product_category_id";
		 Query q = entityManager.createQuery(SQL);
		 if(!productId.equals("0")) {
			 System.out.print("Product Id : "+productId);

			 SQL = "SELECT product, cat from product product, category cat WHERE category_id = product_category_id AND product_id = :productId";
			 q = entityManager.createQuery(SQL);
			 q.setParameter("productId", productId);
		 } 
		 List<Object[]> product = q.getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : product ) {
			  Product product_details = (Product)row[ 0 ];
			  Category category_details = (Category)row[ 1 ];
			
			    HashMap<String, String> results = new HashMap();
			   
				results.put("category_title",category_details.getCategory_title());
				results.put("product_id",String.valueOf(product_details.getProduct_id()));
				results.put("product_title",product_details.getProduct_title());
				results.put("product_category_id",String.valueOf(product_details.getProduct_category_id()));
				results.put("product_image_filename",product_details.getProduct_image_filename());
				results.put("product_description",product_details.getProduct_description());
				results.put("product_cost",product_details.getProduct_cost());
				
			    resultArray.add(results);
			 
		 }	 

       return resultArray;
	}
	
	@GetMapping("/product/all-product/{id}")
	public ArrayList getAllSalaryFields(@PathVariable(value = "id") String category_id) {
		
		String SQL = "SELECT product, cat from product product, category cat WHERE category_id = product_category_id";
		 Query q = entityManager.createQuery(SQL);
		 if(!category_id.equals("0")) {
			 System.out.print("Employee Id : "+category_id);

			 SQL = "SELECT product, cat from product product, category cat WHERE category_id = product_category_id AND category_id = :category_id";
			 q = entityManager.createQuery(SQL);
			 q.setParameter("category_id", category_id);
		 } 
		 List<Object[]> product = q.getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : product ) {
			  Product product_details = (Product)row[ 0 ];
			  Category category_details = (Category)row[ 1 ];
			
			    HashMap<String, String> results = new HashMap();
			   
				results.put("category_title",category_details.getCategory_title());
				results.put("product_id",String.valueOf(product_details.getProduct_id()));
				results.put("product_title",product_details.getProduct_title());
				results.put("product_category_id",String.valueOf(product_details.getProduct_category_id()));
				results.put("product_image_filename",product_details.getProduct_image_filename());
				results.put("product_description",product_details.getProduct_description());
				results.put("product_cost",product_details.getProduct_cost());
				
			    resultArray.add(results);
			 
		 }	 

        return resultArray;
	}
	
	@GetMapping("/product/all-search/{search}")
	public ArrayList searchProduct(@PathVariable(value = "search") String search) {
		
		String SQL = "SELECT product, cat from product product, category cat WHERE category_id = product_category_id";
		 Query q = entityManager.createQuery(SQL);
		 if(!search.equals("0")) {

			 SQL = "SELECT product, cat from product product, category cat WHERE category_id = product_category_id AND (product_title LIKE :title OR category_title LIKE :title)";
			 q = entityManager.createQuery(SQL);
			 q.setParameter("title",  "%" + search + "%");
		 } 
		 List<Object[]> product = q.getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : product ) {
			  Product product_details = (Product)row[ 0 ];
			  Category category_details = (Category)row[ 1 ];
			
			    HashMap<String, String> results = new HashMap();
			   
				results.put("category_title",category_details.getCategory_title());
				results.put("product_id",String.valueOf(product_details.getProduct_id()));
				results.put("product_title",product_details.getProduct_title());
				results.put("product_category_id",String.valueOf(product_details.getProduct_category_id()));
				results.put("product_image_filename",product_details.getProduct_image_filename());
				results.put("product_description",product_details.getProduct_description());
				results.put("product_cost",product_details.getProduct_cost());
				
			    resultArray.add(results);
			 
		 }	 

        return resultArray;
	}
	

	@RequestMapping(value = "/product", method = RequestMethod.POST,
    consumes = {"multipart/form-data"})	
	public Product createProduct(@RequestParam("product_image") MultipartFile product_image, 
			@ModelAttribute("form") Product product) {
		System.out.print("File Data");
		try {
			long unixTime = System.currentTimeMillis() / 1000L;
			String fileName = unixTime+"_" +product_image.getOriginalFilename();
			System.out.print("File URL : ");
			System.out.print(this.fileService.uploadToLocalFileSystem(product_image, fileName));  
            product.setProduct_image_filename(fileName);
		
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return productRepository.save(product);
	}
	
	 
    // For Downloading Files
    @GetMapping("/product/product_image/{fileName:.+}")
    public Path getFileUrl(@PathVariable(name = "fileName") String fileName) throws IOException {
    	System.out.print("Printing URL");
    	System.out.print(this.fileService.getFileURL(fileName));
    	Path fileLocation = this.fileService.getFileLocation(fileName);
    	File file = new File(fileLocation.toString());
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(fileLocation.toString()));
   
    	return fileLocation;
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.POST,
    consumes = {"multipart/form-data"})	
	public Product updateProduct(@RequestParam("product_image") MultipartFile product_image, 
			@ModelAttribute("form") Product product) {
		System.out.print("File Data");
		try {
			if(!product_image.isEmpty()) {
				long unixTime = System.currentTimeMillis() / 1000L;
				String fileName = unixTime+"_" +product_image.getOriginalFilename();
				this.fileService.uploadToLocalFileSystem(product_image, fileName);
	            product.setProduct_image_filename(fileName);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return productRepository.save(product);
	}
    
    @PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId,
			@Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
		final Product updatedProduct = productRepository.save(productDetails);
		return ResponseEntity.ok(updatedProduct);
	}

	@DeleteMapping("/product/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId)
			throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
