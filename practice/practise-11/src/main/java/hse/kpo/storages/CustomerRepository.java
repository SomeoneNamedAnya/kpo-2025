package hse.kpo.storages;

import hse.kpo.domains.Catamaran;
import hse.kpo.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    void deleteByName(String name);
}