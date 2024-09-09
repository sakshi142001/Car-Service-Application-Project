Project Description :-

Entities Package: Designed and implemented entity classes to represent core data models, facilitating efficient data management and database interactions.
Repository Package: Created repository interfaces for CRUD operations and custom queries using Spring Data JPA, ensuring streamlined and effective data access.
Service and ServiceImpl Packages: Developed service interfaces and their implementations to handle business logic, promoting modular design and separation of concerns.
Controller Package: Built RESTful controllers to manage HTTP requests and interact with the service layer, providing a clear and responsive API interface.
Mapper Package: Implemented mapper classes to transform data between entity objects and Data Transfer Objects (DTOs), ensuring smooth data conversion and transfer.
RequestDTOs Package: Defined RequestDTOs to validate and encapsulate input data from client requests, maintaining structured and consistent data handling.
ResponseDTOs Package: Created ResponseDTOs to format and standardize responses sent to clients, enhancing the clarity and consistency of API responses.

Security Package:
JWTFilter: Implemented a custom filter to validate JSON Web Tokens (JWTs) for secure API access, ensuring that only authorized users can access protected endpoints.
JWTService: Developed a service to handle JWT generation and validation, supporting secure authentication and authorization processes.
SecurityConfig: Configured Spring Security to integrate JWT authentication, define security policies, and protect application endpoints.
UserDetailsImpl: Created a class implementing Spring Security’s UserDetails interface to manage user-specific authentication details.
UserDetailsServiceImpl: Implemented a service to load user-specific data during authentication, integrating with the security framework to support user authentication.
Exceptions Package: Designed an exception handling mechanism to manage and handle application errors, providing user-friendly and consistent error messages through custom exception classes and global exception handlers.
Password Encoding: Implemented password encoding using Spring Security to enhance security, ensuring passwords are stored and transmitted securely.
Token Management: Enabled token-based authentication, allowing users to obtain and use tokens for secure access to application resources.

API Testing with Postman: Utilized Postman to thoroughly test API endpoints, validate request and response handling, and ensure the application meets functional and performance requirements.
