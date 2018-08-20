package com.in28minutes.powermock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class MockingStaticMethodTest {
	
	// MOCK A STATIC METHOD -------------------------------------
	//Step 1.- Specific Runner
	//         PowerMockito.mockStatic(UtilityClass.class);
	//Step 2.- Initialize a ClassHavingStaticMethods.class
	//         @PrepareForTest(UtilityClass.class)
	//Step 3.- mock
	//         when(UtilityClass.staticMethod(6)).thenReturn(150);
	
	@Mock
	private Dependency dependency;
	
	@InjectMocks
	private SystemUnderTest systemUnderTest;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		PowerMockito.mockStatic(UtilityClass.class);
		
		List<Integer> stats = Arrays.asList(1, 2, 3);
		when(dependency.retrieveAllStats()).thenReturn(stats);
		when(UtilityClass.staticMethod(6)).thenReturn(150);
		assertEquals(3, stats.size());
		
		int result = systemUnderTest.methodCallingAStaticMethod();
		assertEquals(150, result);
		
		// check that a specific method is called (different than previous examples)
		PowerMockito.verifyStatic();  // We tell PowerMockito to verify the next method is called
		UtilityClass.staticMethod(6); // if we change the parameter to other number different than 6, the test will fail
		
	}
	
	
	
	
}