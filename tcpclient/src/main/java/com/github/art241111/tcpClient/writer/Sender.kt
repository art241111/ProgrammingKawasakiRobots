package com.github.art241111.tcpClient.writer

interface Sender {
    fun send(text: String)
    fun safeSend(text: String)
    fun stopSending()
    fun startSending()
}