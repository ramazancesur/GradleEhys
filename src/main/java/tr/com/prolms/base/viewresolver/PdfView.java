package tr.com.prolms.base.viewresolver;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import tr.com.prolms.base.entity.Role;
import tr.com.prolms.base.entity.User;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PdfView extends AbstractPdfView {

  private final String commaString = ",";

  @Override
  protected void buildPdfDocument(Map<String, Object> model,
                                  Document document, PdfWriter writer, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

    PdfPTable table = new PdfPTable(3);
    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
    table.getDefaultCell().setBackgroundColor(Color.lightGray);

    table.addCell("Name");
    table.addCell("Surname");
    table.addCell("Roles");

    List<User> users = (List<User>) model.get("user");

    table.addCell(users.get(0).getName());
    table.addCell(users.get(0).getSurname());

    StringBuffer toppings = new StringBuffer("");
    for (Role role : users.get(0).getRoles()) {
      toppings.append(role.getName());
      toppings.append(commaString);
    }
    table.addCell(toppings.toString());
    document.add(table);

  }

}
