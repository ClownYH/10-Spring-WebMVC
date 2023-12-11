package com.ohgiraffers.securitysession.auth.model;

import com.ohgiraffers.securitysession.user.model.dto.LoginUserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AuthDetails implements UserDetails {

    private LoginUserDTO loginUserDTO;

    public AuthDetails(LoginUserDTO loginUserDTO) {
        this.loginUserDTO = loginUserDTO;
    }

    public LoginUserDTO getLoginUserDTO() {
        return loginUserDTO;
    }

    public void setLoginUserDTO(LoginUserDTO loginUserDTO) {
        this.loginUserDTO = loginUserDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 권한이 있는지 없는지 판단하는 메소드
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        loginUserDTO.getRole().forEach(role -> authorities.add(() -> role));

        return authorities;
    }


    @Override
    public String getPassword() { // 사용자의 비밀번호를 반환하는 메소드, 토큰과 비교할 때 사용
        return loginUserDTO.getPassword();
    }

    @Override
    public String getUsername() { // 사용자의 아이디를 반환하는 메소드, 토큰과 비교할 때 사용
        return loginUserDTO.getUserName();
    }

    /**
     * 계정 만료 여부를 표현하는 메소드로
     * false이면 해당 계정을 사용할 수 없다.
     * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 잠겨있는 계정을 확인하는 메소드로
     * false이면 해당 계저응ㄹ 사용할 수 없다.
     *
     * 비밀번호 반복 실패로 일시적인 계정 lock과 같은 경우
     * 혹은 오랜 기간 비접속으로 휴면 처리
     * */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 탈퇴 계정 여부를 표현하는 메소드
     * false이면 해당 계정을 사용할 수 없다.
     *
     * 보통 데이터 삭제는 즉시하는 것이 아닌 일정 기간 보관 후 삭제를 한다.
     * */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정 비활성화 여부로 사용자가 사용할 수 없는 상태
     * false이면 계정을 사용할 수 없다.
     *
     * 삭제 처리와 같은 경우
     * */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
