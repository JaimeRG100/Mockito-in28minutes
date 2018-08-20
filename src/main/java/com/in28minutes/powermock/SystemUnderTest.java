package com.in28minutes.powermock;

import java.util.ArrayList;
import java.util.List;

interface Dependency {
	List<Integer> retrieveAllStats();
}

public class SystemUnderTest {
	private Dependency dependency;

	public int methodUsingAnArrayListConstructor() {
		ArrayList list = new ArrayList();
		return list.size();
	}

	public int methodCallingAStaticMethod() {
		//calls static method SomeClass.staticMethod
		List<Integer> stats = dependency.retrieveAllStats();
		long sum = 0;
		for (int stat : stats)
			sum += stat;
		return UtilityClass.staticMethod(sum);
	}

	// private method
	private long privateMethodUnderTest() {
		List<Integer> stats = dependency.retrieveAllStats();
		long sum = 0;
		for (int stat : stats)
			sum += stat;
		return sum;
	}
}	