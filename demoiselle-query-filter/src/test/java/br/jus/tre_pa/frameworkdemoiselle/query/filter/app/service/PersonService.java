package br.jus.tre_pa.frameworkdemoiselle.query.filter.app.service;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.app.model.Person;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.core.JPAQueryFilter;

@BusinessController
public class PersonService {

  @Inject 
  private JPAQueryFilter<Person> filter;
  
  //@Inject
  //private EntityManager entityManager;
  
  //@Produces
  public JPAQueryFilter<Person> getJpaQueryFilter(InjectionPoint injectionPoint) {
    
    ParameterizedType type = (ParameterizedType) injectionPoint.getType();
    
    Class c = (Class) type.getActualTypeArguments()[0];
   
    return null;//new JPAQueryFilter()null, Person.class);
  }

 
  
}
