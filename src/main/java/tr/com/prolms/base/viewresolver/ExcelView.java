package tr.com.prolms.base.viewresolver;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import tr.com.prolms.base.entity.Role;
import tr.com.prolms.base.entity.User;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcelView extends AbstractExcelView {

  private final String commaString = ",";

  @Override
  protected void buildExcelDocument(Map<String, Object> model,
                                    HSSFWorkbook workbook, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

    CellStyle style = workbook.createCellStyle();
    style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    style.setAlignment(CellStyle.ALIGN_CENTER);
    Row row = null;
    Cell cell = null;
    int rowCount = 0;
    int colCount = 0;

    Sheet workbookSheet = workbook.createSheet("sheet 1");
    // Create header cells
    row = workbookSheet.createRow(rowCount++);

    cell = row.createCell(colCount++);
    cell.setCellStyle(style);
    cell.setCellValue("Name");

    cell = row.createCell(colCount++);
    cell.setCellStyle(style);
    cell.setCellValue("Surname");

    cell = row.createCell(colCount++);
    cell.setCellStyle(style);
    cell.setCellValue("Roles");

    // Create data cells
    row = workbookSheet.createRow(rowCount++);
    colCount = 0;

    List<User> users = (List<User>) model.get("user");

    row.createCell(colCount++).setCellValue(users.get(0).getName());
    row.createCell(colCount++).setCellValue(users.get(0).getSurname());

    StringBuffer toppings = new StringBuffer("");
    for (Role role : users.get(0).getRoles()) {
      toppings.append(role.getName());
      toppings.append(commaString);
    }
    row.createCell(colCount++).setCellValue(toppings.toString());

    for (int i = 0; i < 3; i++) {
      workbookSheet.autoSizeColumn(i, true);
    }

  }

}
