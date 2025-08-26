package com.moujitx.metro.server.service.impl;

import com.moujitx.metro.server.entity.SystemUser;
import com.moujitx.metro.server.exception.AuthorizationException;
import com.moujitx.metro.server.mapper.SystemUserMapper;
import com.moujitx.metro.server.service.ISystemUserService;

import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MOUJITX
 */
@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {
    public SystemUser authenticate(String username, String password) {
        QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        SystemUser user = this.getOne(queryWrapper);

        if (user == null || !user.getPassword().equals(password)) {
            throw new AuthorizationException();
        }

        if (user.getStatus().equals(false)) {
            throw new AuthorizationException("Forbidden User");
        }

        return user;
    }

    public Page<SystemUser> page(Integer page, Integer pageSize) {
        return this.page(new Page<>(page, pageSize));
    }

}