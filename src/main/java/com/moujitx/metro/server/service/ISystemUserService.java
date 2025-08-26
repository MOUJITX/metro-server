package com.moujitx.metro.server.service;

import com.moujitx.metro.server.entity.SystemUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MOUJITX
 */
public interface ISystemUserService extends IService<SystemUser> {
    SystemUser authenticate(String username, String password);
}