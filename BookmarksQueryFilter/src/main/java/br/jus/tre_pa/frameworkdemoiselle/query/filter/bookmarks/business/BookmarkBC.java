package br.jus.tre_pa.frameworkdemoiselle.query.filter.bookmarks.business;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.bookmarks.criteria.BookmarkQueryFilter;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.bookmarks.domain.Bookmark;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.bookmarks.persistence.BookmarkDAO;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.core.JPAQueryFilter;

@BusinessController
public class BookmarkBC extends DelegateCrud<Bookmark, Long, BookmarkDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@Inject
  private JPAQueryFilter<Bookmark> qfilter;
	
	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new Bookmark("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Demoiselle SourceForge", "http://sf.net/projects/demoiselle"));
			insert(new Bookmark("Twitter", "http://twitter.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Blog", "http://blog.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Wiki", "http://wiki.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Bug Tracking", "http://tracker.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Forum", "http://forum.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("SVN", "http://svn.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Maven", "http://repository.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Downloads", "http://download.frameworkdemoiselle.gov.br"));
		}
	}
	
	public List<Bookmark> findAll(BookmarkQueryFilter filter) {
	   
	  //Insirar as regras de negócio aki
	  //Valide a pesquisa aki.
	  
	  
	  //TODO O para BookmarkQueryFilter deve ser passado para a busca
	  return qfilter.findAll();
	}
	
	/*@Produces
  public JPAQueryFilter getJpaQueryFilter(InjectionPoint injectionPoint) {
    
    ParameterizedType type = (ParameterizedType) injectionPoint.getType();
    
    Class c = (Class) type.getActualTypeArguments()[0];
   
    return new JPAQueryFilter(em, c);
  }*/
	
}
