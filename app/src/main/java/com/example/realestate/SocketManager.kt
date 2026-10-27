package com.example.realestate

import io.socket.client.IO
import io.socket.client.Socket

object SocketManager {

    private const val SERVER_URL = "http://10.0.2.2:5000"

    private var socket: Socket? = null

    fun connect(brokerId: String) {
        if (socket == null) {
            socket = IO.socket(SERVER_URL)
        }

        socket?.on(Socket.EVENT_CONNECT) {
            socket?.emit("join_broker", brokerId)
        }

        socket?.connect()
    }

    fun listenForAppointments(
        onNotification: (String) -> Unit
    ) {
        socket?.on("new_appointment") { args ->
            val data = args[0] as org.json.JSONObject
            val message = data.optString("message")

            onNotification(message)
        }
    }

    fun disconnect() {
        socket?.disconnect()
        socket?.off()
        socket = null
    }
}