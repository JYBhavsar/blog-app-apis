### Getting Started
	Implementing Blog Application with user role base authentication

## About Project

# Technical stack using
	Spring boot
	Java17
	PostgreSQL
	Hibernate ORM


## Useful Guidance

	You can use static username and password while doing practice only CRUD like, 
	Set this values in your application.propertie:

	spring.security.user.name=admin
	spring.security.user.password=123
	spring.security.user.roles=ADMIN

# JWT Token Guidance
	while creating the token -
		1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
		2. Sign the JWT using the HS512 algorithm and secret key.
		3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1) 
		compaction of the JWT to a URL-safe string
