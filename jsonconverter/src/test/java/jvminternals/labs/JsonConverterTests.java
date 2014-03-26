package jvminternals.labs;

import static org.junit.Assert.*;

import org.junit.Test;

class TestClass {
	public int SomeFieldInt=1;
	public double SomeFieldDoub=1.25;
	public Integer SomeFieldInteger=new Integer("12");
	public Double SomeFieldDouble=new Double("12.22");
	public String SomeFieldString="SomeString";
	public char SomeFieldChar='a';
	public boolean SomeFieldBoolean=true;
	public String[]values = new String[]{"A","B","C"};
	public TestClass1 testClass1 = new TestClass1();
	public TestClass() { }
}

class TestClass1{
	public int SomeFieldInt1=1;
	public double SomeFieldDoub1=1.25;
	public TestClass2 testClass2 = new TestClass2();
	public TestClass1() { }
}

class TestClass2{
	public int SomeFieldInt2=1;
	public double SomeFieldDoub2=1.25;
	public TestClass2() { }
	
}

public class JsonConverterTests {

	public void testFromJson() throws JsonConverterException {
		JsonConverterInterface json = new JsonConverter();
		TestClass tc = json.fromJson("SomeField: 1", TestClass.class);
		assertNotNull(tc);
	}

	@Test
	public void testToJson() throws JsonConverterException {
		JsonConverterInterface json = new JsonConverter();
		TestClass tC = new TestClass();
		String s = json.toJson(tC);
		System.out.println(s);
		assertNotNull(s);
		assertNotEquals(s,"");
		
	}
}
