package com.prime.showcase.integration.menubar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.prime.showcase.integration.AbstractIntegrationTest;

public class MenubarIntegrationTest extends AbstractIntegrationTest  {
	
	@Before
	public void before() {
		driver.get(toShowcaseUrl("menubar.jsf"));
	}
	
	@Test
	public void shouldRenderMenuBar() throws InterruptedException {
		WebElement menuBar = driver.findElement(By.className("ui-menu"));

		List<WebElement> menuItems = menuBar.findElements(By.className("ui-menuitem"));

		assertThat(menuItems.get(0).getText(),equalTo("File"));
		assertThat(menuItems.get(6).getText(),equalTo("Edit"));
		assertThat(menuItems.get(9).getText(),equalTo("Help"));
		assertThat(menuItems.get(15).getText(),equalTo("Actions"));
		assertThat(menuItems.get(21).getText(),equalTo("Quit"));
	}
	
	@Test
	public void shouldRenderSubMenu() {
		WebElement menuBar = driver.findElement(By.className("ui-menu"));

		List<WebElement> menuItems = menuBar.findElements(By.className("ui-menuitem"));
		
		WebElement editSubMenu = menuItems.get(6);
		editSubMenu.click();
		
		WebElement ulOfEditSubMenu = editSubMenu.findElement(By.tagName("ul"));
		
		assertThat(ulOfEditSubMenu.getText(),equalTo("Undo\nRedo"));
	}
	
	@Test
	public void shouldRenderSecondLevelMenu() {
		WebElement menuBar = driver.findElement(By.className("ui-menu"));

		List<WebElement> menuItems = menuBar.findElements(By.className("ui-menuitem"));
		WebElement fileMenu = menuItems.get(0);
		
		
		fileMenu.click();
		
		WebElement newMenu = fileMenu.findElement(By.tagName("ul")).findElement(By.className("ui-menuitem"));
		newMenu.click();
		
		assertThat(newMenu.findElement(By.tagName("ul")).getText(), equalTo("Project\nOther"));
	}

}
