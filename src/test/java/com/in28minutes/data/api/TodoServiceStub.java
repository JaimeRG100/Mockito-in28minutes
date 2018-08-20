package com.in28minutes.data.api;

import java.util.Arrays;
import java.util.List;

// Main problems with using a stub
// - Dynamic Condition
// - Service Definition}

// Stub are useful in very simple scenarios 
// Stub are very difficult to maintain, because you have to keep track of base changes
// Instead, we can use something more dynamic, called Mocks

public class TodoServiceStub implements TodoService {

	public List<String> retrieveTodos(String user) {
		return Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
	}

	public void deleteTodo(String todo) {		
	}

}
