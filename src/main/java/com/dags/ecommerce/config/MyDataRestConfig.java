package com.dags.ecommerce.config;

import com.dags.ecommerce.entity.Country;
import com.dags.ecommerce.entity.Product;
import com.dags.ecommerce.entity.ProductCategory;
import com.dags.ecommerce.entity.State;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    @Value("${allowed.origins}")
    private String[] allowedOrigins;


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};

        disableHttpMethods(Product.class, config, unsupportedActions);
        disableHttpMethods(ProductCategory.class, config, unsupportedActions);
        disableHttpMethods(Country.class, config, unsupportedActions);
        disableHttpMethods(State.class, config, unsupportedActions);

        exposeIds(config);

        cors.addMapping(config.getBasePath()+"/**").allowedOrigins(allowedOrigins);

    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] unsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        // get a list of all entity classes from the EntityManager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        var entityClasses = new ArrayList<>();

        // add the entity types for the entities to the list
        entities.forEach(entity -> entityClasses.add(entity.getJavaType()));

        //expose to the ids
        var domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
