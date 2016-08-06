package tr.com.prolms.base.service.BaseServices;

import org.apache.poi.ddf.EscherColorRef;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.prolms.base.repository.BaseRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramazancesur on 30/04/2016.
 */
@Service

public class BaseServices<T> implements IBaseServices {

  @Autowired
  private BaseRepo baseRepo;


  @Override
  public long createEntity(org.apache.poi.ss.formula.functions.T Entity) {
    try {
      baseRepo.save(Entity);
      return 1;
    }
    catch (Exception ex){
      return 0;
    }
  }

  @Override
  public org.apache.poi.ss.formula.functions.T updateEntity(org.apache.poi.ss.formula.functions.T Entity) {
    try {
      return (org.apache.poi.ss.formula.functions.T) baseRepo.save(Entity);
    }
    catch (Exception ex){
      ex.printStackTrace();
      return  null;
    }

  }

  @Override
  public void deletedEntity(long id) {
    try {
      baseRepo.delete(id);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void deleteEntity(org.apache.poi.ss.formula.functions.T Entity) {
    try {
      baseRepo.delete(Entity);
    }
    catch (Exception e){
      e.printStackTrace();
    }

  }

  @Override
  public List<org.apache.poi.ss.formula.functions.T> getAllEntity() {
    try {
      return baseRepo.findAll();
    }
    catch (Exception e){
      e.printStackTrace();
      return  null;
    }

  }

  @Override
  public org.apache.poi.ss.formula.functions.T getEntity(long id) {
    try {
      return (org.apache.poi.ss.formula.functions.T) baseRepo.findOne(id);
    }
    catch (Exception e){
      e.printStackTrace();
      return null;
    }

  }


}
