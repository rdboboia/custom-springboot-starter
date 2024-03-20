package rd.boboia.transacciones.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import rd.boboia.transacciones.model.MyModel;

public interface MyDao extends JpaRepository<MyModel, Integer>{

}
