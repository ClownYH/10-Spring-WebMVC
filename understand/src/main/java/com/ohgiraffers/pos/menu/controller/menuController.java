package com.ohgiraffers.pos.menu.controller;

import com.ohgiraffers.pos.menu.dto.MenuDTO;
import com.ohgiraffers.pos.menu.dto.RegistDTO;
import com.ohgiraffers.pos.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("menus")
public class menuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("")
    public ModelAndView selectAllMenu(ModelAndView mv){

        List<MenuDTO> menus = menuService.selectAllMenu();

        if(Objects.isNull(menus)){
            throw new NullPointerException();

        }else {
            mv.addObject("menus", menus);
            mv.setViewName("menu/allMenus");
            return mv;
        }
    }

    @GetMapping("/search")
    public ModelAndView searchMenu(ModelAndView mv,@RequestParam int code){

        if(code == 0){
            System.out.println("메뉴 코드는 0일 수 없습니다.");
            mv.addObject("message", "메뉴 코드는 0이 될 수 없습니다.");
            mv.setViewName("error/errorPage");
            return mv;
        }

        MenuDTO menu = menuService.searchMenu(code);

        if(Objects.isNull(menu)){
            throw new NullPointerException();
        }else {
            mv.addObject("menu", menu);
            mv.setViewName("result/searchResult");
            return mv;
        }
    }

    @PostMapping("/regist")
    public String registMenu(@RequestParam Map<String, String> parameters){

        String name= parameters.get("name");
        int price = Integer.parseInt(parameters.get("price"));
        int categoryCode = Integer.parseInt(parameters.get("categoryCode"));
        String status = parameters.get("status");

        RegistDTO regist = new RegistDTO();
        regist.setName(name);
        regist.setPrice(price);
        regist.setCategoryCode(categoryCode);
        regist.setStatus(status);

        int result = menuService.registMenu(regist);

        if(result > 0){
            return "result/registSuccess";
        }else {
            return "result/registFail";
        }
    }

    @PostMapping("/modify")
    public String modifyMenu(Model model, @RequestParam Map<String, String> parameters){

        int code = Integer.parseInt(parameters.get("code"));
        String name = parameters.get("name");
        int price = Integer.parseInt(parameters.get("price"));
        int categoryCode = Integer.parseInt(parameters.get("categoryCode"));
        String status = parameters.get("status");

        MenuDTO modify = new MenuDTO();
        modify.setCode(code);
        modify.setName(name);
        modify.setPrice(price);
        modify.setCategoryCode(categoryCode);
        modify.setStatus(status);

        int result = menuService.modifyMenu(modify);

        if(result > 0){
            model.addAttribute("message", "수정에 성공하였습니다.");
            return "result/modifyResult";
        }else {
            model.addAttribute("message", "수정에 실패하셨습니다.");
            return "result/modifyResult";
        }
    }

    @PostMapping("/delete")
    public ModelAndView deleteMenu(ModelAndView mv, MenuDTO menuDTO){

        int result = menuService.deleteMenu(menuDTO);

        if(result <= 0){
            System.out.println("삭제에 실패하셨습니다.");
            mv.addObject("message", "삭제에 실패하셨습니다.");
            mv.setViewName("result/deleteResult");
            return mv;
        }else {

            mv.addObject("message", menuDTO.getCode() + "번 메뉴를 삭제하셨습니다.");
            mv.setViewName("result/deleteResult");
            return mv;
        }

    }
}
