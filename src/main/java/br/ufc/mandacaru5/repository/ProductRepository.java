package br.ufc.mandacaru5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.mandacaru5.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findFirstByName(String name);
}
