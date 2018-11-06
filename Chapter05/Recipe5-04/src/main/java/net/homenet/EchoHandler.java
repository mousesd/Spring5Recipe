package net.homenet;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("CONNECTION ESTABLISHED"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String sendMessage = message.getPayload();
        session.sendMessage(new TextMessage("RECEIVED: " + sendMessage));
    }

    //# 예외발생
    //  java.lang.IllegalStateException: Message will not be sent because the WebSocket session has been closed
    //      at org.apache.tomcat.websocket.WsRemoteEndpointImplBase.writeMessagePart(WsRemoteEndpointImplBase.java:424)
    //      at org.apache.tomcat.websocket.WsRemoteEndpointImplBase.sendMessageBlock(WsRemoteEndpointImplBase.java:309)
    //      at org.apache.tomcat.websocket.WsRemoteEndpointImplBase.sendMessageBlock(WsRemoteEndpointImplBase.java:250)
    //      at org.apache.tomcat.websocket.WsRemoteEndpointImplBase.sendPartialString(WsRemoteEndpointImplBase.java:223)
    //      at org.apache.tomcat.websocket.WsRemoteEndpointBasic.sendText(WsRemoteEndpointBasic.java:49)
    //      at org.springframework.web.socket.adapter.standard.StandardWebSocketSession.sendTextMessage(StandardWebSocketSession.java:216)
    //      at org.springframework.web.socket.adapter.AbstractWebSocketSession.sendMessage(AbstractWebSocketSession.java:100)
    //      at net.homenet.EchoHandler.afterConnectionClosed(EchoHandler.java:22)
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        session.sendMessage(new TextMessage("CONNECTION CLOSED"));
    }
}
