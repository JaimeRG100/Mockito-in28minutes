package com.in28minutes.business;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.in28minutes.data.api.TodoService;
import com.in28minutes.data.api.TodoServiceStub;
import com.in28minutes.data.business.TodoBusinessImpl;

// TodoBusinessImpl SUT
// TodoService Dependency

public class TodoBusinessImplStubTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAStub() {
		TodoService todoServiceStub = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
		
		List<String> filteredTodos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAStub2() {
		TodoService todoServiceStub = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
		
		List<String> filteredTodos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Dummy1");
		assertEquals(2, filteredTodos.size());
	}

}
