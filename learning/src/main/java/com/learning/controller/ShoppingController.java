package com.learning.controller;

import com.alibaba.fastjson2.JSON;
import com.learning.domain.Shopping;
import com.learning.service.IShoppingService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Objects;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    private IShoppingService shoppingService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/getShoppingByStatus")
    public @ResponseBody AjaxResult getShoppingByStatus(String status){
//        AjaxResult ajaxResult = new AjaxResult(200, "");
//        Shopping shopping = new Shopping();
//        shopping.setStatus(status);
//        ajaxResult.put("data", shoppingService.getShopping(shopping));
//        return ajaxResult;

        AjaxResult ajaxResult = new AjaxResult(200, "");
        Shopping shopping = new Shopping();
        shopping.setStatus(status);
        if(Objects.requireNonNull(redisTemplate.opsForList().range("shoppingStatus=" + status, 0, -1)).isEmpty()){
            ArrayList<Shopping> shoppings = shoppingService.getShopping(shopping);
            redisTemplate.opsForList().rightPushAll("shoppingStatus" + status, shoppings);
            for (Shopping shopping1 : shoppings) {
                redisTemplate.opsForHash().put("shopping", shopping1.getId() + "", shopping1);
            }
        }
        ajaxResult.put("data", redisTemplate.opsForList().range("shoppingStatus" + status, 0, -1));
        return ajaxResult;
    }

    @PostMapping("/setShopping")
    public @ResponseBody AjaxResult setShopping(@RequestBody Shopping shopping){
        AjaxResult ajaxResult = new AjaxResult(200, "");
        shoppingService.setShopping(shopping);
        ArrayList<Shopping> shoppingReturn = shoppingService.getShopping(shopping);
        if (!shoppingReturn.isEmpty()){
            for (Shopping shopping1 : shoppingReturn) {
                redisTemplate.opsForHash().put("shopping", shopping1.getId() + "", shopping1);
            }
        }
        ajaxResult.put("data", shoppingReturn);
        return ajaxResult;
    }

    @PutMapping("/updateShopping")
    public @ResponseBody AjaxResult updateShopping(@RequestBody Shopping shopping) {
        AjaxResult ajaxResult = new AjaxResult(200, "");
        shoppingService.updateShopping(shopping);
        ArrayList<Shopping> shoppingReturn = shoppingService.getShopping(shopping);
        if (!shoppingReturn.isEmpty()){
            for (Shopping shopping1 : shoppingReturn) {
                redisTemplate.opsForHash().put("shopping", shopping1.getId() + "", shopping1);
            }
        }
        ajaxResult.put("data", shoppingReturn);
        return ajaxResult;
    }

    @GetMapping("/getShoppingDetailById")
    public @ResponseBody AjaxResult getShoppingDetailById(Integer id){
        AjaxResult ajaxResult = new AjaxResult(200, "");
        Shopping shopping = new Shopping();
        if (redisTemplate.opsForHash().get("shopping", id + "") == null){
            shopping.setId(id);
            shopping = shoppingService.getShopping(shopping).get(0);
            redisTemplate.opsForHash().put("shopping", id + "", shopping);
        }
        ajaxResult.put("data", redisTemplate.opsForHash().get("shopping", id + ""));
        return ajaxResult;
    }

    @DeleteMapping("/deleteShoppingById")
    public @ResponseBody AjaxResult deleteShoppingById(Integer id){
        AjaxResult ajaxResult = new AjaxResult(200, "");
        shoppingService.deleteShoppingById(id);
        if (redisTemplate.opsForHash().get("shopping", id + "") != null){
            Shopping shopping = JSON.parseObject(redisTemplate.opsForHash().get("shopping", id + "").toString(), Shopping.class);
            for (Object shopping1 : Objects.requireNonNull(redisTemplate.opsForList().range("shoppingStatus=" + shopping.getStatus(), 0, -1))) {
                if (Objects.equals(shopping.getId(), ((Shopping) shopping1).getId())){
                    redisTemplate.opsForList().remove("shoppingStatus=" + shopping.getStatus(), 1, shopping1);
                }
            }
            redisTemplate.opsForHash().delete("shopping", id + "");
        }
        return ajaxResult;
    }
}
