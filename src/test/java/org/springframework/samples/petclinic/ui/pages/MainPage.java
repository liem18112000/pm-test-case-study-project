package org.springframework.samples.petclinic.ui.pages;

import com.codeborne.selenide.Condition;;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage {

	private final String domain = "http://localhost:8080";

	List<SelenideElement> elementList = new ArrayList<SelenideElement>();

	public MainPage() {
		this.elementList.add($("#main-navbar > ul > li.active"));
		this.elementList.add($("#main-navbar > ul > li:nth-child(2)"));
		this.elementList.add($("#main-navbar > ul > li:nth-child(3)"));
		this.elementList.add($("#main-navbar > ul > li:nth-child(4)"));
		this.openPage();
	}

	@Test
	public void openPage() {
		open(this.domain);
	}

	@Test
	public void elementsExist() {
		this.elementList.forEach(element -> {
			element.should(Condition.exist);
		});
	}

	@Test
	public void elementsVisible() {
		this.elementList.forEach(element -> {
			element.shouldBe(Condition.visible);
		});
	}

	@Test
	public void homeTabRedirect() {
		this.elementList.get(0).click();
		String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(this.domain + "/", currentUrl);
	}

	@Test
	public void findTabRedirect() {
		this.elementList.get(1).click();
		String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(this.domain + "/owners/find", currentUrl);
	}

	@Test
	public void vetTabRedirect() {
		this.elementList.get(2).click();
		String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(this.domain + "/vets.html", currentUrl);
	}

	@Test
	public void errorTabRedirect() {
		this.elementList.get(3).click();
		String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
		assertEquals(this.domain + "/oups", currentUrl);
	}

}
