package com.projectone.springbootreimbursement;

import com.projectone.springbootreimbursement.controllers.RbController;
import com.projectone.springbootreimbursement.models.Rb;
import com.projectone.springbootreimbursement.service.RbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RbController.class)
public class RbControllerTests {

    @MockBean
    private RbService rbService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnRb() throws Exception {
        int rb_id = 1;
        Rb rb = new Rb(rb_id,"May",1500,"Medical","Applied",202,"roshney.kuriakose@gmail.com");

        when(rbService.findById(rb_id)).thenReturn(rb);

        mockMvc.perform(get("//localhost:7000/Rbs/viewId/{rb_id}", rb_id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rb_id").value(rb_id));
    }
    @Test
    public void shouldReturnRbEmp() throws Exception {
    int empId=217;
    Rb rb1=new Rb(1,"May",1500,"Medical","Applied",empId,"roshney.kuriakose@gmail.com");
    Rb rb2=new Rb(2,"May",1500,"Medical","Applied",218,"roshney.kuriakose@gmail.com");

    List <Rb>rb = Stream.of(rb1,rb2).collect(Collectors.toList());
    assertTrue(rb.contains(rb1));

    }

}

