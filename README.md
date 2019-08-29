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
## From Classpath
Pass the image classpath location to `ClassPathResource` constructor, 
get the image bytes by calling `StreamUtils.copyToByteArray(imageFile.getInputStream())` method 
and pass the image byte array to the `ResponseEntity` body.
```
@GetMapping(value = "classpath")
public ResponseEntity<byte[]> fromClasspathAsResEntity() throws IOException {

	ClassPathResource imageFile = new ClassPathResource("images/sasuke.jpg");

	byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());

	return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
}
```

## Using the HttpServletResponse
Serving images/media using `HttpServletResponse` is the most basic approach.  
It is the pure Servlet implementation and used from a decade.

## From Database
```
@GetMapping(value = "database1/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
public void fromDatabaseAsHttpServResp(@PathVariable("id") Integer id, HttpServletResponse response)
		throws SQLException, IOException {

	Optional<User> userImage = imageRepository.findById(id);

	if (userImage.isPresent()) {

		Blob image = userImage.get().getPhoto();

		StreamUtils.copy(image.getBinaryStream(), response.getOutputStream());
	}
}
```
## From Classpath
```
@GetMapping(value = "classpath1", produces = MediaType.IMAGE_JPEG_VALUE)
public void fromClasspathAsHttpServResp(HttpServletResponse response) throws IOException {

	ClassPathResource imageFile = new ClassPathResource("images/naruto.jpg");

	StreamUtils.copy(imageFile.getInputStream(), response.getOutputStream());
}
```
Now let’s jump to the actual implementation of Spring Boot- Display image from database and classpath.

## Things to do:
1. Clone repository: `git clone https://github.com/hendisantika/springboot-image.git`. 
2. Go to the folder: `cd springboot-image`
3. Run the app: `mvn clean spring-boot:run` 

## Test it
When the application successfully started, open your favorite web browser and hit the below URL’s:

1. From database as ResponseEntity → http://localhost:8080/image/database/{id}

2. From database as  HttpServletResponse → http://localhost:8080/image/database1/{id}

3. From classpath as ResponseEntity → http://localhost:8080/image/classpath

4. From classpath as  HttpServletResponse → http://localhost:8080/image/classpath1


> Attention: In this example, we have not written any logic to insert images into the database. Inserting an image into the database is done manually for the below table structure:
```
CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL,
  `photo` longblob,
  PRIMARY KEY (`id`)
);
```
The SQL query to insert the images:
```
INSERT INTO tbl_user(id,photo) VALUES(101,LOAD_FILE('/tmp/images/sasuke.jpg'));
```