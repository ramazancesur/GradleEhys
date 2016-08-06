package tr.com.prolms.base.repository;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by ramazancesur on 30/04/2016.
 */
@Transactional
public interface BaseRepo<T> extends JpaRepository<T,Long> {
}
