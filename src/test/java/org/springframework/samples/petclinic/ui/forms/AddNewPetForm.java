package org.springframework.samples.petclinic.ui.forms;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Getter
public class AddNewPetForm {

	private final SelenideElement nameInput = $("#name");
	private final SelenideElement birthDateInput = $("#birthDate");
	private final SelenideElement typeSelect = $("#type");
	private final SelenideElement btnButton = $("body > div.container-fluid > div > form > div.form-group > div > button");
	private final ElementsCollection options = $$("#type > option");
	private final ElementsCollection helpSpan = $$("body > div.container-fluid > div > form > div.form-group.has-feedback > div.form-group.has-error > div > span.help-inline");
	private final String domain = "http://localhost:8080/owners/1/pets/new";

	@BeforeEach
	public void openPage() {
		open(this.domain);
	}

	@Test
	public void nameInputExist() {
		this.nameInput.should(Condition.exist);
		this.nameInput.should(Condition.visible);
	}

	@Test
	public void birthInputExist() {
		this.birthDateInput.should(Condition.exist);
		this.birthDateInput.should(Condition.visible);
	}

	@Test
	public void typeSelectExist() {
		this.typeSelect.should(Condition.exist);
		this.typeSelect.should(Condition.visible);
	}

	@Test
	public void optionsExist() {
		final String[] optionList = {
			"bird",
			"cat",
			"dog",
			"hamster",
			"lizard",
			"snake"
		};

		for (int i = 0; i < this.options.size(); i++) {
			final SelenideElement element = this.options.get(i);
			element.should(Condition.exist);
			element.should(Condition.visible);
			element.should(Condition.text(optionList[i]));
		}
	}

	@Test
	public void helpSpansExist() {
		this.btnButton.click();
		this.helpSpan.forEach(element -> {
			element.should(Condition.exist);
			element.should(Condition.visible);
			element.should(Condition.text("is required"));
		});
	}

	private Pet createDummyPet(){
		final Pet dummy = new Pet();
		final PetType type = new PetType();
		type.setName("bird");
		dummy.setType(type);
		dummy.setBirthDate(LocalDate.now());
		dummy.setName("Test Pet" + (new Random()).nextInt());
		return dummy;
	}

	@Test
	public void formTest1() {
		final Pet tester = this.createDummyPet();

		this.nameInput.sendKeys(tester.getName());
		this.typeSelect.selectOption(tester.getType().getName());
		this.birthDateInput.sendKeys(tester.getBirthDate().format(DateTimeFormatter.ISO_DATE));

		this.btnButton.click();

		List<SelenideElement> elementList = new ArrayList<SelenideElement>();
		elementList.add($(byText(tester.getName())));
		elementList.add($(byText(tester.getBirthDate().format(DateTimeFormatter.ISO_DATE))));
		elementList.add($(byText(tester.getType().getName())));

		elementList.forEach(element -> {
			element.should(Condition.exist);
			element.should(Condition.visible);
		});
	}



}
