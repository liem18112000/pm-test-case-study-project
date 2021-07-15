package org.springframework.samples.petclinic.ui.forms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.owner.Owner;

import static com.codeborne.selenide.Selenide.*;

public class AddNewOwnerForm {

	private final String domain = "http://localhost:8080/owners/new";

	private final SelenideElement firstNameInput = $("#firstName");

	private final SelenideElement lastNameInput = $("#lastName");

	private final SelenideElement addressInput = $("#address");

	private final SelenideElement cityInput = $("#city");

	private final SelenideElement telephoneInput = $("#telephone");

	private final SelenideElement btnButton = $("#add-owner-form > div.form-group > div > button");

	private final ElementsCollection helpinlineSpans = $$("#add-owner-form > div.form-group.has-feedback > div.form-group.has-error > div > span.help-inline");

	@BeforeEach
	public void openPage() {
		open(this.domain);
	}

	@Test
	public void firstNameInputExist() {
		this.firstNameInput.should(Condition.exist);
		this.firstNameInput.should(Condition.visible);
	}

	@Test
	public void lastNameInputExist() {
		this.lastNameInput.should(Condition.exist);
		this.lastNameInput.should(Condition.visible);
	}

	@Test
	public void addressInputExist() {
		this.addressInput.should(Condition.exist);
		this.addressInput.should(Condition.visible);
	}

	@Test
	public void telephoneInputExist() {
		this.telephoneInput.should(Condition.exist);
		this.telephoneInput.should(Condition.visible);
	}

	@Test
	public void cityInputExist() {
		this.cityInput.should(Condition.exist);
		this.cityInput.should(Condition.visible);
	}

	@Test
	public void helpSpansExist() {

		this.btnButton.click();

		final String[] textList = {
			"must not be empty",
			"must not be empty",
			"must not be empty",
			"must not be empty",
			"numeric value out of bounds (<10 digits>.<0 digits> expected)\n" +
				"must not be empty"
		};

		for (int i = 0; i < this.helpinlineSpans.size(); i++) {
			final SelenideElement element = this.helpinlineSpans.get(i);
			element.should(Condition.exist);
			element.should(Condition.visible);
			element.should(Condition.text(textList[i]));
		}
	}

	private Owner getDummyOwner() {
		Owner dummy = new Owner();
		dummy.setFirstName("Tester");
		dummy.setLastName("Miss");
		dummy.setCity("Tester city");
		dummy.setTelephone("123456789");
		dummy.setAddress("Some where");
		return dummy;
	}

	@Test
	public void formTest1() {
		final Owner tester = this.getDummyOwner();

		this.firstNameInput.sendKeys(tester.getFirstName());
		this.lastNameInput.sendKeys(tester.getLastName());
		this.cityInput.sendKeys(tester.getCity());
		this.telephoneInput.sendKeys(tester.getTelephone());
		this.addressInput.sendKeys(tester.getAddress());

		this.btnButton.click();

		final SelenideElement name = $("body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(1) > td > b");
		final SelenideElement address = $("body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(2) > td");
		final SelenideElement city = $("body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(3) > td");
		final SelenideElement telephone = $("body > div.container-fluid > div > table.table.table-striped > tbody > tr:nth-child(4) > td");

		name.should(Condition.text(tester.getFirstName() + " " + tester.getLastName()));
		address.should(Condition.text(tester.getAddress()));
		city.should(Condition.text(tester.getCity()));
		telephone.should(Condition.text(tester.getTelephone()));
	}

}
