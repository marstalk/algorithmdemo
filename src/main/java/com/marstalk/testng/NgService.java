package com.marstalk.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NgService {

    @Autowired
    private NgDao ngDao;

    public String hi(String name) {
        return ngDao.hi(name);
    }

    public String findUser(String name) {
        System.out.println("NgService do some logic");
        return ngDao.queryByName(name);
    }


}
