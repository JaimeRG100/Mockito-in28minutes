package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.in28minutes.data.api.TodoService;
import com.in28minutes.data.business.TodoBusinessImpl;

public class TodoBusinessImplMockTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		
		// mock dependency
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		List<String> filteredTodos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_withEMptyList() {
		
		
		// mock dependency
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList();
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		List<String> filteredTodos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(0, filteredTodos.size());
	}
	
	
	// BDDMockito example
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {
		
		
		// Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		//when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		// When
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		// Then
		//assertEquals(2, filteredTodos.size());
		assertThat(filteredTodos.size(), is(2));
	}
	
	
	// verify: checks if some method is called
	
	@Test
	public void testDeleteNotRelatedToSpring_usingBDD() {
		
		// Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		//when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		// Then v1
		verify(todoServiceMock).deleteTodo("Learn to Dance");
		
		verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
		verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
		verify(todoServiceMock, atLeast(1)).deleteTodo("Learn to Dance");
		
		verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
		verify(todoServiceMock, never()).deleteTodo("Learn Spring");
		
		// Then v2
		then(todoServiceMock).should().deleteTodo("Learn to Dance");
		
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
		
	}
	
	
	@Test
	public void testDeleteNotRelatedToSpring_usingBDD_argumentCapture() {
		
		// --> Declare Argument Captor
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		// Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		//when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		// Then
		// --> Define Argument captor on specific method call
		then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
		//then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());  // (for when the method is called twice)
		
		// --> Capture the argument
		assertThat(stringArgumentCaptor.getValue(),is("Learn to Dance"));
		
		//assertThat(stringArgumentCaptor.getAllValues().size(),is(2));  (for when the method is called twice)
		
	}

}
