package br.jus.tre_pa.frameworkdemoiselle.query.filter.core;

import static org.junit.Assert.fail;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import br.jus.tre_pa.frameworkdemoiselle.query.filter.app.model.Person;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.app.service.PersonService;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.util.AbstractTest;


//@RunWith(Arquillian.class)
public class JPAQueryFilterTest extends AbstractTest {

  @Inject
  PersonService personService; 
  
  
  /*@Deployment
  public static Archive<?> createTestArchive() {
    
    Archive<?>[] GUAVA = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml")
            .artifact("com.google.guava:guava").exclusion("*").resolveAs(GenericArchive.class)
            .toArray(new Archive<?>[0]);
    
    Archive<?>[] D_CORE = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml")
            .artifact("br.gov.frameworkdemoiselle:demoiselle-core").resolveAs(GenericArchive.class)
            .toArray(new Archive<?>[0]);
    
    
    return ShrinkWrap.create(WebArchive.class, "test.war")
                      .addClasses(PersonService.class, AbstractTest.class)
                      .addPackages(true, "br.jus.tre_pa.frameworkdemoiselle.query.filter")
                      .addAsLibraries(GUAVA)
                      .addAsLibraries(D_CORE)
                      .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                      .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
  } */ 
  

  @Test
  public void testCountAll() {
    
    List<Person> personsFromJPQL = getListFromJPQL("select p from Person p", Person.class);
    
    System.out.println(personService); 
    
    //filter.setBeanClass(Person.class);
    
    
    
    //JPAQueryFilter<Person> filter = JPAQueryFilter.create(getEntityManager(), Person.class);
    
   //assertEquals(personsFromJPQL.size(), filter.countAll());

    //assertTrue(personsFromJPQL.containsAll(easyCriteriaResult));
    
  }
  
  
  @Test
  public void testFindAll() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetBeanClass() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetEntityManager() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetPagination() {
    fail("Not yet implemented");
  }

}
