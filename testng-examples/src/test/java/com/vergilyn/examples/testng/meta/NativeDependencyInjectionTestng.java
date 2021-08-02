package com.vergilyn.examples.testng.meta;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlTest;

/**
 * <a href="http://testng.org/doc/documentation-main.html#native-dependency-injection">native-dependency-injection</a>: <br/>
 * <pre>
 * | Annotation   | {@linkplain ITestContext} | {@linkplain XmlTest} | {@linkplain Method} | Object[] | {@linkplain ITestResult} |
 * |:-------------|:-------------|:--------|:-------|:---------|:------------|
 * | {@linkplain BeforeSuite}  | Yes          |         |        |          |             |
 * | {@linkplain BeforeTest}   | Yes          | Yes     |        |          |             |
 * | {@linkplain BeforeGroups} | Yes          | Yes     |        |          |             |
 * | {@linkplain BeforeClass}  | Yes          | Yes     |        |          |             |
 * | {@linkplain BeforeMethod} | Yes          | Yes     | Yes    | Yes      | Yes         |
 * | {@linkplain Test}         | Yes          |         |        |          |             |
 * | {@linkplain DataProvider} | Yes          |         | Yes    |          |             |
 * | {@linkplain AfterMethod}  | Yes          | Yes     | Yes    | Yes      | Yes         |
 * | {@linkplain AfterClass}   | Yes          | Yes     |        |          |             |
 * | {@linkplain AfterGroups}  | Yes          | Yes     |        |          |             |
 * | {@linkplain AfterTest}    | Yes          | Yes     |        |          |             |
 * | {@linkplain AfterSuite}   | Yes          |         |        |          |             |
 * </pre>
 *
 * @author vergilyn
 * @since 2021-08-02
 *
 */
public class NativeDependencyInjectionTestng {

	@BeforeClass
	public void beforeClass(ITestContext testContext, XmlTest xmlTest){
		TestRunner testRunner = (TestRunner) testContext;

		// TODO 2021-08-02 区别？更建议使用哪种方式？
		Collection<ITestClass> testClasses = testRunner.getTestClasses();
		System.out.printf("beforeClass[TestRunner] >>>> test-class-name: %s \n",
				IterableUtils.get(testClasses, 0).getRealClass().getName());

		List<XmlClass> classes = xmlTest.getClasses();
		System.out.printf("beforeClass[XmlTest] >>>> test-class-name: %s \n",
				classes.get(0).getName());
	}

	@BeforeMethod  // The annotated method will be run before each test method.
	public void BeforeMethod(ITestContext testContext, XmlTest xmlTest, Method method, Object[] objects, ITestResult testResult){

		System.out.printf("beforeMethod >>>> method-name: %s \n", method.getName());

		Test annotation = method.getAnnotation(Test.class);
		// 如果是`test02`会是""
		System.out.printf("beforeMethod >>>> test-name: %s \n", annotation.testName());

	}

	@Test(testName = "test-name-01")
	public void test01(){
		System.out.println("execute >>>> #test01()");
	}

	@Test
	public void test02(){
		System.out.println("execute >>>> #test02()");
	}
}
