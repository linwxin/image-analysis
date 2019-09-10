package com.imageanalysis.demo.controller;

import com.imageanalysis.demo.VO.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Controller
@RequestMapping("/")
@Slf4j
public class Demo {

    @Value("${image_path}")
    private String fileDir;

    @GetMapping("")
    public String index() {
        return "blank";
    }

    @PostMapping(value = "upload")
    @ResponseBody
    public HttpRequest upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();//获取最初文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取后缀.jpg
        //String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"static";//获取绝对路径
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static";//获取绝对路径
        path = path + fileDir;
        path = path.replace("/", "\\");
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(path + fileName);

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imgUrl = path + fileName;
        return new HttpRequest(true, imgUrl);//假装传了imgUrl, 其实没屁用
    }

    @PostMapping(value = "test")
    @ResponseBody
    public HttpRequest test() {
        // todo 测试图片接口
        System.out.println("进来了");
        return new HttpRequest(true);
    }
}
