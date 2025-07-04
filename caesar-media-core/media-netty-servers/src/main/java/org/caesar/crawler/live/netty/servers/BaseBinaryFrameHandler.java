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

package org.caesar.crawler.live.netty.servers;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.caesar.crawler.live.netty.base.listener.IBaseMsgListener;
import org.caesar.crawler.live.netty.base.msg.ICmdMsg;
import org.caesar.crawler.live.netty.base.msg.IMsg;

import java.util.List;
import java.util.function.Consumer;


/**
 * 消息处理器
 *
 * @author mjz
 * @date 2023/1/4
 */
@Slf4j
public abstract class BaseBinaryFrameHandler<
        T extends BaseBinaryFrameHandler<?, ?, ?, ?>,
        CmdEnum extends Enum<CmdEnum>,
        Msg extends IMsg,
        MsgListener extends IBaseMsgListener<T, CmdEnum>
        > extends SimpleChannelInboundHandler<Msg>
        implements IBaseMsgListener<T, CmdEnum> {

    @Getter
    private final Object roomId;
    protected final List<MsgListener> msgListeners;

    public BaseBinaryFrameHandler(List<MsgListener> msgListeners, Object roomId) {
        this.msgListeners = msgListeners;
        this.roomId = roomId;
        if (this.msgListeners == null || this.msgListeners.isEmpty()) {
            if (log.isDebugEnabled()) {
                log.debug("listener not set");
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg msg) {
        this.onMsg((T) BaseBinaryFrameHandler.this, msg);
        if (msg instanceof ICmdMsg<?>) {
            ICmdMsg<?> cmdMsg = (ICmdMsg<?>) msg;
            Enum<?> cmdEnum = cmdMsg.getCmdEnum();
            if (cmdEnum == null) {
                this.onUnknownCmd((T) BaseBinaryFrameHandler.this, cmdMsg.getCmd(), cmdMsg);
            } else {
                this.onCmdMsg((T) BaseBinaryFrameHandler.this, (CmdEnum) cmdEnum, (ICmdMsg<CmdEnum>) cmdMsg);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause.getCause() instanceof UnrecognizedPropertyException) {
            log.error("缺少字段：{}", cause.getMessage());
        } else {
            super.exceptionCaught(ctx, cause);
        }
    }

    @Override
    public void onMsg(T t, IMsg msg) {
        IBaseMsgListener.super.onMsg(t, msg);
        iteratorMsgListeners(msgListener -> msgListener.onMsg(t, msg));
    }

    /**
     * 重写该方法，判断CMD，或者调用{@link IBaseMsgListener#onOtherCmdMsg(Object, Enum, ICmdMsg)}
     *
     * @param t      BaseBinaryFrameHandler
     * @param cmd    CmdEnum
     * @param cmdMsg BaseMsg
     */
    @Override
    public void onCmdMsg(T t, CmdEnum cmd, ICmdMsg<CmdEnum> cmdMsg) {
        IBaseMsgListener.super.onCmdMsg(t, cmd, cmdMsg);
        iteratorMsgListeners(msgListener -> msgListener.onCmdMsg(t, cmd, cmdMsg));
    }

    @Override
    public void onUnknownCmd(T t, String cmdString, IMsg msg) {
        IBaseMsgListener.super.onUnknownCmd(t, cmdString, msg);
        iteratorMsgListeners(msgListener -> msgListener.onUnknownCmd(t, cmdString, msg));
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    public void iteratorMsgListeners(Consumer<MsgListener> consumer) {
        if (msgListeners.isEmpty()) {
            return;
        }
        for (int i = 0; i < msgListeners.size(); i++) {
            consumer.accept(msgListeners.get(i));
        }
    }

    public String getRoomIdAsString() {
        if (this.roomId == null) {
            return "";
        }
        return this.roomId.toString();
    }

    public long getRoomIdAsLong() {
        String roomIdAsString = this.getRoomIdAsString();
        if (roomIdAsString.trim().isEmpty()) {
            return 0L;
        }
        return Long.parseLong(roomIdAsString);
    }
}
