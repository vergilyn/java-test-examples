package com.vergilyn.examples.testng.dumpstack;

import java.util.concurrent.atomic.AtomicInteger;

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
import org.testng.annotations.Test;

/**
 * <a href="https://testng.org/doc/documentation-main.html#Annotations">Testng Annotations</a>:
 * <pre>
 * {@linkplain BeforeSuite}: The annotated method will be run before all tests in this suite have run.
 * {@linkplain AfterSuite}: The annotated method will be run after all tests in this suite have run.
 * {@linkplain BeforeTest}: The annotated method will be run before any test method belonging to the classes inside the <test> tag is run.
 * {@linkplain AfterTest}: The annotated method will be run after all the test methods belonging to the classes inside the <test> tag have run.
 * {@linkplain BeforeGroups}: The list of groups that this configuration method will run before. This method is guaranteed to run shortly before the first test method that belongs to any of these groups is invoked.
 * {@linkplain AfterGroups}: The list of groups that this configuration method will run after. This method is guaranteed to run shortly after the last test method that belongs to any of these groups is invoked.
 * {@linkplain BeforeClass}: The annotated method will be run before the first test method in the current class is invoked.
 * {@linkplain AfterClass}: The annotated method will be run after all the test methods in the current class have been run.
 * {@linkplain BeforeMethod}: The annotated method will be run before each test method.
 * {@linkplain AfterMethod}: The annotated method will be run after each test method.
 * </pre>
 *
 * dump-stack:
 * <pre>
 * [01] >>>> beforeTest
 * [02] >>>> beforeClass
 *
 * [03] >>>> beforeMethod
 * [04] >>>> test01
 * [05] >>>> innerMethod    // 未触发任何的 testng-aop
 * [06] >>>> afterMethod
 *
 * [07] >>>> beforeMethod
 * [08] >>>> test02
 * [09] >>>> innerMethod
 * [10] >>>> afterMethod
 *
 * [11] >>>> afterClass
 * [12] >>>> afterTest
 * </pre>
 * 特别：`@BeforeTest`在`@BeforeClass`之前，并且（以下代码写法）也只执行1次！
 *
 * @author vergilyn
 * @since 2021-08-02
 */
public class DumpStackTestng {
	private static final AtomicInteger INDEX = new AtomicInteger(0);

	@BeforeClass
	public void beforeClass(){
		sout("beforeClass");
	}

	@BeforeTest
	public void beforeTest(){
		sout("beforeTest");
	}

	@BeforeMethod
	public void beforeMethod(){
		sout("beforeMethod");
	}

	@AfterMethod
	public void afterMethod(){
		sout("afterMethod");
	}

	@AfterTest
	public void afterTest(){
		sout("afterTest");
	}

	@AfterClass
	public void afterClass(){
		sout("afterClass");
	}

	@Test
	public void test01(){
		sout("test01");
		innerMethod();
	}

	@Test
	public void test02(){
		sout("test02");
		innerMethod();
	}

	public void innerMethod(){
		sout("innerMethod");
	}

	private void sout(String str){
		System.out.printf("[%02d] >>>> %s \n", INDEX.incrementAndGet(), str);
	}
}
