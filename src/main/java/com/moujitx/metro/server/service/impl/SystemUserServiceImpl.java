package com.moujitx.metro.server.service.impl;

import com.moujitx.metro.server.entity.SystemUser;
import com.moujitx.metro.server.mapper.SystemUserMapper;
import com.moujitx.metro.server.service.ISystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MOUJITX
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

}