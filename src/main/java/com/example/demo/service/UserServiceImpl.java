package com.example.demo.service;

import com.example.demo.DAO.UserDAO;
import com.example.demo.entity.User;
import com.example.demo.utils.SchoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDAO userDAO;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside SignUp {}", requestMap);
        try
        {
            if (validateSignUp(requestMap))
            {
                User user = userDAO.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user))
                {
                    userDAO.save(getUserFromMap(requestMap));
                    return SchoolUtils.getResponseEntity("Successfully registered.", HttpStatus.OK);
                }
                else
                {
                    return SchoolUtils.getResponseEntity("Email already Exists", HttpStatus.BAD_REQUEST);
                }
            }
            else
            {
                return SchoolUtils.getResponseEntity("Invalid Data", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return SchoolUtils.getResponseEntity("SOMETHING WENT WRONG", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private boolean validateSignUp(Map<String, String> requestMap)
    {
       return  requestMap.containsKey("name") && requestMap.containsKey("contactNumber") &&
                requestMap.containsKey("password");
    }

    private User getUserFromMap(Map<String, String> requestMap)
    {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setEmail(requestMap.get("email"));
        user.setContactNumber(requestMap.get("contactNumber"));

        user.setStatus(requestMap.get("status"));
        user.setRole(requestMap.get("role"));

        user.setPassword(requestMap.get("password"));
        return user;
    }
}
