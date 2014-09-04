package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;

import views.html.*;

public class Application extends Controller {

	static Form<Task> taskForm = Form.form(Task.class);

    public static Result index() {
        return redirect(routes.Application.toDoList());
    }

    public static Result toDoList() {
    	return ok(views.html.index.render(taskForm, Task.getAll()));
    }

    public static Result addItem() {
    	Form<Task> filledForm = taskForm.bindFromRequest();
    	if(filledForm.hasErrors()) {
    		return badRequest(
    			views.html.index.render(filledForm, Task.getAll())
    			);
    	} else {
    		Task.create(filledForm.get());
    		return redirect(routes.Application.toDoList());
    	}
    }

    public static Result deleteItem(Long id) {
    	Task.delete(id);
    	return redirect(routes.Application.toDoList());
    }

}
