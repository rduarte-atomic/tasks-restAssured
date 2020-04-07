package br.com.devops.tasks.api.tasks_api;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest 
{
	@BeforeClass
	public static void setup()
	{
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
	}
	
	@Test
	public void deveRetornarTarefas()
	{
		RestAssured.given()
			.when()
			.get("/todo")
		.then()
			.statusCode(200);
		;
	}
	
	@Test
	public void deveAdicionarTarefaComSucesso()
	{
		RestAssured.given()
			.body("{ \"task\": \"Teste API\", \"dueDate\": \"2020-06-23\" }")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.statusCode(201);
		;
	}
	
	@Test
	public void naoDeveAdicionarTarefaComSucesso()
	{
		RestAssured.given()
			.body("{ \"task\": \"Teste API\", \"dueDate\": \"2000-06-23\" }")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.statusCode(400)
			.body("message", CoreMatchers.is("Due date must not be in past"))
		;
	}
}
