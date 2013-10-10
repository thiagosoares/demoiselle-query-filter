package br.jus.tre_pa.frameworkdemoiselle.query.filter.bookmarks.criteria;

import javax.inject.Inject;

import br.jus.tre_pa.frameworkdemoiselle.query.filter.annotations.Attribute;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.bookmarks.domain.Bookmark;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.core.JPAQueryFilter;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.criterion.SingularCriterion;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.enums.OperationType;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.qualifiers.Predicate;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.stereotypes.QueryFilter;

@QueryFilter
public class BookmarkQueryFilter {

	private static final long serialVersionUID = -766978003137540645L;

	@Inject
	@Predicate(operation = OperationType.STARTS_WITH_IGNORE_CASE)
	@Attribute(name = "description")
	private SingularCriterion<String> descriptionCriterion;

	
	public SingularCriterion<String> getDescriptionCriterion() {
		return descriptionCriterion;
	}

	
	public void setDescriptionCriterion(SingularCriterion<String> descriptionCriterion) {
		this.descriptionCriterion = descriptionCriterion;
	}

}
