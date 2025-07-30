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
14. CSRF
> Cross-Site Request Forgery is an security attack.
> ```xhtml
> <html>
>   <form action="http://targetsite.com/account/update" method="post">
>       <input type="text" name="email" value="evil@evil.com" />
>       <input type="submit" id="submit" />
>   </form>
>   <script>
>       document.getElementById("document").click();
>   </script>
> </html>
> ```
> When user send a GET request to `evil.com`, the attacker send that form to victim.
> Browser redirects to address `http://targetsite.com/account/update` with a email value and the victim's emil is uopdated.
> CSRF token can be used to prevent the attack.
> Web application sends a token and check the existance of token for each `POST`, `PUT`, `DELETE` requests.

### OAuth2 Theory
##### 1. Terminology
> ###### Resource Owner
> Real owner of the data or service.
> Consider `Google Photos`, as a end user we are the real owner of the photo.
> ###### Client
> Third party app which need to use our resource to give us extra service.
> For example it can be a `Photo Editor` app which need to use our photos.
> ###### Resource Server
> Server which contains our data and service. In our example Server which contains Google Photo app.
> When we need to reach our photos we need send a request to that server.
> The client need to do same action when it needs our photos.
> ###### Auth Server
> Server which store our(we are Resource Owner) credentials If we want to reach our resource first we need to access that server to get access token. After that we can use application which use that server a Auth Server.
> ###### Scope
> It corresponds to the concept of `authorities` inside Spring Security.
> When we need a token we need to tell the server what we want to do with that token.
> In the example of `Photo Editor` reading the photos is the scope.
##### 1. Authorization Code Flow
> ###### Step 1
> Request sent to the Auth Server by Resource Owner. Request includes:
> - `response_type=code` Indicates that the server needs to response a `authorization code`.
> - `client_id={client_id}` Point to the Client in our example it is the `Photo Editor` app.
> Photo Editor app register itself to the Auth Server once in a lifetime and Auth Server gives it to the ClientID and ClientSecret.
> - `redirect_uri={client_address}` auth server redirect to client's after process that request.
> - `scope=photo+offline_access}` indicates that what resource owner wants the client does.
> - `state={code_against_csrf}` `Client` generates it and send it to the Auth Server when Auth Server redirects to the Client again it add that code to the request so Client knows it's the own request.
> ###### Step 2
> Auth Server redirects to the Client with a request and add request parameters. `?state=qvkEJcF3SvmIGqb8&code=-gSCJWIXgohlxIaGw6KyFMD7cA0eGjavPnU-JhYl25RTi0PR`
> - `State` used by client to check it's the own request.
> - `Code` is the authorization code. With that code Auth Server knows that the Client can request a access token to access resource which Resource Owner wants to Client access. 
> ###### Step 3
> Client wants a token from Auth Server with a POST request.
> - `grant_type=authorization_code` indicated the flow.
> - `client_id=SPT5b6oDVqmwjvkMbuNMVBEa` indicates the client. `Photo Editor`.
> - `client_secret=msEM9e-Et9fMUPOtVQXGXBqFCwd-OMy2x5c1i9ZppFkaql2-` Client prove itself to the auth server.
> - `redirect_uri=https://www.oauth.com/playground/authorization-code.html` auth server redirects to that address after process post request.
> - `code=-gSCJWIXgohlxIaGw6KyFMD7cA0eGjavPnU-JhYl25RTi0PR` Authorization Code which generated by Auth server at the request of Resource Owner. Includes scopes.
> ###### Step 4
> token end point responses to the Client:
> {
"token_type": "Bearer",
"expires_in": 86400,
"access_token": "J6q-wAZKSAFF4q_Mgz-bViLT9LQp1nIqPHxONTle66JaxCZTZWliUkHfQyvc8dAjhFyg8_V4",
"scope": "photo offline_access",
"refresh_token": "fAhatIytgW2eGG24gMdCUunl"
}
> Client can use access_token to reach resource which belongs to the Resource Owner.

### Practice
1. TLS configuration on localhost
2. HttpBasic authentication
3. FormLogin authentication
4. ExceptionHandling configuration
5. Custom **UserDetailsService**
6. Custom **AuthenticationProvider**
7. Custom **AuthenticationEntryPoint**
8. Custom **AccessDeniedHandler**
9. JwtToken based authentication implementation
10. Social Login With Google Oauth2 Server
11. Using Keycloak as Authorization Server

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
##### Running the JwtToken Application

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
cd SpringSecurityTutorial/easybank-api

# Run the project
mvn spring-boot:run
```

##### Running the KeyCloak Application
```bash
# Run a KeyCloak container
docker run -d -p 127.0.0.1:3000:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:26.3.2 start-dev

# Clone the repository
git clone https://github.com/cd0b/SpringSecurityTutorial
cd SpringSecurityTutorial/UsingKeyCloak

# Run the project
mvn spring-boot:run
```