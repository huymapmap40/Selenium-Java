package test_cases.UI;

import org.testng.annotations.Test;
import page_objects.*;
import test_cases.TestSetup;
import utilities.Constant;
import wrappers.BrowserWrapper;
import data_objects.Customer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GuRuTest extends TestSetup {

    // Declare parameters
    private String accessEmail = "huymapmap40@gmail.com";
    private String userId = "";
    private String userPasswd = "";
    BrowserWrapper browser = new BrowserWrapper();
    private Customer customer;
    private String customerName = "Test name";
    private String customerGender = "male";
    private String customerBirthDay = "01031994";
    private String customerAddress = "Test Address";
    private String customerCity = "ho chi minh";
    private String customerState = "StateABCXYZ";
    private String customerPin = "123456";
    private String customerPhone = "654321";
    private String customerEmail = "huymapmap@gmail.com";
    private String customerPasswd = "123456";
    private String messageCustomerAdded = "Customer Registered Successfully!!!";
    private String messageAccountCreated = "Account Generated Successfully!!!";
    private String messageDepositTransaction = "Transaction details of Deposit for Account";
    private String customerId;
    private String accountId;
    private int currentAmount;
    private String accountType = "Savings";
    private int depositInit = 1000;
    private int extraDeposit = 2000;
    private String descDeposit = "Add";

    // Declare page object
    private ManagerPage managerPage;
    private NewCustomerPage newCustomerPage;
    private NewAccountPage newAccountPage;
    private DepositPage depositPage;
    private DeleteAccountPage deleteAccountPage;
    private DeleteCustomerPage deleteCustomerPage;

    @Test
    public void GuRuTestFunctional() {
        System.out.println("Demo test GuRu 99 pages");

        GuRuTest.setupTest(Constant.urlPage);
        FillEmailGuruPage fillEmailGuruPage = FillEmailGuruPage.getInstance();

        // Fill an email and get userID/password
        fillEmailGuruPage.fillEmailAndSubmit(accessEmail);
        userId  = fillEmailGuruPage.getUserName();
        userPasswd = fillEmailGuruPage.getPassword();
        System.out.println(userId + "/" + userPasswd);

        // Login to manager page with valid userID/password
        browser.get(Constant.urlLoginPage);
        LoginPage loginPage = LoginPage.getInstance();
        managerPage = loginPage.loginToManagerPage(userId, userPasswd);

        // Add new customer
        newCustomerPage = managerPage.gotoNewCustomerPage();
        customer = new Customer(customerName,customerBirthDay,customerAddress,customerCity,customerState,customerPin,customerPhone, customerEmail,customerPasswd);
        newCustomerPage.addNewCustomer(customer);
        customerId = newCustomerPage.getCustomerId();
        System.out.println("Customer ID--->"+customerId);

        // VP: New customer is added
        assertTrue(newCustomerPage.isSuccessCustomerCreationDisplayed());
        assertEquals(newCustomerPage.getCustomerName(), customerName);
        assertEquals(newCustomerPage.getCustomerGender(), customerGender);
        assertEquals(newCustomerPage.getCustomerAddress(), customerAddress);
        assertEquals(newCustomerPage.getCustomerCity(), customerCity);
        assertEquals(newCustomerPage.getCustomerState(), customerState);
        assertEquals(newCustomerPage.getCustomerEmail(), customerEmail);
        assertEquals(newCustomerPage.getCustomerPin(), customerPin);
        assertEquals(newCustomerPage.getCustomerMobileNo(), customerPhone);
//        assertEquals(newCustomerPage.getCustomerBrithDate(), customerBirthDay);
        assertEquals(newCustomerPage.getSuccessCreationLabel(), messageCustomerAdded);

        // Add new account
        newAccountPage = managerPage.gotoNewAccountPage();
        newAccountPage.addNewAccount(customerId, accountType, depositInit);
        accountId = newAccountPage.getAccountID();
        currentAmount = newAccountPage.getCurrentAmount();
        System.out.println("Account ID--->"+accountId);

        // VP: New account is added
        assertTrue(newAccountPage.isSuccessAccountMessageDisplayed());
        assertEquals(newAccountPage.getSuccessAccountMessage(), messageAccountCreated);
        assertEquals(newAccountPage.getCustomerName(), customerName);
        assertEquals(newAccountPage.getAccountType(), accountType);
        assertEquals(newAccountPage.getEmail(), customerEmail);
        assertEquals(currentAmount, depositInit);
        assertEquals(newAccountPage.getCustomerID(), customerId);

        // Add new deposit
        depositPage = managerPage.gotoDepositPage();
        depositPage.addNewDeposit(accountId, extraDeposit, descDeposit);

        // VP: New deposit is added
        assertTrue(depositPage.isSuccessDepositMessageDisplayed());
        assertEquals(depositPage.getSuccessDepositMessage(), messageDepositTransaction + " " + accountId);
        assertEquals(depositPage.getAccountID(), accountId);
        assertEquals(depositPage.getAmountCredited(), Integer.toString(extraDeposit));
        assertEquals(depositPage.getTransactionType(), "Deposit");
        assertEquals(depositPage.getDescription(), descDeposit);
        assertEquals(depositPage.getCurrentBalance(), Integer.toString(currentAmount + extraDeposit));

        // CleanUp
        deleteAccountPage = managerPage.gotoDeleteAccounttPage();
        deleteAccountPage.deleteAccount(accountId);
        deleteCustomerPage = managerPage.gotoDeleteCustomerPage();
        deleteCustomerPage.deleteCustomer(customerId);
        GuRuTest.cleanUpTest();
    }
}
