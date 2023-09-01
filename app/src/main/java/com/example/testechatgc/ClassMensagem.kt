package com.example.testechatgc

import java.io.Serializable

data class ClassMensagem(
    val contato: String,
    val mensagem: String,
    val horario: String,
    var status: String = "não lida",
): Serializable
