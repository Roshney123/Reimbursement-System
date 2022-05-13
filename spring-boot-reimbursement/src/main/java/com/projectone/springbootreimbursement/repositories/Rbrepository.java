
package com.projectone.springbootreimbursement.repositories;

        import com.projectone.springbootreimbursement.models.Rb;
        import org.springframework.data.jpa.repository.JpaRepository;
        import java.util.List;

/**
 * Interface which extends Jpa Repository,stands between RbController and Rb
 */
public interface Rbrepository extends JpaRepository<Rb, Integer> {


    List<Rb> findByEmpId(int empId);
    Rb save(Rb r);
    List<Rb> findAll();
    Rb findById(int rb_id);


}

