/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.insa.chatsystem.controller;

import java.net.InetAddress;
import org.insa.chatsystem.messages.Message;

/**
 *
 * @author Bastien
 */
public interface NItoController {
    void rcvMessage(InetAddress source, String message);
    void rcvHello(InetAddress source);
    void rcvBye(InetAddress source);
    void rcvFileReq(InetAddress source, String nomFichier);
    void rcvReqResp(InetAddress source);
}
