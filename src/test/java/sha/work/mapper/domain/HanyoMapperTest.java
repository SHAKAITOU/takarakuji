package sha.work.mapper.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.ReplacementDataSetLoader;

import sha.work.service.domain.init.HanyoDataImportService;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages="sha.*")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class,
	DbUnitTestExecutionListener.class})
@Import(value= {HanyoDataImportService.class})
@DbUnitConfiguration(dataSetLoader = ReplacementDataSetLoader.class)
@Transactional
public class HanyoMapperTest {

	/** DB access class. */

	@Autowired
	private HanyoDataImportService service;

	@Test
	public void test_importHanyoData() throws Exception {
		service.importHanyoData();
	}

}
