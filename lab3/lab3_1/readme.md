a) Identify a couple of examples that use AssertJ expressive methods chaining.

        assertThat(found).isEqualTo(alex);
        assertThat(fromDb).isNull();
        assertThat(fromDb.getEmail()).isEqualTo(emp.getEmail());
        assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());

b) Identify an example in which you mock the behavior of the repository (and avoid involving a database). 


        @Mock( lenient = true)
        private EmployeeRepository employeeRepository;

        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());

c) What is the difference between @Mock and @MockBean?

  @Mock: (org.mockito.Mock)
  Mark a field as a mock.
  Allows shorthand mock creation.
  Minimizes repetitive mock creation code.
  Makes the test class more readable.
  Makes the verification error easier to read because the field name is used to identify the mock.

  @MockBean: (org.springframework.boot.test.mock.mockito.MockBean)
  Annotation that can be used to add mocks to a Spring ApplicationContext. Can be used as a class level annotation or on fields in either @Configuration classes, or test classes that are @RunWith the SpringRunner.
  Mocks can be registered by type or by bean name. Any existing single bean of the same type defined in the context will be replaced by the mock, if no existing bean is defined a new one will be added.
  When @MockBean is used on a field, as well as being registered in the application context, the mock will also be injected into the field.

d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

  It allows to override spring boot properties for testing 

e) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences? 
  
  In C), runs the tests in a simplified and light environment, simulating the behavior of 
  an application server, by using @WebMvcTest mode.
  Get a reference to the server context with @MockMvc. 
  To make the test more localized to the controller, you may mock the 
  dependencies on the service (@MockBean); the repository 
  component will not be involved.

  In D) and E) we start the full web context 
  (@SpringBootTest, with Web 
  Environment enabled). The API is 
  deployed into the normal SpringBoot 
  context. Uses the entry point for serverside Spring MVC test support 
  (MockMvc). while in E), it uses a REST client to create 
  realistic requests (TestRestTemplate).

