package com.learning.service.impl;

import com.learning.domain.Shopping;
import com.learning.mapper.ShoppingMapper;
import com.learning.service.IShoppingService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class ShoppingServiceImpl implements IShoppingService {

    @Autowired
    private ShoppingMapper shoppingMapper;

    @Override
    public ArrayList<Shopping> getShopping(Shopping shopping) {
        System.out.println(shopping);
        return shoppingMapper.getShopping(shopping);
    }

    @Override
    public void setShopping(Shopping shopping) {
        shoppingMapper.setShopping(shopping);
    }

    @Override
    public void updateShopping(Shopping shopping) {
        shoppingMapper.updateShopping(shopping);
    }

    @Override
    public void deleteShoppingById(Integer id) {
        shoppingMapper.deleteShoppingById(id);
    }
}
