package com.practice.util;

import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.practice.master.Master;

public class CommonMethods extends Master {

	public void extractNumbers() {
		String str = "Showing Records 1 - 6 of 6";
		String words[] = str.split(" ");
		int i = Integer.parseInt(words[words.length - 1]);
		System.out.println(i);
	}

	public static void waitMethod(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void selectDropDown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public void time() {
		System.out.println(LocalDateTime.now().getHour());
	}

	public static int getMonthIndex(String strMonth) {

		String strMonths[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		int i;
		for (i = 0; i < strMonths.length; i++) {
			if (strMonths[i].equalsIgnoreCase(strMonth))
				break;
		}
		return i;
	}

	public static int noOfrecords(WebElement elenoOfrecords) {
		if (elenoOfrecords.getText().isEmpty()) {
			return 0;
		} else {
			String words[] = elenoOfrecords.getText().split(" ");
			int intRecords = Integer.parseInt(words[words.length - 1]);
			return intRecords;
		}
	}

	private static void readRecords(int pageSize, int noOfCols) {
		String firstXPath = "//table[@class=\"lvt small\"]/tbody/tr[";
		String secondXPath = "]/td[";

		for (int i = 3; i < pageSize + 3; i++) {
			for (int j = 2; j <= noOfCols; j++) {
				String finalXPath = firstXPath + i + secondXPath + j + "]";
				System.out.print(driver.findElement(By.xpath(finalXPath)).getText() + " || ");
			}
			System.out.println();
		}
	}

	public static void displayRecords(int totalRecords, int noOfCols, WebElement eleBtnNext, WebElement eleStatusBar) {

		boolean btnEnable = true;
		int noOfPages = totalRecords / 20;
		int pageSize = 20;
		int lastPageSize = totalRecords % 20;
		for (int i = 0; i <= noOfPages; i++) {
			if (i == noOfPages) {
				pageSize = lastPageSize;
				btnEnable = false;
			}
			readRecords(pageSize, noOfCols);

			if (btnEnable == true) {
				eleBtnNext.click();
				WebDriverWait wait = new WebDriverWait(driver, 40);
				wait.until(ExpectedConditions.invisibilityOf(eleStatusBar));
			}
		}
	}

	public static String readObjRepo(String eleName) {
		String value = "";
		String objRepoFilePath = "E:\\Temp Eclipse Repository\\Tiger\\src\\main\\java\\com\\practice\\objectRepository\\ObjRepo.xml";
		try {

			FileInputStream FIS = new FileInputStream(objRepoFilePath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(FIS);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("ObjRep");

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) nNode;
					value = ele.getElementsByTagName(eleName).item(i).getTextContent();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	@Test
	public void test() {
		System.out.println(readObjRepo("input"));
	}
	public static void verifyAllLinks() {
		String url = null;
		int respCode = 200;
		List<WebElement> links = driver.findElements(By.tagName("a"));
		Iterator<WebElement> it = links.iterator();
		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			System.out.println(url);
		}

		try {
			HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());
			huc.setRequestMethod("HEAD");
			huc.connect();
			respCode = huc.getResponseCode();

			if (respCode >= 400) {
				System.out.println(url + "URL is broken");
			} else {
				System.out.println(url + "URL is valid link");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
