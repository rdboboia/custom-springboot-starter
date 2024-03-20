package rd.boboia.transacciones.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rd.boboia.transacciones.repository.MyRepository;

@Service
public class MyBusiness {
	
	private static final String ADMIN_USER_NAME = "admin";
	
	@Autowired
	private MyRepository myRepository;
	
	public void doMyBusiness(String user) {
		if (ADMIN_USER_NAME.equals(user)) {
			this.myRepository.getData();
		} else {
			throw new RuntimeException("Unauthorized");
		}
	}
	
}
