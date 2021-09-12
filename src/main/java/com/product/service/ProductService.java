package com.product.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.product.dto.ProductDTO;
import com.product.entity.Product;
import com.product.exception.ProductException;
import com.product.repository.ProductRepository;
import com.product.repository.SubscribedProdRepository;

@Service
public class ProductService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductRepository productrepo;

	@Autowired
	SubscribedProdRepository subscribedprodrepo;

	// Get products by name
	public List<ProductDTO> getProductByName(@PathVariable String productname) throws ProductException {

		logger.info("Product request with name {}", productname);

		Iterable<Product> product = productrepo.findByproductname(productname);
		List<ProductDTO> productDTO = new ArrayList<ProductDTO>();

		product.forEach(Pro -> {
			productDTO.add(ProductDTO.valueOf(Pro));
		});
		if (productDTO.isEmpty())
			throw new ProductException("Service.PRODUCTS_NOT_FOUND");
		return productDTO;
	}

	//Get products by category
	public List<ProductDTO> getProductBycategory(@PathVariable String category) throws ProductException {
		logger.info("Product request of category {}", category);
		Iterable<Product> product = productrepo.findBycategory(category);
		List<ProductDTO> productDTO = new ArrayList<ProductDTO>();

		product.forEach(Pro -> {
			productDTO.add(ProductDTO.valueOf(Pro));
		});
		if (productDTO.isEmpty())
			throw new ProductException("Service.PRODUCTS_NOT_FOUND");
		return productDTO;
	}

	//Get products by seller id
	public List<ProductDTO> getProductBySellerId(@PathVariable String sellerid) throws ProductException {
		logger.info("Product request of seller {}", sellerid);
		Iterable<Product> product = productrepo.findBysellerid(sellerid);
		List<ProductDTO> productDTO = new ArrayList<ProductDTO>();

		product.forEach(Pro -> {
			productDTO.add(ProductDTO.valueOf(Pro));
		});
		if (productDTO.isEmpty())
			throw new ProductException("Service.PRODUCTS_NOT_FOUND");
		return productDTO;
	}

	//Get all product details
	public List<ProductDTO> getAllProduct() throws ProductException {

		Iterable<Product> products = productrepo.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();

		products.forEach(product -> {
			ProductDTO productDTO = ProductDTO.valueOf(product);
			productDTOs.add(productDTO);
		});
		if (productDTOs.isEmpty())
			throw new ProductException("Service.PRODUCTS_NOT_FOUND");
		logger.info("Product Details : {}", productDTOs);
		return productDTOs;
	}

	//Update stock
	public Product updateProductStock(ProductDTO products, String prodid) throws ProductException{
        Product existingProduct = productrepo.findById(prodid).orElse(null);
        if(existingProduct != null) {
            existingProduct.setStock(products.getStock());
            return productrepo.save(existingProduct); 
        }else {
        	throw new ProductException("Service.PRODUCTS_NOT_FOUND");
        }
    
    }
	
	//Get product by id
	public ProductDTO getByProdid(String prodid) throws ProductException{
		logger.info("Profile request for customer {}", prodid);
		Product pro = productrepo.findByProdid(prodid);
		if(pro != null) {
			ProductDTO proDTO = ProductDTO.valueOf(pro);
			logger.info("Profile for customer : {}", proDTO);
			return proDTO;
		} else{
			throw new ProductException("Service.PRODUCTS_NOT_FOUND");
		}
	}
	
	//Add product
	public void saveProduct(ProductDTO productDTO) throws ProductException {
		logger.info("Adding product with data {}", productDTO);
		Product product = productDTO.createProduct();
		productrepo.save(product);
	}


}
