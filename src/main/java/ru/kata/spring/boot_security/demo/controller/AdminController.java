//package ru.kata.spring.boot_security.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.RoleService;
//import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
//import ru.kata.spring.boot_security.demo.service.UserService;
//import ru.kata.spring.boot_security.demo.service.UserServiceImpl;
//import ru.kata.spring.boot_security.demo.util.UserErrorResponse;
//import ru.kata.spring.boot_security.demo.util.UserNotCreatedException;
//import ru.kata.spring.boot_security.demo.util.UserNotFoundException;
//
//import javax.validation.Valid;
//import java.security.Principal;
//import java.util.List;
//
//@Controller
////@RestController  //@Controller + @ResponseBody
//@RequestMapping("/admin")
//public class AdminController {
//
//
//    private final UserService userService;
//    private final RoleService roleService;
//
//    @Autowired
//    public AdminController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
////    @GetMapping("/sayHello")
////    @ResponseBody
////    public String sayHello() {
////        return "Hello World";
////    }
//
//@GetMapping("")
//public String printUsers(ModelMap modelMap, Principal principal) {
//    modelMap.addAttribute("user", userService.findUserByLogin(principal.getName()));
//    modelMap.addAttribute("usersList", userService.getAllUsers());
//    modelMap.addAttribute("roles", roleService.getListOfRoles());
//    modelMap.addAttribute("newUser", new User());
//    return "admin";
//}
//
//    @GetMapping("/users/{id}")
//    @ResponseBody
//    public User getUser(@PathVariable("id") long id) {
//        return userService.findUserById(id);
//    }
//    @ExceptionHandler
//    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
//        UserErrorResponse response = new UserErrorResponse(
//                "User with this ID not found",
//                System.currentTimeMillis()
//        );
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping("/users")
//    @ResponseBody
//    public List<User> getUsers() {
//        return userService.getAllUsers();
//    }
//
//
//
//@PostMapping("/add")
//public String createUser(User user) {
//    userService.addUser(user);
//    return "redirect:/admin";
//}
//@PostMapping("/users")
//@ResponseBody
////public User create(@RequestBody @Valid User user) {
//public ResponseEntity<HttpStatus> create(@RequestBody User user){
//
//    userService.addUser(user);
////    return user;
//    return ResponseEntity.ok(HttpStatus.OK);
//}
//    @ExceptionHandler
//    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e) {
//        UserErrorResponse response = new UserErrorResponse(
//                e.getMessage(),
//                System.currentTimeMillis()
//        );
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//
//@PostMapping("/edit/{id}")
//public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user, @RequestParam("roles") Long[] rolesId) {
//    userService.editUserById(user);
//
//    return "redirect:/admin";
//}
//@ResponseBody
//@PatchMapping("/editUser/{id}")
//
//
//    @PostMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.removeUserById(id);
//
//        return "redirect:/admin";
//    }
//
//    @ResponseBody
//    @DeleteMapping("/userDelId/{id}")
//    public ResponseEntity<HttpStatus> delUser(@PathVariable("id") Long id) {
//        userService.removeUserById(id);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }
//    @ResponseBody
//    @DeleteMapping("/userDelLogin/{login}")
//    public ResponseEntity<HttpStatus> delUser(@PathVariable("login") String login) {
//        userService.removeUserById(userService.findUserByLogin(login).getId());
//        return ResponseEntity.ok(HttpStatus.OK);
//    }
//
//
//}
