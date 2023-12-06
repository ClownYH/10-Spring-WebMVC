package com.ohgiraffers.pos.menu.service;

import com.ohgiraffers.pos.menu.dto.MenuDTO;
import com.ohgiraffers.pos.menu.dto.RegistDTO;
import com.ohgiraffers.pos.menu.model.MenuDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MenuService {

    private MenuDAO menuDAO;

    public MenuService(MenuDAO menuDAO) { // @Autowired로 갈음할 수 있음
        this.menuDAO = menuDAO;
    }

    public List<MenuDTO> selectAllMenu() {
        List<MenuDTO> menus = menuDAO.selectAllMenu();

        if (Objects.isNull(menus)) {
            System.out.println("exception menus가 없네요");
        }

        return menus;
    }

    public int registMenu(RegistDTO regist) {
        int result = menuDAO.registMenu(regist);

        if(result < 0){
            System.out.println("등록 실패하였습니다.");
            return 0;
        }

        return result;
    }

    public int modifyMenu(MenuDTO modify) {
        int result = menuDAO.modifyMenu(modify);

        if(result < 0){
            System.out.println("수정에 실패하였습니다. 아무래도 없는 코드인 것 같습니다.");
        }

        return result;
    }

    public MenuDTO searchMenu(int code) {
        MenuDTO menu = menuDAO.searchMenu(code);

        return menu;
    }
}
