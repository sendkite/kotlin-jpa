package com.prac.demo.common.exception

class NotEnoughStockException: RuntimeException {
    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable): super(message, cause)
}