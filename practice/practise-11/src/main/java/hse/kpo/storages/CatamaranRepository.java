package hse.kpo.storages;

import hse.kpo.domains.Catamaran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatamaranRepository extends JpaRepository<Catamaran, Integer> {
    @Query("""
        SELECT c 
        FROM Catamaran c 
        JOIN c.engine e 
        WHERE e.type = :engineType 
        AND c.vin > :minVin
    """)
    List<Catamaran> findCatamaransByEngineTypeAndVinGreaterThan(
            @Param("engineType") String engineType,
            @Param("minVin") Integer minVin
    );
}