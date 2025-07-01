/*
 * MIT License
 *
 * Copyright (c) 2023 OrdinaryRoad
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.caesar.crawler.live.douyin.codec.msg;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.caesar.crawler.live.douyin.codec.constant.DouyinCmdEnum;
import org.caesar.crawler.live.douyin.codec.msg.base.BaseDouyinCmdMsg;
import org.caesar.crawler.live.douyin.codec.protobuf.Message;
import org.caesar.crawler.live.netty.util.serializer.ProtobufToBase64Serializer;

/**
 * @author mjz
 * @date 2024/4/26
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DouyinCmdMsg extends BaseDouyinCmdMsg {

    @JsonSerialize(using = ProtobufToBase64Serializer.class)
    private Message msg;

    @Override
    public String getCmd() {
        if (msg == null) {
            return null;
        }
        return msg.getMethod();
    }

    @Override
    public void setCmd(String cmd) {
        // ignore
        // method_ = cmd;
    }

    @Override
    public DouyinCmdEnum getCmdEnum() {
        if (msg == null) {
            return null;
        }
        return DouyinCmdEnum.getByName(msg.getMethod());
    }
}
