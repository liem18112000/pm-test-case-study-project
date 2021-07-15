package org.springframework.samples.petclinic.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindOwnerPage {

	private final String domain = "http://localhost:8080/owners/find";

	private final String domain2 = "http://localhost:8080/owners/new";

	@BeforeEach
	public void openPage() {
		open(this.domain);
	}

	List<SelenideElement> elementList = new ArrayList<SelenideElement>();

	public FindOwnerPage() {
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

	public void testAddOwnerbtn() {
		SelenideElement btnLink = $("body > div.container-fluid > div > a");
		btnLink.should(Condition.exist);
		btnLink.should(Condition.visible);
		btnLink.click();
		String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(domain2, currentURL);

	}

}
