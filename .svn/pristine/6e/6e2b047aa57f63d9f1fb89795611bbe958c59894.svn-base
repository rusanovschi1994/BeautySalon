package com.iucosoft.beautysalon.fileservice.fileserviceDao;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.iucosoft.beautysalon.fileservice.UserFileServiceIntf;
import com.iucosoft.beautysalon.models.User;
import com.iucosoft.beautysalon.models.UserRole;
import java.io.FileOutputStream;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Rusanovschi
 */
public class UserFileServiceImpl implements UserFileServiceIntf {

    @Override
    public void scrie(List<User> lista, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook();//1
        Sheet creditSheet = workbook.createSheet("Utilizatori");//2

        int rowIndex = 0;

        Row rowTitlu = creditSheet.createRow(rowIndex++);
        int cellIndexTitlu = 0;

        CellStyle style = getBoldStyle(workbook);

        String[] titles = {"ID", "Nume/Prenume", "Login", "Drept de utilizator"};

        for (String title : titles) {
            createRowTitleBold(style, rowTitlu, cellIndexTitlu++, title);

        }
        for (User u : lista) {
            Row row = creditSheet.createRow(rowIndex++);

            int cellIndex = 0;
            row.createCell(cellIndex++).setCellValue(u.getId());
            row.createCell(cellIndex++).setCellValue(u.getName());
            row.createCell(cellIndex++).setCellValue(u.getLogin());
            row.createCell(cellIndex++).setCellValue(u.getUserRole().getRole());
        }
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
        fos.close();
    }

    @Override
    public List<User> citeste(String fileName) throws IOException {
        List<User> lista = new ArrayList<>();

        FileInputStream fis = new FileInputStream(fileName);
        // Using XSSF for xlsx format, for xls use HSSF
        Workbook workbook = new XSSFWorkbook(fis);

        Sheet sheet = workbook.getSheetAt(0);

        Iterator rowIterator = sheet.iterator();

        if (rowIterator.hasNext()) {
            rowIterator.next(); // sarim peste titluri
        }

        //citire date din tabela
        while (rowIterator.hasNext()) {
            Row row = (Row) rowIterator.next();
            Iterator cellIterator = row.cellIterator();

            User u = new User();

            while (cellIterator.hasNext()) {

                Cell cell = (Cell) cellIterator.next();
                if (cell.getColumnIndex() == 0) {
                    u.setId((int) cell.getNumericCellValue());
                } else if (cell.getColumnIndex() == 1) {
                    u.setName(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 2) {
                    u.setLogin(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 4) {
                    u.setPassword(cell.getStringCellValue());
                }
            }
            lista.add(u);
        }
        fis.close();
        workbook.close();

        return lista;
    }

    private CellStyle getBoldStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderTop((short) 6); // double lines border
        style.setBorderBottom((short) 1); // single line border
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);

        return style;
    }

    private void createRowTitleBold(CellStyle style, Row rowTitlu, int nr, String val) {
        Cell cell = rowTitlu.createCell(nr);
        cell.setCellValue(val);
        cell.setCellStyle(style);
    }

    @Override
    public void creaza(List<User> lista, String fileName) throws IOException {

        Document document = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            Paragraph paragraph = new Paragraph("Lista de utilizatori");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(105);
            table.setSpacingBefore(11f);
            table.setSpacingAfter(11f);
            addTableHeader(table);

            float[] colWidth = {2f, 2f, 2f, 2f};
            table.setWidths(colWidth);

            for (User u : lista) {
                PdfPCell c1 = new PdfPCell(new Paragraph(Integer.toString(u.getId())));
                PdfPCell c2 = new PdfPCell(new Paragraph(u.getName()));
                PdfPCell c3 = new PdfPCell(new Paragraph(u.getLogin()));
                PdfPCell c4 = new PdfPCell(new Paragraph(u.getUserRole().getRole()));

                table.addCell(c1);
                table.addCell(c2);
                table.addCell(c3);
                table.addCell(c4);
            }
            document.add(table);
            document.close();
            writer.close();

        } catch (DocumentException ex) {
            Logger.getLogger(UserFileServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("ID", "Nume/Prenume", "Login", "Drept de utilizator")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
}
