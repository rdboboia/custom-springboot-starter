package rd.boboia.transacciones.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rd.boboia.transacciones.business.MyBusiness;

@RequestMapping(path = "/base")
@RestController
public class MyController {
	
	@Autowired
	private MyBusiness myBusiness;
	
	@GetMapping(path = "/myService")
	public ResponseEntity<Object> returnSomething(@RequestParam(value = "user") String user) {
		HashMap<String, String> map = new HashMap<>();
		map.put("radu", "hola");
		map.put("joe", "adio");
		
		this.myBusiness.doMyBusiness(user);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping(path = "/myService/{user}")
	public ResponseEntity<Object> pathParamMethod(@PathVariable String user) {
		HashMap<String, String> map = new HashMap<>();
		map.put("radu", "hola");
		map.put("joe", "adio");
		
		System.out.println(user);
		
		this.myBusiness.doMyBusiness(user);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
