package com.learning.mapper;

import com.learning.domain.Shopping;
import com.ruoyi.common.core.domain.AjaxResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Mapper
public interface ShoppingMapper {
    ArrayList<Shopping> getShopping(Shopping shopping);

    void setShopping(Shopping shopping);

    @Update("update shoppings set name=#{name}, price=#{price}, status=#{status}, type=#{type} where id=#{id}")
    void updateShopping(Shopping shopping);

    @Delete("delete from shoppings where id=#{id}")
    void deleteShoppingById(Integer id);
}
