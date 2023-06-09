package org.example.service.impl;

import org.example.service.StuService;
import org.example.service.TestTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestTransServiceImpl implements TestTransService {

    @Autowired
    private StuService stuService;

    /**
     * 事务传播 - Propagation
     *      REQUIRED: 使用当前的事务，如果当前没有事务，则自己新建一个事务，子方法是必须运行在一个事务中的；
     *                如果当前存在事务，则加入这个事务，成为一个整体。
     *                如果父方法有注解，子方法没有注解，则子方法报错父方法也会回滚；
     *                但是子方法有注解，父方法没有注解，则子方法报错父方法不会回滚；
     *                主要用于增删改；
     *      SUPPORTS: 如果当前有事务，则使用事务；如果当前没有事务，则不使用事务。
     *                主要用于查询；
     *      MANDATORY: 该传播属性强制必须存在一个事务，如果不存在，则抛出异常；
     *                 父方法没有事务，子方法有MANDATORY事务则会抛出异常；
     *      REQUIRES_NEW: 如果当前有事务，则挂起该事务，并且自己创建一个新的事务给自己使用；
     *                    如果当前没有事务，则同 REQUIRED；
     *                    父方法有异常，子方法没有异常，父方法REQUIRED，子方法REQUIRES_NEW，则父方法回滚不影响子方法，
     *                    但是 子方法有异常回滚，依然会导致父方法回滚；
     *                    也就是 外层事务回滚不影响内层事务，但是内层事务回滚影响外层事务；
     *      NOT_SUPPORTED: 如果当前有事务，则把事务挂起，自己不适用事务去运行数据库操作
     *      NEVER: 如果当前有事务存在，则抛出异常
     *      NESTED: 如果当前有事务，则开启子事务（嵌套事务），嵌套事务是独立提交或者回滚；
     *              如果当前没有事务，则同 REQUIRED。
     *              但是如果主事务提交，则会携带子事务一起提交。
     *              如果主事务回滚，则子事务会一起回滚。相反，子事务异常，则父事务可以回滚或不回滚。
     */

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagationTrans() {
        stuService.saveParent();

        try {
            // save point
            stuService.saveChildren();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // delete
        // update
        stuService.saveParent();

//        int a = 1 / 0;
    }
}
