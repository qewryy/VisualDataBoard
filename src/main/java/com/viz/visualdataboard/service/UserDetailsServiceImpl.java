package com.viz.visualdataboard.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username에 해당하는 사용자 정보를 불러옵니다.
        // 해당 사용자 정보가 없을 경우 UsernameNotFoundException을 던집니다.
        // 여기서는 예시로 null을 반환하도록 하였습니다.
        return null;
    }
}
