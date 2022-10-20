# Security

## Spring Security Model
- Implemented using servlet filters in the background
- Methods to secure an app:
    - Declarative
    - Programmatic

### Security with servlet filters
- Used to pre/post process web requests
- Can route the web requests based on security logic

#### Declarative Security
- Define application security constraints in configuration.
    - Either Java Config
    - Or spring config using XML
- Provides seperation of concerns between application code and security

#### Programmatic Security
- Provides API for custom application coding
- Provides greater customization for specific app requirements

## Different Login Methods
- HTTP Basic Authentication
- Spring default login form
- Custom form

### Auths
- In-memory
- JDBC
- LDAP
- Custom/Pluggable
- Others

## Reference
[Spring Security Documentation](https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/htmlsingle/)