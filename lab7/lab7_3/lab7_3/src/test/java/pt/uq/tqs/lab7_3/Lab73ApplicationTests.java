package pt.uq.tqs.lab7_3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import pt.uq.tqs.lab7_3.Employee;
import pt.uq.tqs.lab7_3.EmployeeRepository;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
class Lab73ApplicationTests {

	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:15-alpine3.17")
		.withUsername("duke")
		.withPassword("password")
		.withDatabaseName("test");

	@Autowired
	private EmployeeRepository employeeRepository;

	// requires Spring Boot >= 2.2.6
	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
	}

	@Test
	void contextLoads() {

		Employee emp = new Employee();
		emp.setName("Foo");
		emp.setAge(28);
		emp.setSalary(4000L);
		emp.setJobposition("Product Owner");

		employeeRepository.save(emp);
		Employee fromDb = employeeRepository.findById(1).get();

		assertThat(fromDb.getName()).isEqualTo(emp.getName());
	}
}
