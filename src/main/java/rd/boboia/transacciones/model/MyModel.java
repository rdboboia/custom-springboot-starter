package rd.boboia.transacciones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "MyModelCustomTableName")
@Data
public class MyModel {
	@Id
	@GeneratedValue
	private int id;
	private String name;
}
