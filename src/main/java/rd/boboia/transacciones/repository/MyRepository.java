package rd.boboia.transacciones.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rd.boboia.transacciones.model.MyModel;
import rd.boboia.transacciones.repository.dao.MyDao;

@Service
public class MyRepository {
	
	@Autowired
	private MyDao myDao;
	
	public void getData() {
		MyModel myModel = new MyModel();
		myModel.setName("Radu");
		MyModel save = this.myDao.save(myModel);
		this.myDao.getReferenceById(1);
		
		System.out.println(save);
	}

}
