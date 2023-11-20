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
import com.project.onlinefoodorderingsystem.model.Company;
import com.project.onlinefoodorderingsystem.repository.CompanyRepository;
import com.project.onlinefoodorderingsystem.services.FileUploadService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class CompanyController {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	public FileUploadService fileService;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/companys")
	public List<Company> getAllCompanys() {
		return companyRepository.findAll();
	}

	@GetMapping("/companys/search/{name}")
	public List<Company> getCompanyByName(@PathVariable(value = "name") String companyName) {
			return companyRepository.serchUserByName(companyName);
	}
	
	@GetMapping("/companys/search-state/{state}")
	public List<Company> serchUserByState(@PathVariable(value = "state") String companyState) {
			return companyRepository.serchUserByState(companyState);
	}
	
	
	@GetMapping("/companys/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable(value = "id") Long companyId)
			throws ResourceNotFoundException {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
		return ResponseEntity.ok().body(company);
	}
	

	@RequestMapping(value = "/companys", method = RequestMethod.POST,
    consumes = {"multipart/form-data"})	
	public Company createCompany(@RequestParam("company_image") MultipartFile company_image, 
			@ModelAttribute("form") Company company) {
		System.out.print("File Data");
		try {
			long unixTime = System.currentTimeMillis() / 1000L;
			String fileName = unixTime+"_" +company_image.getOriginalFilename();
			System.out.print("File URL : ");
			System.out.print(this.fileService.uploadToLocalFileSystem(company_image, fileName));  
            company.setCompany_image_filename(fileName);
		
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return companyRepository.save(company);
	}
	
	 
    // For Downloading Files
    @GetMapping("/companys/company_image/{fileName:.+}")
    public Path getFileUrl(@PathVariable(name = "fileName") String fileName) throws IOException {
    	System.out.print("Printing URL");
    	System.out.print(this.fileService.getFileURL(fileName));
    	Path fileLocation = this.fileService.getFileLocation(fileName);
    	File file = new File(fileLocation.toString());
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(fileLocation.toString()));
   
    	return fileLocation;
    }

    @RequestMapping(value = "/companys/{id}", method = RequestMethod.POST,
    consumes = {"multipart/form-data"})	
	public Company updateCompany(@RequestParam("company_image") MultipartFile company_image, 
			@ModelAttribute("form") Company company) {
		System.out.print("File Data");
		try {
			if(!company_image.isEmpty()) {
				long unixTime = System.currentTimeMillis() / 1000L;
				String fileName = unixTime+"_" +company_image.getOriginalFilename();
				this.fileService.uploadToLocalFileSystem(company_image, fileName);
	            company.setCompany_image_filename(fileName);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return companyRepository.save(company);
	}
    
    @PutMapping("/companys/{id}")
	public ResponseEntity<Company> updateCompany(@PathVariable(value = "id") Long companyId,
			@Valid @RequestBody Company companyDetails) throws ResourceNotFoundException {
		final Company updatedCompany = companyRepository.save(companyDetails);
		return ResponseEntity.ok(updatedCompany);
	}

	@DeleteMapping("/companys/{id}")
	public Map<String, Boolean> deleteCompany(@PathVariable(value = "id") Long companyId)
			throws ResourceNotFoundException {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));

		companyRepository.delete(company);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
