/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.runtime.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

import com.alipay.sofa.runtime.spi.spring.RuntimeShutdownAware;

/**
 * @author qilong.zql
 * @since 2.5.0
 */
public class DefaultRuntimeShutdownAware implements RuntimeShutdownAware, ApplicationContextAware {
    private ApplicationContext ctx;

    @Override
    public void shutdown() {
        if (ctx instanceof AbstractApplicationContext) {
            ((AbstractApplicationContext) ctx).close();
        } else {
            throw new RuntimeException(String.format("%s is not instanceof %s, can not be closed.",
                ctx.getClass(), AbstractApplicationContext.class));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}