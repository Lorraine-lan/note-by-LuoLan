package com.hgsf.supermarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hgsf.supermarket.entity.Bill;
import com.hgsf.supermarket.service.BillService;
import com.hgsf.supermarket.utils.R;
import com.hgsf.supermarket.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/save")
    public R save(@RequestBody Bill bill){
        //验证账单编码是否重复
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<Bill>();
        queryWrapper.eq("billCode",bill.getBillCode());
        Bill db_bill = billService.getOne(queryWrapper);
        if(null == db_bill){
            bill.setCreationDate(new Date());
            billService.save(bill);
            return R.createSuccess();
        }else{
            return R.createError(StatusCode.ERROR,"添加失败,账单编码已存在");
        }
    }

    @PostMapping("/getPageBy")
    public R<Page<Bill>> getPageBy(@RequestParam Integer currentPage,
                                   @RequestParam Integer pageSize,
                                   String billCode,
                                   String productName,
                                   Integer providerId){
        Page<Bill> page = billService.getPageBy(currentPage,pageSize,billCode,productName,providerId);
        return R.createSuccess(page);
    }

    @PostMapping("/update")
    public R update(@RequestBody Bill bill){
        boolean result = billService.updateById(bill);
        if(result){
            return R.createSuccess();
        }else {
            return R.createError();
        }
    }

    @GetMapping("/delete")
    public R delete(@RequestParam Integer id){
        Bill bill = billService.getById(id);
        if(bill.getIsPayment() == 1) {
        boolean result = billService.removeById(id);
        if(result){
            return R.createSuccess();
        }else {
            return R.createError();
        }
        }else {
            return R.createError(StatusCode.ERROR,"账单已支付，无法删除");
        }
    }


}