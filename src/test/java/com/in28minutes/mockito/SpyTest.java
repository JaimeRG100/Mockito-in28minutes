package com.in28minutes.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;

public class SpyTest {

	@Test
	public void test_withoutSpy() {
		List arrayListMock = mock(ArrayList.class);
		assertEquals(0, arrayListMock.size());
		
		// mock return default value
		stub(arrayListMock.size()).toReturn(5);
		arrayListMock.add("Dummy"); // does not affect the size mocked
		assertEquals(5, arrayListMock.size());
		
	}
	
	@Test
	public void test_withSpy() {
		List arrayListSpy = spy(ArrayList.class);
		stub(arrayListSpy.size()).toReturn(5);  // from now on size() always will return 5
		arrayListSpy.add("Dummy");
		verify(arrayListSpy).add("Dummy");
		verify(arrayListSpy, never()).clear();
		assertEquals(5, arrayListSpy.size());
	}

}
