import java.util.*

fun main(args: Array<String>) {
    var lenin = Mago("Stalin", Estado_vital.ANCIANO, Raza.ENANO)
    lenin.pregunta("¿AAAAAAAAA?")
}

open class Persona(
    var nombre: String,
    var edad: Estado_vital,
    var raza: Raza,
    var partidas_jugadas: Int = 0,
    var horas: Int = 0,
    var kills: Int = 0,
    var deaths: Int = 0,
    var assists: Int = 0,
    var kd: Float = kills.toFloat() / deaths.toFloat()
) {
    open fun idioma(respuesta: String) {
        when (this.raza) {
            Raza.GOBLIN -> println(respuesta.rot13cifrado(13))
            Raza.ELFO -> println(respuesta.rot13cifrado(13))
            Raza.ENANO -> println(respuesta.uppercase(Locale.getDefault()))
            Raza.HUMANO -> println(respuesta)
        }
    }

    fun pregunta(pregunta: String) {
        var respuesta = ""
        respuesta = respuestasGen(pregunta)
        idioma(respuesta)
    }

    fun respuestasGen(pregunta: String): String {
        if (pregunta == "¿Como estás?") {
            return when (this.edad) {
                Estado_vital.ANCIANO -> "No me puedo mover"
                Estado_vital.ADULTO -> "En la flor de la vida, pero me empieza a doler la espalda"
                Estado_vital.JOVEN -> "De lujo"
            }
        } else if (pregunta == pregunta.uppercase() && pregunta.contains("¿") && pregunta.contains("?")) {
            return when (this.edad) {
                Estado_vital.ANCIANO -> "Que no te escucho!"
                Estado_vital.ADULTO -> "Estoy buscando la mejor solución"
                Estado_vital.JOVEN -> "Tranqui se lo que hago"
            }
        } else if (pregunta == pregunta.uppercase()) {
            return when (this.edad) {
                Estado_vital.ANCIANO -> "Háblame más alto que no te escucho"
                Estado_vital.ADULTO -> "No me levantes la voz mequetrefe"
                Estado_vital.JOVEN -> "Eh relájate"
            }
        } else if (pregunta == nombre) {
            return when (this.edad) {
                Estado_vital.ANCIANO -> "Las 5 de la tarde"
                Estado_vital.ADULTO -> "¿Necesitas algo?"
                Estado_vital.JOVEN -> "¿Qué pasa?"
            }
        } else return when (this.edad) {
            Estado_vital.ANCIANO -> "En mis tiempos esto no pasaba"
            Estado_vital.ADULTO -> "No sé de qué me estás hablando"
            Estado_vital.JOVEN -> "Yo que se"
        }
    }
}

class Mago(nombre: String, edad: Estado_vital, raza: Raza) : Persona(nombre, edad, raza)

class Berserker(nombre: String, edad: Estado_vital, raza: Raza) : Persona(nombre, edad, raza)

class Ladron(nombre: String, edad: Estado_vital, raza: Raza) : Persona(nombre, edad, raza)

class Guerrero(nombre: String, edad: Estado_vital, raza: Raza) : Persona(nombre, edad, raza)

class Mercader(nombre: String, edad: Estado_vital, raza: Raza) : Persona(nombre, edad, raza) {
    fun venta() {

    }

    fun compra() {

    }
}

fun String.rot13cifrado(tamañoRot: Int) = map {
    var tRot = tamañoRot
    while (tamañoRot > 26) {
        tRot = tamañoRot - 26
    }
    if ((it.code in 65..90) || (it.code in 97..122)) {
        val x = it + tRot
        when {
            it.isUpperCase() -> if (x > 'Z') x - 26 else x
            it.isLowerCase() -> if (x > 'z') x - 26 else x
            else -> it
        }
    } else it
}.toCharArray().joinToString("")


enum class Estado_vital {
    ANCIANO, ADULTO, JOVEN
}

enum class Raza {
    ELFO, ENANO, HUMANO, GOBLIN
}
