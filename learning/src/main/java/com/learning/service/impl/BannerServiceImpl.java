package com.learning.service.impl;

import com.learning.domain.Banner;
import com.learning.mapper.BannerMapper;
import com.learning.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    public List<Banner> BannerList(){
        return bannerMapper.selectBanner();
    }
}
