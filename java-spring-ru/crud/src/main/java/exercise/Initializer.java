package exercise;

import exercise.model.Category;
import exercise.model.Product;
import exercise.repository.CategoryRepository;
import exercise.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Initializer implements ApplicationRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addModels();
    }

    private void addModels() {
        var category1 = new Category();
        category1.setId(1L);
        category1.setName("Напитки");
        categoryRepository.save(category1);

        var category2 = new Category();
        category2.setId(2L);
        category2.setName("Крупы");
        categoryRepository.save(category2);

        /////////
        var product1 = new Product();
        product1.setId(1);
        product1.setTitle("Pepsi");
        product1.setPrice(30);
        product1.setCategory(category1);

        var product2 = new Product();
        product2.setId(2);
        product2.setTitle("Яблочный сок");
        product2.setPrice(40);
        product2.setCategory(category1);

        var product3 = new Product();
        product3.setId(3);
        product3.setTitle("Апельсиновый сок");
        product3.setPrice(50);
        product3.setCategory(category1);

        var product4 = new Product();
        product4.setId(4);
        product4.setTitle("Гречка");
        product4.setPrice(15);
        product4.setCategory(category2);

        var products = List.of(
                product1, product2, product3, product4
        );

        productRepository.saveAll(products);

    }
}