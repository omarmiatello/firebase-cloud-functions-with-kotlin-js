external fun require(module: String): dynamic
external val exports: dynamic

fun main(args: Array<String>) {
    val fireFunctions = require("firebase-functions")
    val config = fireFunctions.config()

    exports.helloWorld = fireFunctions.https.onRequest { request, response ->
        console.log("Request headers: " + toJson(request.headers))
        console.log("Request body: " + toJson(request.body))

        response.send("Hello from Firebase!")
    }
}