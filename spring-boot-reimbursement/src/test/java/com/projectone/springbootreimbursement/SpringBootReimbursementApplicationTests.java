package com.projectone.springbootreimbursement;

import com.projectone.springbootreimbursement.models.Rb;
import com.projectone.springbootreimbursement.service.RbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootReimbursementApplicationTests {


	@Autowired
	RbService rbService;
	public Rb rb=new Rb(1,"April",500,"Travel","Applied",217,"roshney.kuriakose@gmail.com");
	public Rb rb1=new Rb(2,"May",1800,"Medical","Applied",220,"roshney.kuriakose@gmail.com");


	@Test
	public void shouldSaveRbs()
	{
		Assertions.assertNotNull(rbService.saveRbs(rb));
	}

	@Test
	public void shouldUpdateReimbursement()
	{
		Assertions.assertNotNull(rbService.updateRb(rb));
	}


	@Test
	public void shouldReturnAllReimbursements(){
		List reimbursements = rbService.getAllRbs();
		Assertions.assertNotNull(reimbursements);
	}

	@Test
	public void shouldReturnAllRbByEmpID(){
		List Rb = rbService.findByEmpId(217);
		Assertions.assertNotNull(Rb);
	}

	@Test
	void contextLoads() {
	}

}
