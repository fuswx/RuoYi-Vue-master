package com.learning.service;

import com.learning.domain.Shopping;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.ArrayList;

public interface IShoppingService {

    ArrayList<Shopping> getShopping(Shopping shopping);

    void setShopping(Shopping shopping);

    void updateShopping(Shopping shopping);

    void deleteShoppingById(Integer id);
}
