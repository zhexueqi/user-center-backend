package com.yupi.usercenter;


import com.yupi.usercenter.mapper.UserMapper;
import jakarta.annotation.Resource;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class SampleTest {

    @Resource
    private UserMapper userMapper;

//    @Test
//    public void testSelect() {
//        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
//        userList.forEach(System.out::println);
//    }

}