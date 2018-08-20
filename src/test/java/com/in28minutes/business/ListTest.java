package com.in28minutes.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyInt;
import static org.mockito.BDDMockito.given;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void testMockListSizeMethod() {
		// Given
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		
		// When
		// Then
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
	}
	
	@Test
	public void testReturnMultipleValues() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3);
		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
	}
	
	@Test
	public void testMockListGet() {
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("result");
		assertEquals("result", listMock.get(0));
		assertEquals(null, listMock.get(1));		
	}
	
	@Test
	public void testMockListGet_usingBDD() {
		//Given
		List<String> listMock = mock(List.class);
		given(listMock.get(0)).willReturn("result");
		
		//When
		String firstElement = listMock.get(0);
		
		//Then
		assertThat(firstElement, is("result"));		
	}
	
	@Test
	public void testMockListGetAny() {
		List listMock = mock(List.class);
		when(listMock.get(anyInt())).thenReturn("result");
		assertEquals("result", listMock.get(0));
		assertEquals("result", listMock.get(1));		
	}
	
	@Test(expected=RuntimeException.class)
	public void testMockListGet_ThrowException() {
		List listMock = mock(List.class);
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
		listMock.get(0);
	}
	
	/*
	@Test(expected=RuntimeException.class)
	public void testMockListGet_ThrowException2() {
		List listMock = mock(List.class);
		when(listMock.subList(anyInt(), 5)).thenThrow(new RuntimeException("Something"));
		
		listMock.subList(0, 4);
	}
	*/

}
