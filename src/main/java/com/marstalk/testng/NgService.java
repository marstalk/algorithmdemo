package com.marstalk.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NgService {

    @Autowired
    private NgDao ngDao;

    public void hi(){
        ngDao.hi();
    }


}
