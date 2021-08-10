package dao;

import com.lagou.RunBoot;
import com.lagou.entity.BOrder;
import com.lagou.entity.Position;
import com.lagou.entity.PositionDetail;
import com.lagou.repository.BOrderRepository;
import com.lagou.repository.PositionDetailRepository;
import com.lagou.repository.PositionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestShardingDatabase {

    @Resource
    private PositionRepository positionRepository;

    @Resource
    private PositionDetailRepository positionDetailRepository;

    @Resource
    private BOrderRepository bOrderRepository;

    @Test
    public void testAdd() {
        for (int i = 1; i <= 20; i++) {
            Position position = new Position();
            // 自定义主键或者雪花生成主键
//            position.setId(i);
            position.setName("lagou" + i);
            position.setSalary("100000");
            position.setCity("beijing");
            positionRepository.save(position);
        }
    }

    @Test
    public void testAdd2() {
        for (int i = 1; i <= 20; i++) {
            Position position = new Position();
            // 自定义主键或者雪花生成主键
//            position.setId(i);
            position.setName("lagou" + i);
            position.setSalary("100000");
            position.setCity("beijing");
            positionRepository.save(position);

            PositionDetail positionDetail = new PositionDetail();
            positionDetail.setPid(position.getId());
            positionDetail.setDescription("this message" + i);
            positionDetailRepository.save(positionDetail);
        }
    }

    @Test
    public void testFindById(){
        Object object = positionRepository.findPositionById(79745274098978L);
        Object[] positon = (Object[]) object;
        System.out.println(positon[0] + " " + positon[1] + " " + positon[2] + " " +positon[3] + " " + positon[4] );
    }

    @Test
    @Repeat(10) // 重复执行
    public void testShardingBOrder() {
        Random random = new Random();
        int companyId = random.nextInt(10);
        BOrder order = new BOrder();
        order.setDel(false);
        order.setCompanyId(companyId);
        order.setPositionId(324432);
        order.setUserId(2222);
        order.setPublishUserId(1111);
        order.setResumeId(1);
        order.setStatus("AUTO");
        order.setCreateTime(new Date());
        order.setOperateTime(new Date());
        order.setWorkYear("2");
        order.setName("lagou");
        order.setPositionName("Java");
        order.setResumeId(232323);
        bOrderRepository.save(order);
    }
}
