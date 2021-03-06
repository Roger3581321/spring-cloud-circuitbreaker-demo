/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.cloud.circuitbreaker.demo.springretrycircuitbreaker;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HttpService {

    @Retryable(RemoteAccessException.class)
    public Map<String, Object> service() {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("note", "Hello World, service!");
        throw new RemoteAccessException("RemoteAccessException is happening");
    }

    @Recover
    public Map<String, Object> recover(RemoteAccessException e) {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("note", "Hello World, recover!");
        return ret;
    }


}
