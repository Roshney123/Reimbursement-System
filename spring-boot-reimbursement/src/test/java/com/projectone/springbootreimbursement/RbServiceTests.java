package com.projectone.springbootreimbursement;


import com.projectone.springbootreimbursement.models.Rb;
import com.projectone.springbootreimbursement.service.RbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RbServiceTests {

        public RbService service;
        /**Test Data*/
        public Rb rb=new Rb(1,"April",-5,"Travel","Applied",201,"roshney.kuriakose@gmail.com");
        public Rb rb1=new Rb(2,"May",0,"Medical","Applied",202,"roshney.kuriakose@gmail.com");

        /** To initialize before each test*/
        @BeforeEach
        void setUp() {
            System.out.println("Initializing before test");

            service = new RbService();

        }

        /**Test for Employee id is 0 */
        @Test
        void findByEmpId() {
            IllegalStateException ex= Assertions.assertThrows(IllegalStateException.class,()->{
                service.findByEmpId(0);
            });
            Assertions.assertEquals("Employee id cannot be 0", ex.getMessage(), "Can't throw null value for employee id");

        }

        /**Test for Reimbursement amount is or negative value */
        @Test
        void saveRbs() {
            IllegalArgumentException ex=Assertions.assertThrows(IllegalArgumentException.class,()->{
                        service.saveRbs(rb);

                    }
            );
            Assertions.assertEquals("Amount cannot be negative or zero", ex.getMessage(), "Can't throw negative value for amount");
            ex=Assertions.assertThrows(IllegalArgumentException.class,()->{
                service.saveRbs(rb1);
            });
            Assertions.assertEquals("Amount cannot be negative or zero", ex.getMessage(), "Can't throw  zero value for amount ");

        }


        /** Test for reimbursement id passing is 0 */
        @Test
        void findById() {

            IllegalStateException ex= Assertions.assertThrows(IllegalStateException.class,()->{
                service.findById(0);
            });
            Assertions.assertEquals(" id cannot be 0", ex.getMessage(), "Can't throw null value for Reimbursement id");

        }

        /**Test for Reimbursement amount is or negative value  in update method */


    }

