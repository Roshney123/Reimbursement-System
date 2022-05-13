
package com.projectone.springbootreimbursement.controllers;

import com.projectone.springbootreimbursement.models.Rb;
import com.projectone.springbootreimbursement.service.RbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

/**
 * Controller of Rb, perform CRUD operations of Rb(Reimbursement)
 */
@RestController
@RequestMapping("/Rbs")
public class RbController {
    @Autowired
    RbService rbservice;
    @Autowired
    RestTemplate restTemplate;
    /**
     * To create a new Reimbursement request and to update to Reimbursement table
     * @param incomingRb to fetch the json body
     */
    @PostMapping("/new")
    public Rb createRb(@RequestBody Rb incomingRb) {

        /** To get logging message*/
        Logger logger = LoggerFactory.getLogger( RbController.class);
        logger.info("Adding new Reimbursement application");
        Rb rb;
        logger.info("incomingRb"+incomingRb);
        rb=rbservice.saveRbs(incomingRb);
        return rb;
    }


    /**
     * To get the Reimbursement applications according to reimbursement id
    // * @param rb_id represents reimbursement id
     * @return reimbursement application details
     */
    @GetMapping("/viewId/{rb_id}")
    public Rb viewRbById(@PathVariable int rb_id) {

        /** To get logging message*/
        Logger logger = LoggerFactory.getLogger( RbController.class);
        logger.info("Getting Reimbursement application Rb_id");

        Rb outGoingRb = rbservice.findById(rb_id);

        return  outGoingRb;

    }

    /**
     * To get the Reimbursement applications according to Employee id
     * @param empId get employee id from  the path,pass to the function findByEmpId()
     * @return List of Reimbursements having specified id
     */
    @GetMapping("/viewEmpId/{empId}")
    public List<Rb> findByEmpId(@PathVariable int empId) {
        List< Rb >outGoingRb = rbservice.findByEmpId(empId);

        /** To get logging message*/
        Logger logger = LoggerFactory.getLogger( RbController.class);
        logger.info("Getting Reimbursement applications submitted by employee"+empId);

        if (outGoingRb!=null) {
            System.out.println(outGoingRb);
            return outGoingRb;


        }
        return null;

    }

    /**
     * To View all reimbursements
     * @return all reimbursement applications from database
     */
    @GetMapping("/viewAll")
    public List<Rb> viewAllRb() {

        /** To get logging message*/
        Logger logger = LoggerFactory.getLogger( RbController.class);
        logger.info("Getting All Reimbursement applications ");
        List<Rb>rbs=rbservice.getAllRbs();
        logger.info("viewAll"+rbs);
        return rbs;
    }

    /**
     * To update the reimbursement applications according to the eligibility
     * Assume  1000 as maximum amount that can be approved
     * If applied amount is more than 2000, automatically decline
     * Reassigned if the amount is between 1000 and 2000
     * @param rb_id reimbursement id to get the details from the database
     * @return updated status with application details
     */

    @RequestMapping(value = "/update/{rb_id}", method = RequestMethod.PUT)
    public String updateRb(@RequestBody @PathVariable int rb_id)  {

       String s= rbservice.updateRb( rbservice.findById(rb_id));
       return (s);
    }

}
