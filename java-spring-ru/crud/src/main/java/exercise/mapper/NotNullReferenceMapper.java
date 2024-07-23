package exercise.mapper;

import exercise.exception.DependentResourceNotFoundException;
import exercise.model.BaseEntity;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class NotNullReferenceMapper {
    @Autowired
    private EntityManager entityManager;

    public <T extends BaseEntity> T toEntity(Long id, @TargetType Class<T> entityClass) throws DependentResourceNotFoundException {
        if (id != null) {
            var entity = entityManager.find(entityClass, id);

            if (entity == null) {
                throw new DependentResourceNotFoundException(entityClass.getSimpleName() + " with id " + id + " not found");
            }

            return entity;
        } else {
            throw new DependentResourceNotFoundException(entityClass.getSimpleName() + " with id " + id + " not found");
        }
    }
}