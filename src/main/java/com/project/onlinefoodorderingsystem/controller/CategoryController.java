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
import com.project.onlinefoodorderingsystem.repository.CategoryRepository;
import com.project.onlinefoodorderingsystem.services.FileUploadService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	public FileUploadService fileService;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/categories")
	public List<Category> getAllCategorys() {
		return categoryRepository.findAll();
	}

	@GetMapping("/categories/search/{name}")
	public List<Category> getCategoryByName(@PathVariable(value = "name") String categoryName) {
			return categoryRepository.serchUserByName(categoryName);
	}
	
	@GetMapping("/categories/search-state/{state}")
	public List<Category> serchUserByState(@PathVariable(value = "state") String categoryState) {
			return categoryRepository.serchUserByState(categoryState);
	}
	
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long categoryId)
			throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
		return ResponseEntity.ok().body(category);
	}
	

	@RequestMapping(value = "/categories", method = RequestMethod.POST,
    consumes = {"multipart/form-data"})	
	public Category createCategory(@RequestParam("category_image") MultipartFile category_image, 
			@ModelAttribute("form") Category category) {
		System.out.print("File Data");
		try {
			long unixTime = System.currentTimeMillis() / 1000L;
			String fileName = unixTime+"_" +category_image.getOriginalFilename();
			System.out.print("File URL : ");
			System.out.print(this.fileService.uploadToLocalFileSystem(category_image, fileName));  
            category.setCategory_image_filename(fileName);
		
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return categoryRepository.save(category);
	}
	
	 
    // For Downloading Files
    @GetMapping("/categories/category_image/{fileName:.+}")
    public Path getFileUrl(@PathVariable(name = "fileName") String fileName) throws IOException {
    	System.out.print("Printing URL");
    	System.out.print(this.fileService.getFileURL(fileName));
    	Path fileLocation = this.fileService.getFileLocation(fileName);
    	File file = new File(fileLocation.toString());
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(fileLocation.toString()));
   
    	return fileLocation;
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.POST,
    consumes = {"multipart/form-data"})	
	public Category updateCategory(@RequestParam("category_image") MultipartFile category_image, 
			@ModelAttribute("form") Category category) {
		System.out.print("File Data");
		try {
			if(!category_image.isEmpty()) {
				long unixTime = System.currentTimeMillis() / 1000L;
				String fileName = unixTime+"_" +category_image.getOriginalFilename();
				this.fileService.uploadToLocalFileSystem(category_image, fileName);
	            category.setCategory_image_filename(fileName);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return categoryRepository.save(category);
	}
    
    @PutMapping("/categories/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long categoryId,
			@Valid @RequestBody Category categoryDetails) throws ResourceNotFoundException {
		final Category updatedCategory = categoryRepository.save(categoryDetails);
		return ResponseEntity.ok(updatedCategory);
	}

	@DeleteMapping("/categories/{id}")
	public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") Long categoryId)
			throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));

		categoryRepository.delete(category);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
