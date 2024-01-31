package com.learning.mapper;


import com.learning.domain.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    public List<Banner> selectBanner();
}