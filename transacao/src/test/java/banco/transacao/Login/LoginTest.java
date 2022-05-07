package banco.transacao.Login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import transacao.Repositories.RepositoryUser;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginTest {

	@Autowired
	private RepositoryUser repUser;
	
	@Test
	public void TesteLoginPattern() {
		
		Assertions.assertEquals(1, 1);
	}
	
}