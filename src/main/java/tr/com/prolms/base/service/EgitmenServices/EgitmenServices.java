package tr.com.prolms.base.service.EgitmenServices;

import org.springframework.beans.factory.annotation.Autowired;
import tr.com.prolms.base.entity.BaseEntity;
import tr.com.prolms.base.entity.Egitmen;
import tr.com.prolms.base.repository.EgitmenRepo;
import tr.com.prolms.base.service.BaseServices.BaseServices;

/**
 * Created by ramazancesur on 30/04/2016.
 */
public class EgitmenServices extends BaseServices<Egitmen> {
  @Autowired
  private EgitmenRepo egitmenRepo;


}
