package tr.com.prolms.base.service.BaseServices;


import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by ramazancesur on 30/04/2016.
 */
public interface IBaseServices {
  public long createEntity(T Entity);

  public T updateEntity(T Entity);

  public void deletedEntity(long id);

  public void deleteEntity(T Entity);

  public List<T> getAllEntity();

  public T getEntity(long id);

}
