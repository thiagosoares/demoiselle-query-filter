package br.jus.tre_pa.frameworkdemoiselle.query.filter.unit;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.jus.tre_pa.frameworkdemoiselle.query.filter.fixture.SingularCriterionFixture;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.operations.StartWithOperation;

//@RunWith(DemoiselleRunner.class)
public class SingularCriterionProducersTest {

	@Inject
	private SingularCriterionFixture singularCriterionFixture;

	@Test
	public void createStartWithOperationTest() {
		assertEquals(StartWithOperation.class, singularCriterionFixture.getSingularCriterionStartWithOperation().getOperation().getClass());
		assertEquals("singularCriterionStartWithOperation", singularCriterionFixture.getSingularCriterionStartWithOperation().getField());
	}
}
