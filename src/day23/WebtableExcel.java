package day23;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebtableExcel {
	
	FirefoxDriver f;
	@BeforeTest
	public void setup()
	{
		f = new FirefoxDriver();
		f.get("https://timeanddate.com/worldclock/");
	}
	@Test
	public void webtableTest() throws IOException
	{
		FileInputStream file = new FileInputStream("E:\\ExcelRead.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet ws = wb.getSheet("Sheet3");
		WebElement table= f.findElement(By.xpath("html/body/div[1]/div[8]/section[2]/div[1]/table"));
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		for(int i=0;i<rows.size();i++)
		{
			Row r =ws.createRow(i);
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			for(int j=0;j<cols.size();j++)
			{
				r.createCell(j).setCellValue(cols.get(j).getText());
			}
		}
		FileOutputStream fop= new FileOutputStream("E:\\ExcelRead.xlsx");
		wb.write(fop);
	}

		
	

}
