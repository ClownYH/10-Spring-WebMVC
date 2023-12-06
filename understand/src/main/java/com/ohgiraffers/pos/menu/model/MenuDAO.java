package com.ohgiraffers.pos.menu.model;

import com.ohgiraffers.pos.menu.dto.MenuDTO;
import com.ohgiraffers.pos.menu.dto.RegistDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuDAO { // model -> DAO

    List<MenuDTO> selectAllMenu();

    int registMenu(RegistDTO regist);

    int modifyMenu(MenuDTO modify);

    MenuDTO searchMenu(int code);
}
