package br.jus.tre_pa.frameworkdemoiselle.query.filter.app;

import javax.inject.Inject;

import br.jus.tre_pa.frameworkdemoiselle.query.filter.annotations.Attribute;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.criterion.SingularCriterion;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.enums.OperationType;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.qualifiers.Predicate;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.stereotypes.QueryFilter;

@QueryFilter
public class PersonFilter {

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
