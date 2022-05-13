
package com.projectone.springbootreimbursement.service;

import com.projectone.springbootreimbursement.controllers.RbController;
import com.projectone.springbootreimbursement.models.Rb;
import com.projectone.springbootreimbursement.repositories.Rbrepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
/**
 * Service class of Rb, acts between Repository and Controller
 */
@Service
public class RbService {

    @Autowired
    private Rbrepository rbrepository;
    @Autowired
    RestTemplate restTemplate =new RestTemplate();
    public void setRbRepository(Rbrepository rbRepository) {
        this.rbrepository = rbRepository;
    }

    public RbService(Rbrepository rbrepository) {
        super();
        this.rbrepository = rbrepository;
    }
    public RbService() {
    }

    /**
     * To get all reimbursements
     *
     * @return all reimbursement applications from database
     */
    public List<Rb> getAllRbs() {

        return rbrepository.findAll();
    }

    /**
     * To get the Reimbursement applications according to Employee id
     *
     //  * @param empId get employee id from  the path,pass to the function findByEmpId()
     * @return List of Reimbursements having specified id
     */
    public List<Rb> findByEmpId(int empId)throws IllegalStateException {
        if (empId == 0) {
            throw new IllegalStateException("Employee id cannot be 0");
        } else {
            List<Rb> outGoingRb = rbrepository.findByEmpId(empId);

            /** To get logging message*/
            Logger logger = LoggerFactory.getLogger(RbController.class);
            logger.info("Getting Reimbursement applications submitted by employee" + empId);

            if (outGoingRb != null) {
                System.out.println(outGoingRb);
                return outGoingRb;

            }
            return null;

        }
    }

    /**
     * To create a new Reimbursement request and to update to Reimbursement table
     *
     * @param incomingRb to fetch the json body
     */
    public Rb saveRbs(Rb incomingRb)
            throws IllegalArgumentException {
        if ((incomingRb.getRb_amount() < 0) || (incomingRb.getRb_amount() == 0)) {
            throw new IllegalArgumentException("Amount cannot be negative or zero");
        } else {


            /** To get logging message*/
            Logger logger = LoggerFactory.getLogger(RbController.class);
            logger.info("Adding new Reimbursement application");

            Rb OutGoingRb = rbrepository.save(incomingRb);
            return OutGoingRb;
        }
    }

    /**
     * To get the Reimbursement applications according to reimbursement id
     *
     * @param rb_id represents reimbursement id
     * @return reimbursement application details if found, else return null
     */
    public Rb findById(int rb_id) throws IllegalStateException {
        if (rb_id == 0) {
            throw new IllegalStateException(" id cannot be 0");
        } else {

            /** To get logging message*/
            Logger logger = LoggerFactory.getLogger(RbController.class);
            logger.info("Getting Reimbursement application Rb_id");

            Rb outGoingRb = rbrepository.findById(rb_id);

            if (outGoingRb != null) {
                System.out.println(outGoingRb);
                return outGoingRb;

            }

            return null;
        }
    }

    /**
     * To update the reimbursement applications according to the eligibility
     * Assume  1000 as maximum amount that can be approved
     * If applied amount is more than 2000, automatically decline
     * Reassigned if the amount is between 1000 and 2000
     *
     * @return updated status with application details
     */
    public String updateRb(Rb in)throws IllegalArgumentException {
        if ((in.getRb_amount() < 0) || (in.getRb_amount() == 0)) {
            // throw new IllegalArgumentException
            System.out.println("Amount cannot be negative or zero");
            return null;
        } else {


            /** To get logging message*/
            Logger logger = LoggerFactory.getLogger(RbController.class);
            logger.info("Updating Reimbursement application by checking eligibility");

            if (in.getRb_amount() < 1000) {
                in.setRb_status("Approved");
            } else if (in.getRb_amount() > 2000) {
                in.setRb_status("Denied");
            } else {
                in.setRb_status("Reassigned");
            }

            Rb updatedRb = rbrepository.save(in);
            if (updatedRb.getRb_status() == "Reassigned") {
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                HttpEntity<Rb> entity = new HttpEntity<Rb>(updatedRb, headers);
                logger.info(updatedRb.getEmpMailId());
                String m_id = updatedRb.getEmpMailId();
                logger.info(m_id);
                String s = restTemplate.exchange(
                        "http://localhost:8000/send/" + m_id, HttpMethod.PUT, entity, String.class).getBody();


                return (s);
            } else if (updatedRb.getRb_status() == "Approved") {
                return "Application is Approved";
            } else {
                return "Application is denied";
            }
        }
    }

}
