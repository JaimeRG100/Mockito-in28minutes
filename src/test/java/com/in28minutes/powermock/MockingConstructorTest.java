package com.in28minutes.powermock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemUnderTest.class)
public class MockingConstructorTest {
	
	// MOCK A CONSTRUCTOR -----------------------------------------
	// Step 1.- Prepare for Test => SystemUnderTest.class (not the ArrayList.class)
	//      @PrepareForTest(SystemUnderTest.class)
	// Step 2.- Override the constructor
	//      PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);
	
	@InjectMocks
	private SystemUnderTest systemUnderTest;
	
	@Mock
	ArrayList mockList;

	@Test
	public void powerMockito_CallingAPrivateMethod() throws Exception {
		
		List<Integer> stats = Arrays.asList(1, 2, 3);
		
		when(mockList.size()).thenReturn(5);
		
		PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);
		
		int size = systemUnderTest.methodUsingAnArrayListConstructor();
		
		assertEquals(5, size);	
	}	
	
}
