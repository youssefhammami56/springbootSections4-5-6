package com.youssef.produits;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.youssef.produits.entities.Produit;
import com.youssef.produits.repos.ProduitRepository;
import com.youssef.produits.service.ProduitService;

@SpringBootTest
class ProduitsApplicationTests {

	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private ProduitService produitService;
	@Test
	public void testCreateProduit() {
		Produit prod = new Produit("tv",2100.500,new Date());
		produitRepository.save(prod);
	}

	@Test
	public void testFindProduit() {
		Produit p = null;
		try {
			p = produitRepository.findById(5L).get();
			System.out.println(p);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateProduit() {
		Produit p = null;
		try {
			p = produitRepository.findById(5L).get();
			p.setPrixProduit(1000.0);
			produitRepository.save(p);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}



	@Test
	public void testDeleteProduit()
	{
		produitRepository.deleteById(5L);;
	}

	@Test
	public void testListerTousProduits()
	{
		List<Produit> prods = produitRepository.findAll();
		for (Produit p : prods)
		{
			System.out.println(p);}}

	@Test
	public void testFindByNomProduitContains()
	{
		Page<Produit> prods = produitService.getAllProduitsParPage(0,2);
		System.out.println(prods.getSize());
		System.out.println(prods.getTotalElements());
		System.out.println(prods.getTotalPages());
		prods.getContent().forEach(p -> {System.out.println(p.toString());
		});

	}

}
