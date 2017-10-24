package sha.work.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.work.mapper.db1.UserMapper;

@Service
public class LogicTest {

    @Autowired
    private UserMapper userMapper;
    
    public void save() {
    	userMapper.save(7, "xie");
    }
}
