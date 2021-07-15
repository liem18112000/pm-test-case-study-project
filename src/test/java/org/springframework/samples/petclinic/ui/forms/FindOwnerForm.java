package org.springframework.samples.petclinic.ui.forms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindOwnerForm {

	private final SelenideElement lastNameInput = $("#lastName");

	private final SelenideElement btnButton = $("#search-owner-form > div.form-group > div > button");

	private final String domain = "http://localhost:8080";

	@BeforeEach
	public void openPage() {
		open(this.domain + "/owners/find");
	}

	@Test
	public void lastNameInputExist() {
		this.lastNameInput.shouldBe(Condition.exist);
	}

	@Test
	public void submitButtonExist() {
		this.btnButton.shouldBe(Condition.exist);
	}

	@Test
	public void firstFormTest() {
		this.lastNameInput.sendKeys("Franklin");
		this.btnButton.pressEnter();

		$("body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(1) > td")
				.shouldHave(Condition.text("George Franklin"));

		assertEquals(this.domain + "/owners/1", WebDriverRunner.getWebDriver().getCurrentUrl());
	}

	@Test
	public void secondFormTest() {
		this.lastNameInput.sendKeys("Davis");
		this.btnButton.pressEnter();
		SelenideElement link1 = $("#owners > tbody > tr:nth-child(1) > td:nth-child(1) > a");
		SelenideElement link2 = $("#owners > tbody > tr:nth-child(2) > td:nth-child(1) > a");

		link1.shouldHave(Condition.text("Betty Davis"));
		link2.shouldHave(Condition.text("Harold Davis"));

		link1.click();
		assertEquals(this.domain + "/owners/2", WebDriverRunner.getWebDriver().getCurrentUrl());
	}

	@Test
	public void thirdFormTest() {
		SelenideElement elementP = $("#lastNameGroup > div > span > div > p");

		this.lastNameInput.sendKeys("abcxyz123");
		elementP.shouldNot(Condition.exist);

		this.btnButton.pressEnter();

		elementP.should(Condition.exist);
		elementP.should(Condition.visible);
		elementP.should(Condition.text("has not been found"));
	}

}
