package org.springframework.samples.petclinic.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.conditions.Text;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OwnerPage {

	private final String domain = "http://localhost:8080/owners";

	@BeforeEach
	public void openPage() {
		open(this.domain);
	}

	List<SelenideElement> elementList = new ArrayList<SelenideElement>();

	public OwnerPage() {
		this.elementList.add($("#main-navbar > ul > li.active"));
		this.elementList.add($("#main-navbar > ul > li:nth-child(2)"));
		this.elementList.add($("#main-navbar > ul > li:nth-child(3)"));
		this.elementList.add($("#main-navbar > ul > li:nth-child(4)"));
		this.openPage();
	}

	@Test
	public void elementExist() {
		this.elementList.forEach(element -> {
			element.should(Condition.exist);
		});
	}

	@Test
	public void testName1() {
		SelenideElement elementLink = $("#owners > tbody > tr:nth-child(1) > td:nth-child(1) > a");
		elementLink.should(Condition.exist);
		elementLink.should(Condition.visible);
		elementLink.click();
		String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(domain + "/1", currentURL);
		SelenideElement elementB = $(
				"body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(1) > td > b");
		elementB.should(Condition.visible);
		elementB.should(Condition.exist);
		elementB.should(Condition.text("George Franklin"));
	}

	@Test
	public void testName2() {
		SelenideElement elementLink2 = $("#owners > tbody > tr:nth-child(2) > td:nth-child(1) > a");
		elementLink2.should(Condition.exist);
		elementLink2.should(Condition.visible);
		elementLink2.click();
		String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(domain + "/2", currentURL);
		SelenideElement elementB2 = $(
				"body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(1) > td > b");
		elementB2.should(Condition.visible);
		elementB2.should(Condition.exist);
		elementB2.should(Condition.text("Betty Davis"));
	}

	@Test
	public void testName3() {
		SelenideElement elementLink3 = $("#owners > tbody > tr:nth-child(3) > td:nth-child(1) > a");
		elementLink3.should(Condition.exist);
		elementLink3.should(Condition.visible);
		elementLink3.click();
		String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(domain + "/3", currentURL);
		SelenideElement elementB3 = $(
				"body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(1) > td > b");
		elementB3.should(Condition.visible);
		elementB3.should(Condition.exist);
		elementB3.should(Condition.text("Eduardo Rodriquez"));
	}

	@Test
	public void testName4() {
		SelenideElement elementLink4 = $("#owners > tbody > tr:nth-child(4) > td:nth-child(1) > a");
		elementLink4.should(Condition.exist);
		elementLink4.should(Condition.visible);
		elementLink4.click();
		String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(domain + "/4", currentURL);
		SelenideElement elementB4 = $(
				"body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(1) > td > b");
		elementB4.should(Condition.visible);
		elementB4.should(Condition.exist);
		elementB4.should(Condition.text("Harold Davis"));
	}

	@Test
	public void testName5() {
		SelenideElement elementLink5 = $("#owners > tbody > tr:nth-child(5) > td:nth-child(1) > a");
		elementLink5.should(Condition.exist);
		elementLink5.should(Condition.visible);
		elementLink5.click();
		String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(domain + "/5", currentURL);
		SelenideElement elementB5 = $(
				"body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(1) > td > b");
		elementB5.should(Condition.visible);
		elementB5.should(Condition.exist);
		elementB5.should(Condition.text("Peter McTavish"));
	}

	@Test
	public void testName6() {
		SelenideElement elementLink6 = $("#owners > tbody > tr:nth-child(6) > td:nth-child(1) > a");
		elementLink6.should(Condition.exist);
		elementLink6.should(Condition.visible);
		elementLink6.click();
		String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(domain + "/6", currentURL);
		SelenideElement elementB6 = $(
				"body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(1) > td > b");
		elementB6.should(Condition.visible);
		elementB6.should(Condition.exist);
		elementB6.should(Condition.text("Jean Coleman"));
	}

	@Test
	public void testName7() {
		SelenideElement elementLink7 = $("#owners > tbody > tr:nth-child(7) > td:nth-child(1) > a");
		elementLink7.should(Condition.exist);
		elementLink7.should(Condition.visible);
		elementLink7.click();
		String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(domain + "/7", currentURL);
		SelenideElement elementB7 = $(
				"body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(1) > td > b");
		elementB7.should(Condition.visible);
		elementB7.should(Condition.exist);
		elementB7.should(Condition.text("Jeff Black"));
	}

	@Test
	public void testName8() {
		SelenideElement elementLink8 = $("#owners > tbody > tr:nth-child(8) > td:nth-child(1) > a");
		elementLink8.should(Condition.exist);
		elementLink8.should(Condition.visible);
		elementLink8.click();
		String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(domain + "/8", currentURL);
		SelenideElement elementB8 = $(
				"body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(1) > td > b");
		elementB8.should(Condition.visible);
		elementB8.should(Condition.exist);
		elementB8.should(Condition.text("Maria Escobito"));
	}

	@Test
	public void testName9() {
		SelenideElement elementLink9 = $("#owners > tbody > tr:nth-child(9) > td:nth-child(1) > a");
		elementLink9.should(Condition.exist);
		elementLink9.should(Condition.visible);
		elementLink9.click();
		String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(domain + "/9", currentURL);
		SelenideElement elementB9 = $(
				"body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(1) > td > b");
		elementB9.should(Condition.visible);
		elementB9.should(Condition.exist);
		elementB9.should(Condition.text("David Schroeder"));
	}

	@Test
	public void testName10() {
		SelenideElement elementLink10 = $("#owners > tbody > tr:nth-child(10) > td:nth-child(1) > a");
		elementLink10.should(Condition.exist);
		elementLink10.should(Condition.visible);
		elementLink10.click();
		String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(domain + "/10", currentURL);
		SelenideElement elementB10 = $(
				"body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(1) > td > b");
		elementB10.should(Condition.visible);
		elementB10.should(Condition.exist);
		elementB10.should(Condition.text("Carlos Estaban"));
	}

}
