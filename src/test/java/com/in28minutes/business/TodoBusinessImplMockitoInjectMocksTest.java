package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.in28minutes.data.api.TodoService;
import com.in28minutes.data.business.TodoBusinessImpl;

//@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMocksTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	private TodoService todoServiceMock;
	
	@InjectMocks
	private TodoBusinessImpl todoBusinessImpl;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_withEMptyList() {
		List<String> todos = Arrays.asList();
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(0, filteredTodos.size());
	}
	
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {
		// Given
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		// When
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		// Then
		assertThat(filteredTodos.size(), is(2));
	}
	
	@Test
	public void testDeleteNotRelatedToSpring_usingBDD() {
		// Given
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
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
		// --> Declare Argument Captor (with @Captor annotation)
		// Given
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		//when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		
		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		// Then
		// --> Define Argument captor on specific method call
		then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
		
		// --> Capture the argument
		assertThat(stringArgumentCaptor.getValue(),is("Learn to Dance"));
	}

}
