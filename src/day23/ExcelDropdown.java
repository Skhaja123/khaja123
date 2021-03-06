package day23;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.examples.CreateCell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class ExcelDropdown {
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream file = new FileInputStream("E:\\ExcelRead.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet ws = wb.getSheet("Sheet2");
		Row r;
		
		System.setProperty("webdriver.chrome.driver", "F:\\chromedriver.exe");
		ChromeDriver c = new ChromeDriver();
		c.get("http://newtours.demoaut.com/");
		c.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a")).click();
		WebElement drop=c.findElement(By.name("country"));
		List<WebElement> dropdown= drop.findElements(By.tagName("option"));
		System.out.println(dropdown.size());
		for(int i=0;i<dropdown.size();i++)
		{
			r = ws.createRow(i);
			r.createCell(0).setCellValue(dropdown.get(i).getText());
			dropdown.get(i).click();
			if(dropdown.get(i).isSelected())
				r.createCell(1).setCellValue("Passed");
			else
				r.createCell(1).setCellValue("Failed");
			
		}
		FileOutputStream fop= new FileOutputStream("E:\\ExcelRead.xlsx");
		wb.write(fop);
	}

}
