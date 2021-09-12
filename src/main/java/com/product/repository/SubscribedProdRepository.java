package com.product.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.entity.SubscribedProd;

	public interface SubscribedProdRepository extends JpaRepository<SubscribedProd, String>{
		List<SubscribedProd> findByBuyerid(String buyerid);
	}

