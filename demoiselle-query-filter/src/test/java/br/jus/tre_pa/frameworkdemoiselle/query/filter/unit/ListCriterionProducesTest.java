package br.jus.tre_pa.frameworkdemoiselle.query.filter.unit;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.jus.tre_pa.frameworkdemoiselle.query.filter.fixture.ListCriterionFixture;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.operations.InOperation;

//@RunWith(DemoiselleRunner.class)
public class ListCriterionProducesTest {

	@Inject
	private ListCriterionFixture listCriterionFixture;

	@Test
	public void createInOperationTest() {
		assertEquals(InOperation.class, listCriterionFixture.getListCriterion().getOperation().getClass());
	}

}
