package br.com.mv.autowireds;

import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import br.com.mv.dto.ResultProcMovimentacao;

public class FabricaRelatorios {
	
	
	public void preencherPlanilha(String nomePlanilha, HSSFWorkbook workbook, List<ResultProcMovimentacao> lista) {
		CellStyle realStyle = workbook.createCellStyle();
		CellStyle backStyle = workbook.createCellStyle();
		backStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		backStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		HSSFSheet workSheet = workbook.createSheet(nomePlanilha);

		// Cabecalho da Planilha Excel
		preencherCabecalhoLinha(backStyle, workSheet);

		// Gerando o conteudo do arquivo
		int rownum = 1;
		for (ResultProcMovimentacao procMovimentacao : lista) {
			int cellnum = 0;
			HSSFRow row = workSheet.createRow(rownum++);
			HSSFCell cell1 = row.createCell(cellnum++);
			cell1.setCellStyle(realStyle);
			cell1.setCellValue(procMovimentacao.getId_conta().doubleValue());

			HSSFCell cell2 = row.createCell(cellnum++);
			cell2.setCellStyle(realStyle);
			cell2.setCellValue(procMovimentacao.getId_movimentacao().doubleValue());
			
			HSSFCell cell3 = row.createCell(cellnum++);
			cell3.setCellStyle(realStyle);
			cell3.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(procMovimentacao.getData()));
			
			HSSFCell cell4 = row.createCell(cellnum++);
			cell4.setCellStyle(realStyle);
			cell4.setCellValue(procMovimentacao.getId_empresa().doubleValue());
			
			HSSFCell cell5 = row.createCell(cellnum++);
			cell5.setCellStyle(realStyle);
			cell5.setCellValue(procMovimentacao.getMovimentacao_inicio().doubleValue());
			
			HSSFCell cell6 = row.createCell(cellnum++);
			cell6.setCellStyle(realStyle);
			cell6.setCellValue(procMovimentacao.getValor().doubleValue());
		}
	}
	
	private void preencherCabecalhoLinha(CellStyle backStyle, HSSFSheet workSheet) {
		HSSFRow headerRow = workSheet.createRow(0);
		createHeaderCell(0, "Id conta cliente", backStyle, headerRow);
		createHeaderCell(1, "Id movimentação", backStyle, headerRow);
		createHeaderCell(2, "Data", backStyle, headerRow);
		createHeaderCell(3, "Id Empresa", backStyle, headerRow);
		createHeaderCell(4, "Movimentação Inicio", backStyle, headerRow);
		createHeaderCell(5, "Valor", backStyle, headerRow);
	}
	
	private HSSFCell createHeaderCell(int columnNumber, String text, CellStyle backStyle, HSSFRow headerRow) {
		HSSFCell cell = headerRow.createCell(columnNumber);
		cell.setCellValue(text);
		cell.setCellStyle(backStyle);
		return cell;
	}
	

}
