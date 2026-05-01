package com.hgsf.supermarket;

        import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
        import com.hgsf.supermarket.entity.Bill;
        import com.hgsf.supermarket.service.BillService;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;

        import java.util.List;

@SpringBootTest
public class BillServiceTest {
    @Autowired
    private BillService billService;
    @Test
    public void getPageByTest(){
        Page<Bill> page = billService.getPageBy(1,3,null,null,null);
        System.out.println(page);
        //取出集合数据
        List<Bill> records = page.getRecords();
        System.out.println(records);
        System.out.println("当前页面"+page.getCurrent());
        System.out.println("每页大小"+page.getSize());
        System.out.println("总记录数"+page.getTotal());
        System.out.println("总页数"+page.getPages());
    }
}
