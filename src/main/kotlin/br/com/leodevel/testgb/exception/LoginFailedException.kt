package br.com.leodevel.testgb.exception

import java.lang.RuntimeException

class LoginFailedException: RuntimeException {

    constructor(message: String): super(message)
    constructor(ex: Exception): super(ex)

}