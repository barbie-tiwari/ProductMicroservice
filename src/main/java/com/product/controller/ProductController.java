package com.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.product.dto.ProductDTO;
import com.product.dto.SubscribedProdDTO;
import com.product.entity.Product;
import com.product.exception.ProductException;
import com.product.service.ProductService;
import com.product.service.SubscribedProdService;

@RestController
public class ProductController {

	@Autowired
	ProductService productservice;
	
	@Autowired
	SubscribedProdService subproser;
	
	@Autowired
	Environment environment;
	
	//PRODUCT
	
	//Get all products
	@GetMapping(value = "/api/allproducts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getAllProduct() throws ProductException {
		try {
			List<ProductDTO> buyerDTOs = productservice.getAllProduct();
			return new ResponseEntity<>(buyerDTOs, HttpStatus.OK);
			} catch (Exception exception) {
			   throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),exception);
			}
		}

	
	// Get all products by product name
	@GetMapping(value = "/api/product/{productname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductByName(@PathVariable String productname)
			throws ProductException {
		try {
			List<ProductDTO> productDTO = productservice.getProductByName(productname);
			return new ResponseEntity<>(productDTO, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}

	}

	//Get all products by category
	@GetMapping(value = "/api/products/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductBycategory(@PathVariable String category)
			throws ProductException {
		try {
			List<ProductDTO> productDTO = productservice.getProductBycategory(category);
			return new ResponseEntity<>(productDTO, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}
	}
	
	//Get products by sellerid
	@GetMapping(value = "/apis/products/{sellerid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductBysellerid(@PathVariable String sellerid)
			throws ProductException {
		try {
			List<ProductDTO> productDTO = productservice.getProductBySellerId(sellerid);
			return new ResponseEntity<>(productDTO, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}
	}

	//Get products by prodid
	@GetMapping(value = "/api/prod/{prodid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getByProdid(@PathVariable String prodid) throws ProductException{
		try {
				ProductDTO product = productservice.getByProdid(prodid);
				return new ResponseEntity<>(product,HttpStatus.OK);
			} catch(Exception exception) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
						exception);
			}
			
		}
		
	//Add product
	@PostMapping(value = "/api/products/addproduct", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDTO productDTO) throws ProductException {
		try {
				String successMessage = environment.getProperty("API.INSERT_SUCCESS");
				productservice.saveProduct(productDTO);
				return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
			} catch (Exception exception) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
						exception);
			}
		}

	//Update stocks
	@PutMapping(value = "/api/products/{prodid}")
    public ResponseEntity<Product> updateProductStock(@RequestBody ProductDTO products, @PathVariable String prodid) throws ProductException{
        try {
        	Product prod = productservice.updateProductStock(products, prodid);
			return new ResponseEntity<>(prod, HttpStatus.OK);
        }catch(Exception exception) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
        }
    }
	
	//SUBSCRIBED PRODUCT
	
	//Get subscribed products of buyer
	@GetMapping(value = "/api/subs/{buyerid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubscribedProdDTO>> getByBuyerid(@PathVariable String buyerid)
			throws ProductException {
		try {
			List<SubscribedProdDTO> subscribed = subproser.getByBuyerid(buyerid);
			return new ResponseEntity<>(subscribed, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}
	}

	//Get all subscribed products
	@GetMapping(value = "/api/subsprod", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubscribedProdDTO>> getAllSubProduct() throws ProductException {
		try {
			List<SubscribedProdDTO> subscribedDto = subproser.getAllSubProduct();
			return new ResponseEntity<>(subscribedDto, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}
	}
	
}
