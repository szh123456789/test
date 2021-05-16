package com.test.tools.listener;


import java.util.EventListener;

public interface MethodMonitorEventListener extends EventListener {
    // 处理方法执行之前发布的事件
    public void onMethodBegin(MethodMonitorEvent event);
    // 处理方法结束时发布的事件
    public void onMethodEnd(MethodMonitorEvent event);
}
