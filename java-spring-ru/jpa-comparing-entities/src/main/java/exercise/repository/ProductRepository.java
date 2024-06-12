package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import exercise.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query(value = "SELECT * FROM products p WHERE p.title = ?1 AND p.price = ?2", nativeQuery = true)
    Product findByTitleAndPrice(String title, int price);
}
