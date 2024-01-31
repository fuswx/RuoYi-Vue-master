package com.learning.controller;

import com.learning.domain.Banner;
import com.learning.service.BannerService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/list")
    public AjaxResult bannerList(){
        List<Banner> banners = bannerService.BannerList();
        return AjaxResult.success("查询成功", banners);
    }
}
