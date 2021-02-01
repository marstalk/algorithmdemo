package com.marstalk.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NgServiceWithStatic {
    @Autowired
    private NgDao ngDao;

    public String checkUser(String user) {
        if (!NgUtils.notNull(user)) {
            throw new RuntimeException("user name can not be null");
        }
        return ngDao.queryByName(user);
    }

}
