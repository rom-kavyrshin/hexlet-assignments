package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;


@Component
public class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withTitleContain(params.getTitleCont()))
                .and(withPriceLessThan(params.getPriceLt()))
                .and(withPriceGreaterThan(params.getPriceGt()))
                .and(withRatingGreaterThan(params.getRatingGt()));
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, cb) -> categoryId == null ? cb.conjunction() : cb.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> withTitleContain(String title) {
        return (root, query, cb) -> title == null ? cb.conjunction() : cb.like(root.get("title"), title);
    }

    private Specification<Product> withPriceLessThan(Integer priceLessThan) {
        return (root, query, cb) -> priceLessThan == null ? cb.conjunction() : cb.lessThan(root.get("price"), priceLessThan);
    }

    private Specification<Product> withPriceGreaterThan(Integer priceGreaterThan) {
        return (root, query, cb) -> priceGreaterThan == null ? cb.conjunction() : cb.greaterThan(root.get("price"), priceGreaterThan);
    }

    private Specification<Product> withRatingGreaterThan(Double ratingGreaterThan) {
        return (root, query, cb) -> ratingGreaterThan == null ? cb.conjunction() : cb.greaterThan(root.get("rating"), ratingGreaterThan);
    }

}
