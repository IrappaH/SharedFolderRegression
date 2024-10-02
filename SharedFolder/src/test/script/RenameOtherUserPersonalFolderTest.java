package test.script;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import cocoro.lib.uiauto.DriverFactory;
import cocoro.testng.listener.PriorityInClass;
import pages.HomePage;
import pages.LoginPage;
import pages.OtherUserPersonalFolder;
import pages.OtherUserPersonalFolderCreation;
import pages.OtherUserPersonalFolderRename;
import pages.PersonalFolder;
import tests.Autoutil;
import tests.InitialTestSetup;

public class RenameOtherUserPersonalFolderTest extends InitialTestSetup implements Autoutil {
	private OtherUserPersonalFolderCreation otheruserpersonalFolderCreation = null;
	private OtherUserPersonalFolder otherUserPersonalFolder = null;
	private OtherUserPersonalFolderRename otherUserPersonalFolderRename = null;
	private HomePage homePage = null;

	@BeforeClass(alwaysRun = true)
	public void login(ITestContext context) throws Exception {
		setupClassCommonSteps(context);
		this.config.ReadConfig(context);
		setDriver(new DriverFactory().createDriver(this.config).getDriver());
		LoginPage loginPage = goToLoginPage();
		loginPage.userNameTxtbox().sendKeys("test5.cocoro@gmail.com");
		loginPage.passwordTxtbox().sendKeys("Demo88Demo");
		homePage = loginPage.login();
		Thread.sleep(4000);
		// personalFolder.waitForControlToDisplay(getShareFolderLbl);
		otherUserPersonalFolder = homePage.gotoOtherUserPersonalFolder();
		otheruserpersonalFolderCreation = otherUserPersonalFolder.gotoOtherUserPersonalFolderCreation();
		otheruserpersonalFolderCreation.createFolder("AutoTest");
		assertEquals(otherUserPersonalFolder.firstRowName().getText(), "AutoTest");
		Thread.sleep(2000);
		String projectPath = System.getProperty("user.dir");		
		Thread.sleep(1000);		
		otherUserPersonalFolder.uploadBtn().click();
		Thread.sleep(1000);
		PersonalFolder.uploadFileWithRobot(projectPath + "\\PNGFiles\\");
		PersonalFolder.uploadFileWithRobot("Single.pdf");
		Thread.sleep(7000);
		otherUserPersonalFolder.uiControl("homeLink").click();
	}
	@BeforeMethod(alwaysRun = true)
	public void verifyFolderIsEmpty() throws Exception {
		Thread.sleep(2000);
		otherUserPersonalFolder = homePage.gotoOtherUserPersonalFolder();
	}
	@AfterMethod(alwaysRun = true)
	public void deleteAllFolders() throws Exception {
		otherUserPersonalFolder.uiControl("homeLink").click();
		Thread.sleep(2000);
	}	
	// English chars
	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(1)
	public void verifyFolderIsRenameWithEnglishSinlgeByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a folder with 84 single byte alphabet characters in the root folder.Folder should be renamed in the root folder");
		String value = Autoutil.File84[0];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.firstRowName().getText(), value);
	}

	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(2)
	public void verifyFolderIsRenameWithEnglishMultiByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a folder with 84 Multi byte alphabet characters in the root folder.Folder should be renamed in the root folder");
		String value = Autoutil.File84[1];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.firstRowName().getText(), value);
	}

	// Numeric chars
	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(3)
	public void verifyFolderIsRenameWithNumberSinlgeByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a folder with 84 single byte Number characters in the root folder.Folder should be renamed in the root folder");
		String value = Autoutil.File84[2];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.firstRowName().getText(), value);

	}

	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(4)
	public void verifyFolderIsRenameWithNumberMultiByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a folder with 84 Multi byte Number characters in the root folder.Folder should be renamed in the root folder");
		String value = Autoutil.File84[3];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.firstRowName().getText(), value);
	}

	// Katagana chars
	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(5)
	public void verifyFolderIsRenameWithKataganaSinlgeByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a folder with 84 single byte Katagana characters in the root folder.Folder should be renamed in the root folder");
		String value = Autoutil.File84[4];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.firstRowName().getText(), value);

	}	
	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(6)
	public void verifyFolderIsRenameWithKataganaMultiByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a folder with 84 Multi byte Katagana characters in the root folder.Folder should be renamed in the root folder");
		String value = Autoutil.File84[5];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.firstRowName().getText(), value);
	}
	// Hiragana

	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(7)
	public void verifyFolderIsRenameWithHiraganaMultiByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a folder with 84 Multi byte Hiragana characters in the root folder.Folder should be renamed in the root folder");
		String value = Autoutil.File84[6];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.firstRowName().getText(), value);
	}

	// Kanji chars
	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(8)
	public void verifyFolderIsRenameWithKanjiMultiByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a folder with 84 Multi byte Kanji characters in the root folder.Folder should be renamed in the root folder");
		String value = Autoutil.File84[7];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.firstRowName().getText(), value);
	}

	// Specialchars
	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(9)
	public void verifyFolderIsRenameWithSpecialcharsMultiByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a folder with 84 Multi byte Special characters in the root folder.Folder should be renamed in the root folder");
		String value = Autoutil.File84[8];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.firstRowName().getText(), value);
	}
	
	// English 85chars
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(10)
		public void verifyFolderIsRenameWithEnglishSinlgeByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a folder with 85 single byte alphabet characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[0];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}
		
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(11)
		public void verifyFolderIsRenameWithEnglishMultiByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a folder with 85 Multi byte alphabet characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[1];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}

		// Numeric chars
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(12)
		public void verifyFolderIsRenameWithNumberSinlgeByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a folder with 85 single byte Number characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[2];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();

		}

		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(13)
		public void verifyFolderIsRenameWithNumberMultiByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a folder with 85 Multi byte Number characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[3];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}

		// Katagana chars
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(14)
		public void verifyFolderIsRenameWithKataganaSinlgeByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a folder with 85 single byte Katagana characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[4];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();

		}	
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(15)
		public void verifyFolderIsRenameWithKataganaMultiByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a folder with 85 Multi byte Katagana characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[5];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}
		// Hiragana

		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(16)
		public void verifyFolderIsRenameWithHiraganaMultiByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a folder with 85 Multi byte Hiragana characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[6];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}

		// Kanji chars
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(17)
		public void verifyFolderIsRenameWithKanjiMultiByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a folder with 85 Multi byte Kanji characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[7];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}

		// Specialchars
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(18)
		public void verifyFolderIsRenameWithSpecialcharsMultiByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a folder with 85 Multi byte Special characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[8];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}
		
	
		// unsupport Specialchars
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(19)
				public void verifyFolderIsRenameWithForwardSlash() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with forward slash(/) characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[0];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(20)
				public void verifyFolderIsRenameWithBackSlash() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with Back slash('\') characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[1];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
		
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(21)
				public void verifyFolderIsRenameWithsnapshot() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with snapshot('.snapshot') characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[2];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(22)
				public void verifyFolderIsRenameWithSNAPSHOT() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with SNAPSHOT('.SNAPSHOT') characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[3];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(23)
				public void verifyFolderIsRenameWithsnapshots() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with snapshots('.snapshots') characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[4];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(24)
				public void verifyFolderIsRenameWithSNAPSHOTS() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with SNAPSHOTS('.SNAPSHOTS') characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[5];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(25)
				public void verifyFolderIsRenameWithSnapshot() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with Snapshot('.Snapshot') characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[6];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(26)
				public void verifyFolderIsRenameWithCON() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with CON characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[7];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(27)
				public void verifyFolderIsRenameWithPRN() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with PRN characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[8];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(28)
				public void verifyFolderIsRenameWithAUX() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with AUX characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[9];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(29)
				public void verifyFolderIsRenameWithNUL() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with NUL characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[10];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(30)
				public void verifyFolderIsRenameWithCLOCK$() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with CLOCK$ characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[11];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(31)
				public void verifyFolderIsRenameWithCOM0() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with COM0 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[12];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(32)
				public void verifyFolderIsRenameWithCOM1() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with COM1 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[13];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(33)
				public void verifyFolderIsRenameWithCOM2() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with COM2 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[14];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(34)
				public void verifyFolderIsRenameWithCOM3() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with COM3 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[15];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(35)
				public void verifyFolderIsRenameWithCOM4() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with COM4 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[16];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(36)
				public void verifyFolderIsRenameWithCOM5() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with COM5 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[17];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(37)
				public void verifyFolderIsRenameWithCOM6() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with COM6 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[18];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(38)
				public void verifyFolderIsRenameWithCOM7() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with COM7 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[19];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(39)
				public void verifyFolderIsRenameWithCOM8() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with COM8 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[20];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(40)
				public void verifyFolderIsRenameWithCOM9() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with COM9 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[21];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(41)
				public void verifyFolderIsRenameWithLPT0() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with LPT0 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[22];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(42)
				public void verifyFolderIsRenameWithLPT1() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with LPT1 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[23];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(43)
				public void verifyFolderIsRenameWithLPT2() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with LPT2 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[24];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(44)
				public void verifyFolderIsRenameWithLPT3() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with LPT3 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[25];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(45)
				public void verifyFolderIsRenameWithLPT4() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with LPT4 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[26];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(46)
				public void verifyFolderIsRenameWithLPT5() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with LPT5 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[27];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(47)
				public void verifyFolderIsRenameWithLPT6() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with LPT6 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[28];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(48)
				public void verifyFolderIsRenameWithLPT7() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with LPT7 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[29];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(49)
				public void verifyFolderIsRenameWithLPT8() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with LPT8 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[30];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(50)
				public void verifyFolderIsRenameWithLPT9() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with LPT9 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[31];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(51)
				public void verifyFolderIsRenameWithcon() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with con characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[32];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(52)
				public void verifyFolderIsRenameWithprn() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with prn characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[33];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(53)
				public void verifyFolderIsRenameWithaux() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with aux characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[34];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(54)
				public void verifyFolderIsRenameWithnul() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with nul characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[35];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(55)
				public void verifyFolderIsRenameWithclock$() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with clock$ characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[36];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				// start cp apste
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(56)
				public void verifyFolderIsRenameWithcom0() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with com0 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[37];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(57)
				public void verifyFolderIsRenameWithcom1() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with com1 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[38];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(58)
				public void verifyFolderIsRenameWithcom2() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with com2 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[39];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(59)
				public void verifyFolderIsRenameWithcom3() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with com3 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[40];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(60)
				public void verifyFolderIsRenameWithcom4() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with com4 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[41];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(61)
				public void verifyFolderIsRenameWithcom5() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with com5 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[42];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(62)
				public void verifyFolderIsRenameWithcom6() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with com6 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[43];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(63)
				public void verifyFolderIsRenameWithcom7() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with com7 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[44];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(64)
				public void verifyFolderIsRenameWithcom8() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with com8 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[45];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(65)
				public void verifyFolderIsRenameWithcom9() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with com9 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[46];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(66)
				public void verifyFolderIsRenameWithlpt0() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with lpt0 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[47];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(67)
				public void verifyFolderIsRenameWithlpt1() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with lpt1 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[48];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(68)
				public void verifyFolderIsRenameWithlpt2() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with lpt2 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[49];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(69)
				public void verifyFolderIsRenameWithlpt3() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with lpt3 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[50];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(70)
				public void verifyFolderIsRenameWithlpt4() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with lpt4 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[51];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(71)
				public void verifyFolderIsRenameWithlpt5() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with lpt5 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[52];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(72)
				public void verifyFolderIsRenameWithlpt6() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with lpt6 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[53];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(73)
				public void verifyFolderIsRenameWithlpt7() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with lpt7 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[54];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(74)
				public void verifyFolderIsRenameWithlpt8() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with lpt8 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[55];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(75)
				public void verifyFolderIsRenameWithlpt9() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder with lpt9 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[56];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(76)
				public void verifyFolderIsRenameEndWithDot() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder end with dot(.) characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[57];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(77)
				public void verifyFolderIsRenameEndWithSpace() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder end with space characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[58];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				

				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(78)
				public void verifyFolderIsRenameStartWithSpace() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a folder start with space characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[59];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotootherUserPersonalFolderRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				

	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(79)	
	public void verifyFileIsRenamebutton() throws Exception {
		setTestDesc(
				"Verify that Admin can check uploaded file has Rename option in file context menu");
		
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();		
		Thread.sleep(1000);
		assertTrue(otherUserPersonalFolderRename.IsDisplayedrenameOption());
		
	}
	

	// English chars
	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(80)
	public void verifyFileIsRenameWithEnglishSinlgeByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a file with 84 single byte alphabet characters in the root folder.Folder should be renamed in the root folder");
		String value = FileRename84[0];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.secondRowName().getText().substring(0, 80), value);
	}

	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(81)
	public void verifyFileIsRenameWithEnglishMultiByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a file with 84 Multi byte alphabet characters in the root folder.Folder should be renamed in the root folder");
		String value = FileRename84[1];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.secondRowName().getText().substring(0, 80), value);
	}

	// Numeric chars
	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(82)
	public void verifyFileIsRenameWithNumberSinlgeByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a file with 84 single byte Number characters in the root folder.Folder should be renamed in the root folder");
		String value = FileRename84[2];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.secondRowName().getText().substring(0, 80), value);

	}

	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(83)
	public void verifyFileIsRenameWithNumberMultiByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a file with 84 Multi byte Number characters in the root folder.Folder should be renamed in the root folder");
		String value = FileRename84[3];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.secondRowName().getText().substring(0, 80), value);
	}

	// Katagana chars
	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(84)
	public void verifyFileIsRenameWithKataganaSinlgeByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a file with 84 single byte Katagana characters in the root folder.Folder should be renamed in the root folder");
		String value = FileRename84[4];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.secondRowName().getText().substring(0, 80), value);

	}	
	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(85)
	public void verifyFileIsRenameWithKataganaMultiByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a file with 84 Multi byte Katagana characters in the root folder.Folder should be renamed in the root folder");
		String value = FileRename84[5];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.secondRowName().getText().substring(0, 80), value);
	}
	// Hiragana

	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(86)
	public void verifyFileIsRenameWithHiraganaMultiByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a file with 84 Multi byte Hiragana characters in the root folder.Folder should be renamed in the root folder");
		String value = FileRename84[6];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.secondRowName().getText().substring(0, 80), value);
	}

	// Kanji chars
	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(87)
	public void verifyFileIsRenameWithKanjiMultiByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a file with 84 Multi byte Kanji characters in the root folder.Folder should be renamed in the root folder");
		String value = FileRename84[7];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.secondRowName().getText().substring(0, 80), value);
	}

	// Specialchars
	@Test(groups = { "Rename" }, alwaysRun = true)
	@PriorityInClass(88)
	public void verifyFileIsRenameWithSpecialcharsMultiByte() throws Exception {
		setTestDesc(
				"Verify that Admin can Rename a file with 84 Multi byte Special characters in the root folder.Folder should be renamed in the root folder");
		String value = FileRename84[8];
		otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
		otherUserPersonalFolderRename.renameFolder(value);
		assertEquals(otherUserPersonalFolder.secondRowName().getText().substring(0, 80), value);
	}
	
	// English 85chars
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(89)
		public void verifyFileIsRenameWithEnglishSinlgeByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a file with 85 single byte alphabet characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[0];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}
		
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(90)
		public void verifyFileIsRenameWithEnglishMultiByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a file with 85 Multi byte alphabet characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[1];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}

		// Numeric chars
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(91)
		public void verifyFileIsRenameWithNumberSinlgeByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a file with 85 single byte Number characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[2];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();

		}

		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(92)
		public void verifyFileIsRenameWithNumberMultiByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a file with 85 Multi byte Number characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[3];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}

		// Katagana chars
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(93)
		public void verifyFileIsRenameWithKataganaSinlgeByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a file with 85 single byte Katagana characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[4];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();

		}	
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(94)
		public void verifyFileIsRenameWithKataganaMultiByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a file with 85 Multi byte Katagana characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[5];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}
		// Hiragana

		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(95)
		public void verifyFileIsRenameWithHiraganaMultiByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a file with 85 Multi byte Hiragana characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[6];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}

		// Kanji chars
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(96)
		public void verifyFileIsRenameWithKanjiMultiByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a file with 85 Multi byte Kanji characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[7];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}

		// Specialchars
		@Test(groups = { "Rename" }, alwaysRun = true)
		@PriorityInClass(97)
		public void verifyFileIsRenameWithSpecialcharsMultiByte85chars() throws Exception {
			setTestDesc(
					"Verify that Admin can Rename a file with 85 Multi byte Special characters in the root folder.Folder should be renamed in the root folder");
			String value = Autoutil.File85[8];
			otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
			String ActualMsg = otherUserPersonalFolderRename.renameFolderpathIsTooLong(value);
			String ExpectedMsg = getLocalizedMessage("renameMorethan84").trim();			
			assertEquals(ActualMsg, ExpectedMsg);
			otherUserPersonalFolderRename.ErrorOKBtn();
		}
		
	
		// unsupport Specialchars
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(98)
				public void verifyFileIsRenameWithForwardSlash() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with forward slash(/) characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[0];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(99)
				public void verifyFileIsRenameWithBackSlash() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with Back slash('\') characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[1];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
		
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(100)
				public void verifyFileIsRenameWithsnapshot() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with snapshot('.snapshot') characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[2];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(101)
				public void verifyFileIsRenameWithSNAPSHOT() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with SNAPSHOT('.SNAPSHOT') characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[3];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(102)
				public void verifyFileIsRenameWithsnapshots() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with snapshots('.snapshots') characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[4];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(103)
				public void verifyFileIsRenameWithSNAPSHOTS() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with SNAPSHOTS('.SNAPSHOTS') characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[5];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(104)
				public void verifyFileIsRenameWithSnapshot() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with Snapshot('.Snapshot') characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[6];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(105)
				public void verifyFileIsRenameWithCON() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with CON characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[7];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(106)
				public void verifyFileIsRenameWithPRN() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with PRN characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[8];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(107)
				public void verifyFileIsRenameWithAUX() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with AUX characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[9];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(108)
				public void verifyFileIsRenameWithNUL() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with NUL characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[10];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(109)
				public void verifyFileIsRenameWithCLOCK$() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with CLOCK$ characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[11];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(110)
				public void verifyFileIsRenameWithCOM0() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with COM0 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[12];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(111)
				public void verifyFileIsRenameWithCOM1() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with COM1 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[13];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(112)
				public void verifyFileIsRenameWithCOM2() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with COM2 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[14];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(113)
				public void verifyFileIsRenameWithCOM3() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with COM3 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[15];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(114)
				public void verifyFileIsRenameWithCOM4() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with COM4 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[16];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(115)
				public void verifyFileIsRenameWithCOM5() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with COM5 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[17];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(116)
				public void verifyFileIsRenameWithCOM6() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with COM6 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[18];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(117)
				public void verifyFileIsRenameWithCOM7() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with COM7 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[19];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(118)
				public void verifyFileIsRenameWithCOM8() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with COM8 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[20];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(119)
				public void verifyFileIsRenameWithCOM9() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with COM9 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[21];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(120)
				public void verifyFileIsRenameWithLPT0() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with LPT0 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[22];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(121)
				public void verifyFileIsRenameWithLPT1() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with LPT1 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[23];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(122)
				public void verifyFileIsRenameWithLPT2() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with LPT2 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[24];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(123)
				public void verifyFileIsRenameWithLPT3() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with LPT3 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[25];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(124)
				public void verifyFileIsRenameWithLPT4() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with LPT4 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[26];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(125)
				public void verifyFileIsRenameWithLPT5() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with LPT5 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[27];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(126)
				public void verifyFileIsRenameWithLPT6() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with LPT6 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[28];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(127)
				public void verifyFileIsRenameWithLPT7() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with LPT7 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[29];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(128)
				public void verifyFileIsRenameWithLPT8() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with LPT8 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[30];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(129)
				public void verifyFileIsRenameWithLPT9() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with LPT9 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[31];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(130)
				public void verifyFileIsRenameWithcon() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with con characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[32];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(131)
				public void verifyFileIsRenameWithprn() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with prn characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[33];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(132)
				public void verifyFileIsRenameWithaux() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with aux characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[34];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(133)
				public void verifyFileIsRenameWithnul() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with nul characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[35];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(134)
				public void verifyFileIsRenameWithclock$() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with clock$ characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[36];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				// start cp apste
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(135)
				public void verifyFileIsRenameWithcom0() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with com0 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[37];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(136)
				public void verifyFileIsRenameWithcom1() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with com1 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[38];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(137)
				public void verifyFileIsRenameWithcom2() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with com2 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[39];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(138)
				public void verifyFileIsRenameWithcom3() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with com3 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[40];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(139)
				public void verifyFileIsRenameWithcom4() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with com4 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[41];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(140)
				public void verifyFileIsRenameWithcom5() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with com5 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[42];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(141)
				public void verifyFileIsRenameWithcom6() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with com6 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[43];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(142)
				public void verifyFileIsRenameWithcom7() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with com7 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[44];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(143)
				public void verifyFileIsRenameWithcom8() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with com8 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[45];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(144)
				public void verifyFileIsRenameWithcom9() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with com9 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[46];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(145)
				public void verifyFileIsRenameWithlpt0() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with lpt0 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[47];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(146)
				public void verifyFileIsRenameWithlpt1() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with lpt1 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[48];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(147)
				public void verifyFileIsRenameWithlpt2() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with lpt2 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[49];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(148)
				public void verifyFileIsRenameWithlpt3() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with lpt3 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[50];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(149)
				public void verifyFileIsRenameWithlpt4() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with lpt4 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[51];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(150)
				public void verifyFileIsRenameWithlpt5() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with lpt5 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[52];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(151)
				public void verifyFileIsRenameWithlpt6() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with lpt6 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[53];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(152)
				public void verifyFileIsRenameWithlpt7() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with lpt7 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[54];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(153)
				public void verifyFileIsRenameWithlpt8() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with lpt8 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[55];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(154)
				public void verifyFileIsRenameWithlpt9() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file with lpt9 characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[56];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(155)
				public void verifyFileIsRenameEndWithDot() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file end with dot(.) characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[57];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				
				@Test(groups = { "Rename" }, enabled = false)
				@PriorityInClass(156)
				public void verifyFileIsRenameEndWithSpace() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file end with space characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[58];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}
				

				@Test(groups = { "Rename" }, alwaysRun = true)
				@PriorityInClass(157)
				public void verifyFileIsRenameStartWithSpace() throws Exception {
					setTestDesc(
							"Verify that Admin can Rename a file start with space characters in the root folder.Folder should be renamed in the root folder");
					String value = Autoutil.FileUnsupport[59];
					otherUserPersonalFolderRename = otherUserPersonalFolder.gotoOtherUserPersonalFileRename();
					String ActualMsg = otherUserPersonalFolderRename.renameFolderInvalid(value);
					String ExpectedMsg = getLocalizedMessage("renameInvalid").trim();			
					assertEquals(ActualMsg, ExpectedMsg);
					otherUserPersonalFolderRename.ErrorOKBtn();
				}	
				
}	

