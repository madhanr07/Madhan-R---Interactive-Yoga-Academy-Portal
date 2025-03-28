package com.prjgrp.artf;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;

import com.prjgrp.artf.model.User;
import com.prjgrp.artf.model.YogaClass;
import com.prjgrp.artf.repository.InstructionalVideoRepository;
import com.prjgrp.artf.repository.UserRepository;
import com.prjgrp.artf.repository.YogaClassRepository;
import com.prjgrp.artf.configuration.SwaggerConfig;
import com.prjgrp.artf.controller.InstructionalVideoController;
import com.prjgrp.artf.controller.UserController;
import com.prjgrp.artf.controller.YogaClassController;
import com.prjgrp.artf.model.InstructionalVideo;
import com.prjgrp.artf.service.UserService;
import com.prjgrp.artf.service.YogaClassService;
import com.prjgrp.artf.service.InstructionalVideoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//@WebMvcTest({YogaClassController.class, UserController.class, InstructionalVideoController.class})
// @SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringappApplicationTests {

    private static final String LOG_FOLDER_PATH = "logs";
    private static final String LOG_FILE_PATH = "logs/application.log"; 

    @Autowired
    private MockMvc mockMvc;

    @Autowired
	private InstructionalVideoRepository instructionalVideoRepository;

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private YogaClassRepository yogaClassRepository;

    @MockBean
    private YogaClassService yogaClassService;

    @MockBean
    private UserService userService;

    @MockBean
    private InstructionalVideoService instructionalVideoService;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Order(1)
    @Test
    void contextLoads() {
    }

    // Test for User entity with Lombok annotations
    @Test
    @Order(2)
    void Annotation_testUserHasLombokAnnotations() throws Exception {
        // Path to the User entity file
        Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/model/User.java");

        // Read the content of the entity file as a string
        String entityFileContent = Files.readString(entityFilePath);

        // Check if @Data annotation exists for the User entity
        assertTrue(entityFileContent.contains("@Data"), "User entity should contain @Data annotation");
    }

    // Test for YogaClass entity with Lombok annotations
    @Test
    @Order(3)
    void Annotation_testYogaClassHasLombokAnnotations() throws Exception {
        // Path to the YogaClass entity file
        Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/model/YogaClass.java");

        // Read the content of the entity file as a string
        String entityFileContent = Files.readString(entityFilePath);

        // Check if @Data annotation exists for the YogaClass entity
        assertTrue(entityFileContent.contains("@Data"), "YogaClass entity should contain @Data annotation");
    }

    // Test for InstructionalVideo entity with Lombok annotations
    @Test
    @Order(4)
    void Annotation_testInstructionalVideoHasLombokAnnotations() throws Exception {
        // Path to the InstructionalVideo entity file
        Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/model/InstructionalVideo.java");

        // Read the content of the entity file as a string
        String entityFileContent = Files.readString(entityFilePath);

        // Check if @Data annotation exists for the InstructionalVideo entity
        assertTrue(entityFileContent.contains("@Data"), "InstructionalVideo entity should contain @Data annotation");
    }

    // Test for User entity with @JsonIgnore annotations
    @Test
    @Order(5)
    void Annotation_testUserHasJSONIgnoreAnnotations() throws Exception {
        // Path to the User entity file
        Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/model/User.java");

        // Read the content of the entity file as a string
        String entityFileContent = Files.readString(entityFilePath);

        // Check if @JsonIgnore annotation exists in the User entity
        assertTrue(entityFileContent.contains("@JsonIgnore"), "User entity should contain @JsonIgnore annotation");
    }

    // Test for YogaClass entity with @JsonIgnore annotations
    @Test
    @Order(6)
    void Annotation_testYogaClassHasJSONIgnoreAnnotations() throws Exception {
        // Path to the YogaClass entity file
        Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/model/YogaClass.java");

        // Read the content of the entity file as a string
        String entityFileContent = Files.readString(entityFilePath);

        // Check if @JsonIgnore annotation exists in the YogaClass entity
        assertTrue(entityFileContent.contains("@JsonIgnore"), "YogaClass entity should contain @JsonIgnore annotation");
    }

    // Test to check if UserRepository file exists in the specified path
    @Test
    @Order(7)
    public void Repository_testUserRepositoryFile() {
        // Define the path to the UserRepository file
        String filePath = "src/main/java/com/prjgrp/artf/repository/UserRepository.java";

        // Create a File object using the specified path
        File file = new File(filePath);

        // Check if the file exists and is indeed a file
        assertTrue(file.exists() && file.isFile(), "UserRepository file should exist and be a file");
    }

    @Test
    @Order(8)
    public void Repository_testYogaClassRepositoryFile() {
        // Define the path to the YogaClassRepository file
        String filePath = "src/main/java/com/prjgrp/artf/repository/YogaClassRepository.java";

        // Create a File object using the specified path
        File file = new File(filePath);

        // Check if the file exists and is indeed a file
        assertTrue(file.exists() && file.isFile(), "YogaClassRepository file should exist and be a file");
    }

    // Test to check if InstructionalVideoRepository file exists in the specified path
    @Test
    @Order(9)
    public void Repository_testInstructionalVideoRepositoryFile() {
        // Define the path to the InstructionalVideoRepository file
        String filePath = "src/main/java/com/prjgrp/artf/repository/InstructionalVideoRepository.java";

        // Create a File object using the specified path
        File file = new File(filePath);

        // Check if the file exists and is indeed a file
        assertTrue(file.exists() && file.isFile(), "InstructionalVideoRepository file should exist and be a file");
    }
    
    
    // YogaClassController Tests


    @Test
    @Order(10)
    void CRUD_testCreateYogaClass() throws Exception {
        YogaClass yogaClass = new YogaClass();
        yogaClass.setId(1L);
        yogaClass.setName("Hatha Yoga");
        yogaClass.setDescription("A gentle introduction to yoga.");

        when(yogaClassService.createYogaClass(any(YogaClass.class))).thenReturn(yogaClass);

        mockMvc.perform(post("/api/classes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Hatha Yoga\", \"description\": \"A gentle introduction to yoga.\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Hatha Yoga"))
                .andExpect(jsonPath("$.description").value("A gentle introduction to yoga."));
    }

    @Test
    @Order(11)
    void CRUD_testGetYogaClassById() throws Exception {
        YogaClass yogaClass = new YogaClass();
        yogaClass.setId(1L);
        yogaClass.setName("Hatha Yoga");
        yogaClass.setDescription("A gentle introduction to yoga.");

        when(yogaClassService.getYogaClassById(anyLong())).thenReturn(Optional.of(yogaClass));

        mockMvc.perform(get("/api/classes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Hatha Yoga"))
                .andExpect(jsonPath("$.description").value("A gentle introduction to yoga."));
    }

    @Test
    @Order(12)
    void CRUD_testGetAllYogaClasses() throws Exception {
        YogaClass yogaClass1 = new YogaClass();
        yogaClass1.setId(1L);
        yogaClass1.setName("Hatha Yoga");
        yogaClass1.setDescription("A gentle introduction to yoga.");

        YogaClass yogaClass2 = new YogaClass();
        yogaClass2.setId(2L);
        yogaClass2.setName("Vinyasa Yoga");
        yogaClass2.setDescription("A flowing style of yoga.");

        when(yogaClassService.getAllYogaClasses()).thenReturn(Arrays.asList(yogaClass1, yogaClass2));

        mockMvc.perform(get("/api/classes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Hatha Yoga"))
                .andExpect(jsonPath("$[0].description").value("A gentle introduction to yoga."))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Vinyasa Yoga"))
                .andExpect(jsonPath("$[1].description").value("A flowing style of yoga."));
    }

    @Test
    @Order(13)
    void CRUD_testUpdateYogaClass() throws Exception {
        YogaClass yogaClass = new YogaClass();
        yogaClass.setId(1L);
        yogaClass.setName("Hatha Yoga");
        yogaClass.setDescription("A gentle introduction to yoga.");

        YogaClass updatedYogaClass = new YogaClass();
        updatedYogaClass.setId(1L);
        updatedYogaClass.setName("Advanced Hatha Yoga");
        updatedYogaClass.setDescription("An advanced level of Hatha Yoga.");

        when(yogaClassService.updateYogaClass(anyLong(), any(YogaClass.class))).thenReturn(Optional.of(updatedYogaClass));

        mockMvc.perform(put("/api/classes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Advanced Hatha Yoga\", \"description\": \"An advanced level of Hatha Yoga.\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Advanced Hatha Yoga"))
                .andExpect(jsonPath("$.description").value("An advanced level of Hatha Yoga."));
    }

    @Test
    @Order(14)
    void CRUD_testDeleteYogaClass() throws Exception {
        when(yogaClassService.deleteYogaClass(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/classes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(15)
    void CRUD_testDeleteYogaClassNotFound() throws Exception {
        when(yogaClassService.deleteYogaClass(anyLong())).thenReturn(false);

        mockMvc.perform(delete("/api/classes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // UserController Tests

    @Test
    @Order(16)
    void CRUD_testCreateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("John Doe");
        user.setEmail("john.doe@example.com");

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"John Doe\", \"email\": \"john.doe@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    @Order(17)
    void CRUD_testGetUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("John Doe");
        user.setEmail("john.doe@example.com");

        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    @Order(18)
    void CRUD_testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("John Doe");
        user1.setEmail("john.doe@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("Jane Doe");
        user2.setEmail("jane.doe@example.com");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].username").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].username").value("Jane Doe"))
                .andExpect(jsonPath("$[1].email").value("jane.doe@example.com"));
    }

    @Test
    @Order(19)
    void CRUD_testUpdateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("John Doe");
        user.setEmail("john.doe@example.com");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setUsername("John Smith");
        updatedUser.setEmail("john.smith@example.com");

        when(userService.updateUser(anyLong(), any(User.class))).thenReturn(Optional.of(updatedUser));

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"John Smith\", \"email\": \"john.smith@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("John Smith"))
                .andExpect(jsonPath("$.email").value("john.smith@example.com"));
    }

    @Test
    @Order(20)
    void CRUD_testDeleteUser() throws Exception {
        when(userService.deleteUser(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    // InstructionalVideoController Tests

    @Test
    @Order(21)
    void CRUD_testCreateInstructionalVideo() throws Exception {
        InstructionalVideo video = new InstructionalVideo();
        video.setId(1L);
        video.setTitle("Yoga for Beginners");
        video.setUrl("http://example.com/video");

        when(instructionalVideoService.createInstructionalVideo(any(InstructionalVideo.class))).thenReturn(video);

        mockMvc.perform(post("/api/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Yoga for Beginners\", \"url\": \"http://example.com/video\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Yoga for Beginners"))
                .andExpect(jsonPath("$.url").value("http://example.com/video"));
    }

    @Test
    @Order(22)
    void CRUD_testGetInstructionalVideoById() throws Exception {
        InstructionalVideo video = new InstructionalVideo();
        video.setId(1L);
        video.setTitle("Yoga for Beginners");
        video.setUrl("http://example.com/video");

        when(instructionalVideoService.getInstructionalVideoById(anyLong())).thenReturn(Optional.of(video));

        mockMvc.perform(get("/api/videos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Yoga for Beginners"))
                .andExpect(jsonPath("$.url").value("http://example.com/video"));
    }

    @Test
    @Order(23)
    void CRUD_testGetAllInstructionalVideos() throws Exception {
        InstructionalVideo video1 = new InstructionalVideo();
        video1.setId(1L);
        video1.setTitle("Yoga for Beginners");
        video1.setUrl("http://example.com/video");

        InstructionalVideo video2 = new InstructionalVideo();
        video2.setId(2L);
        video2.setTitle("Advanced Yoga");
        video2.setUrl("http://example.com/video2");

        when(instructionalVideoService.getAllInstructionalVideos()).thenReturn(Arrays.asList(video1, video2));

        mockMvc.perform(get("/api/videos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Yoga for Beginners"))
                .andExpect(jsonPath("$[0].url").value("http://example.com/video"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Advanced Yoga"))
                .andExpect(jsonPath("$[1].url").value("http://example.com/video2"));
    }

    @Test
    @Order(24)
    void CRUD_testUpdateInstructionalVideo() throws Exception {
        InstructionalVideo video = new InstructionalVideo();
        video.setId(1L);
        video.setTitle("Yoga for Beginners");
        video.setUrl("http://example.com/video");

        InstructionalVideo updatedVideo = new InstructionalVideo();
        updatedVideo.setId(1L);
        updatedVideo.setTitle("Yoga for Advanced Users");
        updatedVideo.setUrl("http://example.com/video-updated");

        when(instructionalVideoService.updateInstructionalVideo(anyLong(), any(InstructionalVideo.class))).thenReturn(Optional.of(updatedVideo));

        mockMvc.perform(put("/api/videos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Yoga for Advanced Users\", \"url\": \"http://example.com/video-updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Yoga for Advanced Users"))
                .andExpect(jsonPath("$.url").value("http://example.com/video-updated"));
    }

    @Test
    @Order(25)
    void CRUD_testDeleteInstructionalVideo() throws Exception {
        when(instructionalVideoService.deleteInstructionalVideo(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/videos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(26)
    void CRUD_testPathVariableAnnotations() throws Exception {
        // Path to the UserController entity file
        Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/controller/UserController.java");

        // Read the content of the entity file as a string
        String entityFileContent = Files.readString(entityFilePath);

        // Check if @PathVariable annotation exists in the UserController
        assertTrue(entityFileContent.contains("@PathVariable"), "UserController should contain @PathVariable annotation");
    }

    @Test
    @Order(27)
    void CRUD_testRequestBodyAnnotations() throws Exception {
        // Path to the HistoryController entity file
        Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/controller/YogaClassController.java");

        // Read the content of the entity file as a string
        String entityFileContent = Files.readString(entityFilePath);

        // Check if @RequestBody annotation exists in the HistoryController
        assertTrue(entityFileContent.contains("@RequestBody"), "YogaClassController should contain @RequestBody annotation");
    }
    @Test
    @Order(28)
    public void JPQL_testFindInstructionalVideosByUserId() {
        // Ensure unique username by deleting any existing user with the same username
        userRepository.deleteAll();
        
        // Create and save users with unique usernames
        User user1 = new User();
        user1.setUsername("john_doe_" + System.currentTimeMillis()); // Unique username
        user1.setPassword("password123");
        user1.setEmail("john.doe@example.com");
        user1 = userRepository.save(user1);

        // Create and save instructional videos for the user
        InstructionalVideo video1 = new InstructionalVideo();
        video1.setTitle("Introduction to Vinyasa Flow");
        video1.setUrl("http://example.com/video1");
        video1.setUser(user1);
        instructionalVideoRepository.save(video1);

        InstructionalVideo video2 = new InstructionalVideo();
        video2.setTitle("Hatha Yoga Basics");
        video2.setUrl("http://example.com/video2");
        video2.setUser(user1);
        instructionalVideoRepository.save(video2);

        // Test finding InstructionalVideos by userId
        List<InstructionalVideo> result = instructionalVideoRepository.findInstructionalVideosByUserId(user1.getId());

        // Assert the result
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(2);
    }

    // Test Case: JPQL_testFindYogaClassesByUserId
    @Test
    @Order(29)
    public void JPQL_testFindYogaClassesByUserId() {
        // Ensure unique username by deleting any existing user with the same username
        userRepository.deleteAll();
        
        // Create and save users with unique usernames
        User user1 = new User();
        user1.setUsername("john_doe_" + System.currentTimeMillis()); // Unique username
        user1.setPassword("password123");
        user1.setEmail("john.doe@example.com");
        user1 = userRepository.save(user1);

        // Create and save yoga classes for the user
        YogaClass yogaClass1 = new YogaClass();
        yogaClass1.setName("Vinyasa Flow");
        yogaClass1.setDescription("A dynamic class with a focus on breath.");
        yogaClass1.setUser(user1);
        yogaClassRepository.save(yogaClass1);

        YogaClass yogaClass2 = new YogaClass();
        yogaClass2.setName("Hatha Yoga");
        yogaClass2.setDescription("A slower-paced class focusing on alignment.");
        yogaClass2.setUser(user1);
        yogaClassRepository.save(yogaClass2);

        // Test finding YogaClasses by userId
        List<YogaClass> result = yogaClassRepository.findYogaClassesByUserId(user1.getId());

        // Assert the result
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(2);
    }

    // Test Case: JPQL_testFindYogaClassByName
    @Test
    @Order(30)
    public void JPQL_testFindYogaClassByName() {
        // Ensure unique username by deleting any existing user with the same username
        userRepository.deleteAll();
        
        // Create and save a user
        User user1 = new User();
        user1.setUsername("john_doe_" + System.currentTimeMillis()); // Unique username
        user1.setPassword("password123");
        user1.setEmail("john.doe@example.com");
        user1 = userRepository.save(user1);

        // Create and save a yoga class
        YogaClass yogaClass = new YogaClass();
        yogaClass.setName("Vinyasa Flow");
        yogaClass.setDescription("A dynamic class with a focus on breath.");
        yogaClass.setUser(user1);  // Ensure user is set
        yogaClass = yogaClassRepository.save(yogaClass);

        // Test finding YogaClass by name
        YogaClass result = yogaClassRepository.findYogaClassByName("Vinyasa Flow");

        // Assert the result
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Vinyasa Flow");
    }

    @Test
    @Order(31)
    void PaginateSorting_testPaginateAndSortUserController() throws Exception {
    // Path to the UserController file
    Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/controller/UserController.java");

    // Read the content of the entity file as a string
    String entityFileContent = Files.readString(entityFilePath);

    // Check if pagination and sorting endpoint exists
    assertTrue(entityFileContent.contains("public ResponseEntity<Page<User>> getAllUsersPaginateAndSort"),
            "UserController should contain /paginate endpoint for pagination and sorting");
    }

    @Test
    @Order(32)
    void PaginateSorting_testPaginateAndSortUserService() throws Exception {
        // Path to the UserService file
        Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/service/UserService.java");

        // Read the content of the entity file as a string
        String entityFileContent = Files.readString(entityFilePath);

        // Check if pagination and sorting method exists in the service
        assertTrue(entityFileContent.contains("public Page<User> getAllUsersPaginateAndSort"),
                "UserService should contain getAllUsersPaginateAndSort method for pagination and sorting");
    }

    @Test
    @Order(33)
    void PaginateSorting_testPaginationUser() {
        // Simulate a GET request to fetch users with pagination
        ResponseEntity<String> response = restTemplate.getForEntity("/api/users?page=0&size=10", String.class);

        // Assert that the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Response status should be OK");

        // Assert that the response body is not empty (assuming it's a JSON array of users)
        assertNotNull(response.getBody(), "Response body should not be null");
    }

    @Test
    @Order(34)
    void PaginateSorting_testPaginationYogaClass() {
        // Simulate a GET request to fetch users with pagination
        ResponseEntity<String> response = restTemplate.getForEntity("/api/classes?page=0&size=10", String.class);

        // Assert that the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Response status should be OK");

        // Assert that the response body is not empty (assuming it's a JSON array of users)
        assertNotNull(response.getBody(), "Response body should not be null");
    }

    @Test
    @Order(35)
    void MAPPING_testYogaClassEntityContainsJoinColumnAnnotation() throws Exception {
        // Path to the YogaClass entity file
        Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/model/YogaClass.java");

        // Read the content of the entity file as a string
        String entityFileContent = Files.readString(entityFilePath);

        // Check if the @JoinColumn annotation exists in the YogaClass entity file
        assertTrue(entityFileContent.contains("@JoinColumn"), "YogaClass entity should contain @JoinColumn annotation for mapping relationships");
    }

    @Test
    @Order(36)
    void MAPPING_testUserEntityContainsOneToManyAnnotation() throws Exception {
        // Path to the User entity file
        Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/model/User.java");

        // Read the content of the entity file as a string
        String entityFileContent = Files.readString(entityFilePath);

        // Check if the @OneToMany annotation exists in the User entity file
        assertTrue(entityFileContent.contains("@OneToMany"), "User entity should contain @OneToMany annotation for relationships");
    }

    @Test
    @Order(37)
    void MAPPING_testInstructionalVideoEntityContainsJoinColumnAnnotation() throws Exception {
        // Path to the InstructionalVideo entity file
        Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/model/InstructionalVideo.java");

        // Read the content of the entity file as a string
        String entityFileContent = Files.readString(entityFilePath);

        // Check if the @JoinColumn annotation exists in the InstructionalVideo entity file
        assertTrue(entityFileContent.contains("@JoinColumn"), "InstructionalVideo entity should contain @JoinColumn annotation for mapping relationships");
    }

    @Test
    @Order(38)
    void MAPPING_testYogaClassEntityContainsManyToOneAnnotation() throws Exception {
        // Path to the YogaClass entity file
        Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/model/YogaClass.java");

        // Read the content of the entity file as a string
        String entityFileContent = Files.readString(entityFilePath);

        // Check if the @ManyToOne annotation exists in the YogaClass entity file
        assertTrue(entityFileContent.contains("@ManyToOne"), "YogaClass entity should contain @ManyToOne annotation for relationships");
    }

    @Test
    @Order(39)
    void MAPPING_testUserEntityContainsOneToManyAnnotationForInstructionalVideos() throws Exception {
        // Path to the User entity file
        Path entityFilePath = Paths.get("src/main/java/com/prjgrp/artf/model/User.java");

        // Read the content of the entity file as a string
        String entityFileContent = Files.readString(entityFilePath);

        // Check if the @OneToMany annotation exists in the User entity file for instructional videos
        assertTrue(entityFileContent.contains("@OneToMany"), "User entity should contain @OneToMany annotation for instructional videos relationship");
    }

    @Test
    @Order(40)
    public void SwaggerUI_testConfigurationFolder() { 
        String directoryPath = "src/main/java/com/prjgrp/artf/configuration"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory(), "The specified configuration folder should exist and be a directory.");
    }

    @Test
    @Order(41)
    public void SwaggerUI_testCustomOpenAPIBeanCreation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SwaggerConfig.class);
        OpenAPI openAPI = context.getBean(OpenAPI.class);

        assertNotNull(openAPI, "OpenAPI bean should not be null.");
        Info info = openAPI.getInfo();
        assertNotNull(info, "OpenAPI Info should not be null.");
        assertEquals("My API", info.getTitle(), "API title should match the expected value.");
        assertEquals("1.0", info.getVersion(), "API version should match the expected value.");
        assertEquals("API documentation using Swagger", info.getDescription(), "API description should match the expected value.");
        context.close();
    }

    @Test
    @Order(42)
    public void SwaggerUI_testCustomOpenAPIMethodIsAnnotatedWithBean() throws NoSuchMethodException {
        Method method = SwaggerConfig.class.getDeclaredMethod("customOpenAPI");
        Bean beanAnnotation = method.getAnnotation(Bean.class);
        assertTrue(beanAnnotation != null, "customOpenAPI method should be annotated with @Bean.");
    }

    @Test
    @Order(43)
    public void SwaggerUI_testConfigurationAnnotation() {
        Configuration configurationAnnotation = SwaggerConfig.class.getAnnotation(Configuration.class);
        assertTrue(configurationAnnotation != null, "SwaggerConfig should be annotated with @Configuration.");
    }
    @Test
    @Order(44)
    void SwaggerUI_testSwaggerUIEndpointIsAccessible() {
        // Make a GET request to the Swagger UI URL
        ResponseEntity<String> response = restTemplate.getForEntity("/swagger-ui/index.html", String.class);

        // Assert that the endpoint returns a 200 OK status
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Swagger UI endpoint is not accessible");
    }  

    @Test
    @Order(45)
    public void Log_testLogFolderAndFileCreation() {
        // Check if the "logs" folder exists
        File logFolder = new File(LOG_FOLDER_PATH);
        assertTrue(logFolder.exists(), "Log folder should be created");

        // Check if the "application.log" file exists inside the "logs" folder
        File logFile = new File(LOG_FILE_PATH);
        assertTrue(logFile.exists(), "Log file should be created inside 'logs' folder");
    }

    @Test
    @Order(46)
    void AOP_testAOPConfigFile() {
        // Path to the LoggingAspect class file
        String filePath = "src/main/java/com/prjgrp/artf/aspect/LoggingAspect.java";

        // Create a File object
        File file = new File(filePath);

        // Assert that the file exists and is a valid file
        assertTrue(file.exists() && file.isFile(), "LoggingAspect.java file should exist at the specified path.");
    }

    @Test
    @Order(47)
    void AOP_testAOPConfigFileAspect() throws Exception {
        // Path to the LoggingAspect class file
        Path aspectFilePath = Paths.get("src/main/java/com/prjgrp/artf/aspect/LoggingAspect.java");

        // Read the content of the aspect file
        String aspectFileContent = Files.readString(aspectFilePath);

        // Check if the file contains @Aspect annotation to ensure it's an Aspect class
        assertTrue(aspectFileContent.contains("@Aspect"), "The LoggingAspect.java should be annotated with @Aspect.");

        // Check if the file contains the logger definition to ensure logging functionality is implemented
        assertTrue(aspectFileContent.contains("private static final Logger logger"), "The LoggingAspect.java should define a logger for logging.");
    }

    @Test
    @Order(48)
    void AOP_testAspectMethods() throws Exception {
        // Path to the LoggingAspect class file
        Path aspectFilePath = Paths.get("src/main/java/com/prjgrp/artf/aspect/LoggingAspect.java");

        // Read the content of the aspect file
        String aspectFileContent = Files.readString(aspectFilePath);

        // Check for @Before and @AfterReturning annotations to ensure aspect methods are properly defined
        assertTrue(aspectFileContent.contains("@Before"), "@Before annotation should be present in the LoggingAspect.java");
        assertTrue(aspectFileContent.contains("@AfterReturning"), "@AfterReturning annotation should be present in the LoggingAspect.java");
    }

    @Test
    @Order(49)
    void AOP_testLoggingStatements() throws Exception {
        // Path to the LoggingAspect class file
        Path aspectFilePath = Paths.get("src/main/java/com/prjgrp/artf/aspect/LoggingAspect.java");

        // Read the content of the aspect file
        String aspectFileContent = Files.readString(aspectFilePath);

        // Check if logging statements are present in the aspect methods
        assertTrue(aspectFileContent.contains("logger.info"), "The LoggingAspect.java should contain logger.info statements for logging.");
    }
}
