package com.ohgiraffers.fileupload;

import jakarta.annotation.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileuploadController {

    @Resource
    private ResourceLoader resourceLoader;

    @RequestMapping(value = {"/", "/main"})
    public String index(){
        return "main";
    }

    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile, String singleFileDescription, Model model) throws IOException {
        // 원래 파일 등록은 서비스 로직에서 수행
        System.out.println("single file : " + singleFile);
        System.out.println("원본 파일 이름 : " + singleFile.getOriginalFilename());
        System.out.println("input name : " + singleFile.getName());
//        System.out.println("원본 파일 객체 : " + singleFile.getBytes());
        // 실제 컴퓨터가 인식하고 있는 바이너리 데이터가 들어있는 주소값, 다른 곳으로 파일을 보내거나 검사할 때는 여기 있는 값이 간다.
        System.out.println("원본 파일 사이즈 : " + singleFile.getSize()); // 사이즈는 용량을 의미, 제한을 줘야 한다.

        // 파일을 저장할 경로 설정(실제 파일을 저장할 위치는 이렇게 사용하지 않는다. FTP나 nodejs, s3 서버 등을 이용한다.)
//        String root = "c:/upload-files";
//        String filePath = root + "/single";
        String filePath = resourceLoader.getResource("classpath:static/img/").getFile().getAbsolutePath();
        File dir = new File(filePath);
        System.out.println(dir.getAbsolutePath());

        if(!dir.exists()){
            dir.mkdirs(); // 폴더 생성
        }

        String originFileName = singleFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf(".")); // 확장자만 떼어낸다.
        String savedName = UUID.randomUUID().toString().replace("-", "") + ext; // UUID.randomUUID 무작위로 생성된 이름 부여(결코 중복 안됨)

        try {
            System.out.println("filePath==========================" + filePath);
            singleFile.transferTo(new File(filePath + "/" + savedName));
            model.addAttribute("message", "파일 업로드 성공");
            model.addAttribute("img", "static/img/" + savedName);
            System.out.println("singleFileDescription : " + singleFileDescription);
        }catch (IOException e){
            e.printStackTrace();
            model.addAttribute("message", "파일 업로드 실패");
        }

        return "result";
    }

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multipartFiles, String multiFileDescription, Model model){
        System.out.println("multiFiles : " + multipartFiles);
        System.out.println("multiFileDescription : " + multiFileDescription);

        String root = "c:/upload-files";
        String filePath = root + "/multi";

        File dir = new File(filePath);
        if(!dir.exists()){
            dir.mkdirs(); // 폴더를 다수 만들 경우
//            dir.mkdir(); // 상위 폴더가 존재하는 경우
        }

        List<FileDTO> files = new ArrayList<>();

        try {
            for (MultipartFile file : multipartFiles) {
                String originFileName = file.getOriginalFilename();
                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
                files.add(new FileDTO(originFileName, savedName,filePath,multiFileDescription));
                file.transferTo(new File(filePath, "/" + savedName));
            }
            model.addAttribute("message", "다중 파일 업로드 성공");
        }catch (IOException e){
            e.printStackTrace();

            for (FileDTO file : files) {
                new File(filePath + "/" + file.getSavedName()).delete(); // 한 번에 다 받을 때 에러가 나면 전부 삭제해야 한다.
            }

            model.addAttribute("message", "다중 파일 업로드 실패");
        }

        return "result";

    }

}
