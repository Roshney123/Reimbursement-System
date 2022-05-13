
package com.projectone.springbootreimbursement.models;
/**
 * Model for Reimbursement(Rb) according to Reimbursement table
 */

import lombok.*;
import javax.persistence.*;


@Entity
@Table(name="reimbursement")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Rb {


    /**
     * rb_id refers to Reimbursement id
     */
    @Id
    @Column(name="rb_id", columnDefinition = "AUTO_INCREMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rb_id;

    /**
     * rb_month refers to month applied for the reimbursement
     */
    @Column(name="rb_month")
    private String rb_month;
    /**
     * rb_amount refers to amount applied for reimbursement
     */
    @Column(name="rb_amount")
    private double rb_amount;

    /**
     * rb_reason refers to reason for reimbursement application
     */
    @Column(name="rb_reason")
    private String rb_reason;
    /**
     * rb_status refers to status of application(Applied,Approved,Denied,Reassigned)
     */
    @Column(name="rb_status")
    private String rb_status;

    /**
     * empId refers to employee id who applied for the reimbursement
     */
    @Column(name="empId")
    private int empId;

    /**
     * empMailId refers to employee Mail id which we use to send mail if Reassigned
     */
    //@Column(name="empMailId")
    private String empMailId;

}

