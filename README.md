# springboot-image

Spring Boot- Display image from database and classpath

On this repository, We are going to learn how to display image from the database and classpath location using Spring Boot RESTful web service. 
Returning image/media data with Spring Boot REST application, we have multiple options:
1. Using the `ResponseEntity`
2. Using the `HttpServletResponse`

Producing an image/media using REST service, Spring Framework has `MediaType` class inside the `org.springframework.http` package. 
We can use it according to our need like `MediaType.IMAGE_JPEG` or `MediaType.IMAGE_JPEG_VALUE`.

## Using the ResponseEntity
You can return an image as `byte[]` wrapped in the `ResponseEntity`. We need to define the return type of the method as `ResponseEntity<byte[]>` and create returning ResponseEntity object in the method body.

## From Database
Use the JPA to fetch the details from the database. get the image bytes, set the content type and pass the image byte array to the `ResponseEntity` body.
```
@GetMapping("database/{id}")
public ResponseEntity<byte[]> fromDatabaseAsResEntity(@PathVariable("id") Integer id) 
        throws SQLException {

	Optional<User> userImage = imageRepository.findById(id);
	byte[] imageBytes = null;
	if (userImage.isPresent()) {
	
		imageBytes = userImage.get().getPhoto().getBytes(1,
					(int) userImage.get().getPhoto().length());
	}
	
	return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
}
```


## Things to do:
1. Clone repository: `git clone https://github.com/hendisantika/springboot-image.git`. 
2. Go to the folder: `cd springboot-image`
3. Run the app: `mvn clean spring-boot:run` 
