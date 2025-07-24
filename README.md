## Spring Security Practices
This repository is not a complete project. It is a personal learning space for experimenting with **Spring Security**.

### Theory
1. What is Authentication?
> The answer to the question “Who are you?”.
> The system checks whether a user exists with the given credentials.
2. DelegatingFilterProxy
> A `DelegatingFilterProxy` is registered with the servlet container.
> When a request arrives, the servlet container invokes this proxy.
> It retrieves the `FilterChainProxy` bean from the `ApplicationContext` and calls its `doFilter` method.
3. FilterChainProxy
> Chooses the appropriate `SecurityFilterChain` based on the incoming request
> and executes the filters within that chain via their `doFilter` methods.
4. SecurityFilterChain
> Contains the actual security filters.
> It can be configured to match specific security needs.
> For example, if a `SecurityFilterChain` is configured with `httpBasic`,it will include the `BasicAuthenticationFilter`.
> If the request matches this chain, the filter attempts to authenticate it.
5. ProviderManager(AuthenticationManager)
> An AuthenticationFilter implementation delegates `Authentication` object to ProviderManager for authentication.
> Delegates the incoming `Authentication` object to the appropriate `AuthenticationProvider`.
> The provider uses a `UserDetailsService` (or `UserDetailsManager`) and `PasswordEncoder` to validate the credentials,
> and returns an authenticated (or throws an AuthenticationException) `Authentication` object.
6. AuthenticationProviders
> Authenticate the given `Authentication` object using a `UserDetailsService` and a `PasswordEncoder`.
7. UserDetailsService
> Defines the method `loadUserByUsername(String username)` which returns a `UserDetails` object based on the provided username.
8. UserDetailsManager
> Extends `UserDetailsService` and provides additional methods for creating, updating, and deleting users.
9. PasswordEncoder
> Applies hashing to the password, generates salt and compares passwords.
10. What is session?
> Cached information used to identify a user.
> The server generates a cookie and sends it to the client.
> On subsequent requests, the browser sends the cookie back, allowing the server to recognize the user.
> Server uses that cookie to identify and retrieve the associated user session.
> Sessions are temporary and expire after a set duration.
11. How TLS works?
> A bit complex. As I understand it:
> - The client sends a handshake "hello" message.
> - The server responds with its `public key`.
> - The client generates a pre-master key, encrypts it using the `public key`, and sends it back.
> - The server decrypts it with its `private key` and obtains the pre-master key.
> - Both sides then derive the same symmetric `master key` from the pre-master key.
> - All further communication is encrypted symmetrically for performance.
> - Optionally, the server can generate a `Session Ticket` for resuming sessions without generating a new key on each request.
12. SecurityContextHolder and SecurityContext
> The `SecurityContext` holds the current `Authentication` object. `SecurityContextHolder` stores the context in a `ThreadLocal` variable, making it accessible throughout the execution of the thread.
> - This is the default behavior.
> - For async operations, `SecurityContextHolder` can be configured to use `InheritableThreadLocal`, so child threads inherit the parent’s context.
> - A global strategy also exists but it is rarely useful I guess.
13. CORS
> Cross-Origin Resource Sharing is a precautionary mechanism implementd by the browser.
> `Origin` consists of protocol, domain and port insformation.
> `https://domain1.com:111` and `https://domain2.com:111` are different origins.
> `https://domain.com:111` and `https://domain.com:222` are different origins.
> `http://domain.com:111` and `https://domain.com:111` are different origins.
> If browser dedects that an origin request sent to another origin browser first send a `preflight request` to target resource.
> If target respond with a response which includes `Access-Control-*` headers browser decide whether to send the actual request.
> We can use `.cors()` method on HttpSecurity object to configure cors in Spring Boot.

### Practice
1. TLS configuration on localhost
2. HttpBasic authentication
3. FormLogin authentication
4. ExceptionHandling configuration
5. Custom **UserDetailsService**
6. Custom **AuthenticationProvider**
7. Custom **AuthenticationEntryPoint**
8. Custom **AccessDeniedHandler**

### Followed Courses and Documents
- [Spring Security Zero to Master (Udemy)](https://www.udemy.com/course/spring-security-zero-to-master)
- [Spring Boot ile Rest API Geliştirme (BackendGuru)](https://backendguru.com/egitimlerimiz/spring-boot-ile-rest-api-gelistirme-egitimi)
- [Spring Security Official Documentation](https://docs.spring.io/spring-security/reference/servlet/architecture.html)
- ChatGPT and DeepSeek :smile:

### Getting Started
### Requirements
- Java 21
- Maven
- Postman
- Docker

### Running the Project

```bash
# Run a PostgreSQL container
docker run --name easybankapi-postgres \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres \
-e POSTGRES_DB=easybankapidb \
-p 5432:5432 \
-d postgres

# Clone the repository
git clone https://github.com/cd0b/SpringSecurityTutorial
cd SpringSecurityTutorial

# Run the project
mvn spring-boot:run