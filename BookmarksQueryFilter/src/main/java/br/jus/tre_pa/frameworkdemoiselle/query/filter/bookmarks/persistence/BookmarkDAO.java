package br.jus.tre_pa.frameworkdemoiselle.query.filter.bookmarks.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.bookmarks.domain.Bookmark;

@PersistenceController
public class BookmarkDAO extends JPACrud<Bookmark, Long> {
	
	private static final long serialVersionUID = 1L;
	
}
