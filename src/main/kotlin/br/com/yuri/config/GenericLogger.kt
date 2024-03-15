package br.com.yuri.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GenericLogger {
    companion object {
        fun <T> loggerFor(targetClass: Class<T>): Logger = LoggerFactory.getLogger(targetClass)
    }
}