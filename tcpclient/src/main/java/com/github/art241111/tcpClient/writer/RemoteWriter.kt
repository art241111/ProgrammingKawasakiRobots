package com.github.art241111.tcpClient.writer

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.PrintStream
import java.net.Socket
import java.util.*

/**
 * This class creates a Writer that allows
 * you to send text strings to the server.
 * @author Artem Gerasimov.
 */
class RemoteWriter: RemoteWriterImp {
    // A variable that displays whether our Writer is working.
    private var isWriting = false

    // Writer, which allows you to send data to the server
    private lateinit var writer: PrintStream

    private val sendQueue: Queue<String> = LinkedList()
    private var delay = 0L

    /**
     * Add message to queue.
     * @param text - the text that will be sent to the server.
     */
    override fun send(text: String) {
        sendQueue.add(text)
    }

    /**
     * Handle queue.
     */
    private fun startSend(){
        GlobalScope.launch {
            while (isWriting){
                if(sendQueue.isNotEmpty()){
                    val text = sendQueue.poll()
                    if(!text.isNullOrEmpty()){
                        if (text.contains("Delay")){
                            delay(text.substringAfter(":").toLong())
                        } else{
                            sendToServer(text)
                        }
                    }
                }

                delay(delay)
            }
        }
    }

    /**
     * Sending a text command to the server.
     * @param text - the text that will be sent to the server.
     */
    private fun sendToServer(text: String){
        writer.print(text)
        writer.flush()
    }

    /**
     * Creating a writer that will send data to the server.
     * @param socket - - the connection that you want to listen to.
     */
    override fun createWriter(socket: Socket, delay: Long) {
        this.delay = delay
        writer = PrintStream(socket.getOutputStream())

        isWriting = true
        sendQueue.clear()
        startSend()
    }

    /**
     * Sending the final command and closing Writer.
     */
    override fun destroyWriter(stopCommand: String) {
        if(::writer.isInitialized && isWriting){
            if(stopCommand != ""){
                sendToServer(stopCommand)
                Thread.sleep(50L)
            }
            writer.close()
        }

        isWriting = false
        sendQueue.clear()
    }
}
