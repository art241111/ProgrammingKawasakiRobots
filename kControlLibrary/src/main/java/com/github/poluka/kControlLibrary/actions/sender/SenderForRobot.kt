package com.github.poluka.kControlLibrary.actions.sender

import com.github.art241111.tcpClient.writer.Sender
import kotlinx.coroutines.*
import java.util.*

class SenderForRobot(private val sender: Sender,
                     private var delay: Long = 5L): Sender {
    private val programStatus = ProgramStatus()
    private val sendQueue: Queue<String> = LinkedList()
    private var isWriting = false

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)


    /**
     * Add message to queue.
     * @param text - the text that will be sent to the server.
     */
    override fun send(text: String) {
        sender.send(text)
    }

    override fun safeSend(text: String) {
        if(programStatus.status == ProgramStatusEnum.READY_TO_SEND){
            sendQueue.add(text)
        }
    }

    /**
     * Handle queue.
     */
    private fun startSend(){
        scope.launch {
            while (isWriting){
                if(sendQueue.isNotEmpty()){
                    val text = sendQueue.poll()
                    if(!text.isNullOrEmpty()){
                        if (text.contains("Delay")){
                            delay(text.substringAfter(":").toLong())
                        } else{
                            send(text)
                        }
                    }
                }
            }
        }
    }

    /**
     * Sending the final command and closing Writer.
     */
    override fun stopSending() {
        isWriting = false
        sendQueue.clear()
    }

    override fun startSending() {
        isWriting = true
        sendQueue.clear()
        startSend()
    }
}