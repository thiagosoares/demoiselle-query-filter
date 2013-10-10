package br.jus.tre_pa.frameworkdemoiselle.query.filter.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.pagination.PaginationContext;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.Reflections;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.helper.JPAQueryHelper;

public class JPAQueryFilter<T extends Object> implements QueryFilter<T>, Serializable {

  private static final long serialVersionUID = 4307459614522545738L;

  private EntityManager entityManager;

	private Pagination pagination;

	private Class<T> beanClass;
	
	@Inject
	private JPAQueryHelper helper;
	
	@Inject
	private InjectionPoint injectionPoint;
	
	
	/*public JPAQueryFilter() {
    super();
    this.beanClass = (Class<T>) Clazz.typeForGenericSuperclass(getClass(), 0);
  }*/

	/*public JPAQueryFilter(EntityManager entityManager, Class<T> beanClass) {
    super();
    
    this.entityManager = entityManager;
    this.beanClass = beanClass;
  }*/

  /*public static <T> JPAQueryFilter<T> create(EntityManager entityManager, Class<T> beanClass) {
	  return new JPAQueryFilter<T>(entityManager, beanClass);
	}*/
	

  public List<T> findAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getBeanClass());
		Root<T> p = cq.from(getBeanClass());

		addPredicatesToQuery(cq, cb, p);
		addOrderToQuery(cq, cb, p);

		TypedQuery<T> query = getEntityManager().createQuery(cq);

		Pagination pagination = getPagination();
		if (pagination != null) {
			query.setFirstResult(pagination.getFirstResult());
			query.setMaxResults(pagination.getPageSize());
			pagination.setTotalResults(this.countAll());
		}
		return query.getResultList();
	}

	protected int countAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<T> p = cq.from(getBeanClass());

		cq.select(cb.count(p));

		addPredicatesToQuery(cq, cb, p);

		TypedQuery<Long> query = getEntityManager().createQuery(cq);

		return query.getSingleResult().intValue();
	}

	private <X> void addPredicatesToQuery(CriteriaQuery<X> cq, CriteriaBuilder cb, Root<T> p) {
		Predicate[] predicates = helper.getPredicates(cb, p, this.getClass());
		if (predicates != null && predicates.length > 0) {
			cq.where(predicates);
		}
	}

	private <X> void addOrderToQuery(CriteriaQuery<X> cq, CriteriaBuilder cb, Root<T> p) {

	}

	protected Class<T> getBeanClass() {
	  
	  if (this.beanClass == null) {
	    ParameterizedType type = (ParameterizedType) injectionPoint.getType();
    
	  
	    this.beanClass = (Class<T>) type.getActualTypeArguments()[0];
		  //TODO Isso não esta funcionando. Ver o motivo
			//this.beanClass = Reflections.getGenericTypeArgument(this.getClass(), 0);
		}

		return this.beanClass;
	}
	
	protected EntityManager getEntityManager() {
		if (this.entityManager == null) {
		  //TODO Acredito que isso possa trazer problemas. E quando houver mais que um ?
			this.entityManager = Beans.getReference(EntityManager.class);
		}

		return this.entityManager;
	}

	protected Pagination getPagination() {
		if (pagination == null) {
			try {
				PaginationContext context = Beans.getReference(PaginationContext.class);
				pagination = context.getPagination(getBeanClass());

			} catch (ContextNotActiveException cause) {
				pagination = null;
			}
		}

		return pagination;
	}


  public void setBeanClass(Class<T> beanClass) {
    this.beanClass = beanClass;
  }

	
	
}
