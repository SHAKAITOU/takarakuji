package sha.work.mapper.loto;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.ReplacementDataSetLoader;

import sha.framework.util.CsvFileReader;
import sha.work.dto.loto.Loto7;
import sha.work.loto.LotoConstant;
import sha.work.mapper.domain.HanyoMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class,
	DbUnitTestExecutionListener.class})
@DbUnitConfiguration(dataSetLoader = ReplacementDataSetLoader.class)
@Transactional
public class Loto7MapperTest {

	/** DB access class. */
	@Autowired
	private Loto7Mapper mapper;

	@Autowired
	private HanyoMapper hanyoMapper;

	@Test
	public void testInsert() throws Exception {
		List<List<String>> csvData = CsvFileReader.read(LotoConstant.LOTO7+LotoConstant.CSV);

		for(List<String> data : csvData) {
			Loto7 loto7 = new Loto7();
			loto7.setTurn(Integer.valueOf(data.get(Loto7Mapper.IDX_TURN)));
			loto7.setOpenDt(data.get(Loto7Mapper.IDX_OPEN_DT));
			loto7.setL1(Integer.valueOf(data.get(Loto7Mapper.IDX_L1)));
			loto7.setL2(Integer.valueOf(data.get(Loto7Mapper.IDX_L2)));
			loto7.setL3(Integer.valueOf(data.get(Loto7Mapper.IDX_L3)));
			loto7.setL4(Integer.valueOf(data.get(Loto7Mapper.IDX_L4)));
			loto7.setL5(Integer.valueOf(data.get(Loto7Mapper.IDX_L5)));
			loto7.setL6(Integer.valueOf(data.get(Loto7Mapper.IDX_L6)));
			loto7.setL7(Integer.valueOf(data.get(Loto7Mapper.IDX_L7)));
			loto7.setB1(Integer.valueOf(data.get(Loto7Mapper.IDX_B1)));
			loto7.setB2(Integer.valueOf(data.get(Loto7Mapper.IDX_B2)));

			//mapper.save(loto7);
		}

	}

	@Test
	public void test_getNomalNumberSum() throws Exception {

	}

}
