package br.gov.caixa.api.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.gov.caixa.api.util.TesteForTests;


//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring-context.xml")
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class FuncionarioTest {
		
//	@Autowired
//	private FuncionarioService funcionarioService;
	
	@Test
	public void test() {
		
		TesteForTests testeForTests = new TesteForTests();
		assertTrue( testeForTests.ExecuteCalc() == 1 );
	}

//	@Test
//    public void testSampleServiceGetAccountDescription() {
//    	 
//    	 coordenacaoService = new CoordenacaoService();
//    	 
//    	 Coordenacao coordenacao = hibernateTemplate.get(Coordenacao.class, 1);
//    	 
//    	 int resp = coordenacaoService.setCoordenador((long)1, (long)40);   	 
//    	 assertTrue(resp == 1);
//
//     }


}
