package com.example.stationery.controller;

import com.example.stationery.dto.UploadMediaDto;
import com.example.stationery.model.User;
import com.example.stationery.service.JwtService;
import com.example.stationery.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/rest")
public class UserController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<String> login(HttpServletRequest request, @RequestBody User user) {
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if (userService.checkLogin(user)) {
                result = jwtService.generateTokenLogin(user.getUsername());
                httpStatus = HttpStatus.OK;
            } else {
                result = "Wrong username or password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            result = "Service error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(result, httpStatus);
    }

    @PostMapping("/upload/image")
    public ResponseEntity<?> uploadImage(
            @RequestParam("uFile") MultipartFile uploadFile,
            @RequestParam("user_id") String user_id
    ) throws IOException {
        String fileName = uploadFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf("."));
        if (!".jpg|.png".contains(ext)) {
            ext = ".jpg";
        }
        String mediaFileName = user_id + "_" + ext;

        String imageSubDir = "/usr_upload/" + "/image/";
        boolean ret = new File(imageSubDir).mkdir();
        System.out.println(ret);

        String mediaPath = imageSubDir + "/" + mediaFileName;
        uploadFile.transferTo(new File("D:\\Download" + mediaPath));

        UploadMediaDto listImageDto = UploadMediaDto.builder()
                .mediaPath(mediaPath)
                .mediaUrl(mediaPath)
                .build();

        return ResponseEntity.ok().body(listImageDto);
    }
}
