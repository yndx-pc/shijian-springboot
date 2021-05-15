package com.example.shijian.controller;

import com.example.shijian.entity.User;
import com.example.shijian.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {



    @Autowired
    UserRepository userRepository;

//    @Autowired
//    JdbcTemplate jdbcTemplate;
//    PrintWriter printWriter = null;
//
//    @RequestMapping("/test")
//    @ResponseBody
//    @CrossOrigin
//    public void index(HttpServletResponse response) throws JsonProcessingException {
//        String sql = "select F_name name,votes vote " +
//                "from food_frugal_foods " +
//                "where votes > 0 " +
//                "order by vote desc";
////        sql = "select F_name name from food_frugal_foods";
//        response.setContentType("application/json");
//
//        response.setCharacterEncoding("utf-8");
//        try {
//            printWriter = response.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
//        printWriter.write(new ObjectMapper().writeValueAsString(list));
//    }

//    @GetMapping("/user/{id}")
//    public User getUser(@PathVariable("id") Integer id){
//        User user = userRepository.findById(id).get();
//        return user;
//    }

    @GetMapping("/user/{name}")
    public List<User> getName(@PathVariable("name") String name){
        List<User> user = userRepository.findByName(name);
        return user;
    }

    @GetMapping(value = "/user/all")
    public List<User> getAll(){
        List<User> user = userRepository.findAll();
        return user;
    }

    @GetMapping("/user")
    public User insertUser(User user){
        User save = userRepository.save(user);
        return save;
    }
}
