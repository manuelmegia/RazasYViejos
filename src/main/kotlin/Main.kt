import java.net.InterfaceAddress
import java.util.*

fun main(args: Array<String>) {
    var lenin: Anciano = Enano("Stalin")
    lenin.pregunta("¿AAAAAAAAA?")
}

open class Persona(var nombre: String) : Anciano, Adulto, Joven {
    override fun idioma(respuesta: String) {
    }

    override fun pregunta(pregunta: String) {
        var respuesta = ""
        if (test() == "Viejo") respuesta = respuestasViejo(pregunta)
        else if (test() == "Adulto") respuesta = respuestasAdulto(pregunta)
        else if (test() == "Anciano") respuesta = respuestasJoven(pregunta)
        idioma(respuesta)
    }

    override fun respuestasViejo(pregunta: String): String {
        var cont = 0
        for (c in pregunta.toCharArray()) {
            if (c == '¿' || c == '?') cont += 2
            else if (c.isUpperCase()) cont++
        }

        return if (pregunta == "¿Como estás?") "No me puedo mover"
        else if (cont == pregunta.length) "Háblame más alto que no te escucho"
        else if (cont == pregunta.length + 2) "Que no te escucho!"
        else if (pregunta == nombre) "Las 5 de la tarde"
        else "En mis tiempos esto no pasaba"
    }

    override fun respuestasAdulto(pregunta: String): String {
        var cont = 0
        for (c in pregunta.toCharArray()) {
            if (c.equals('¿') || c.equals('?')) cont += 2
            else if (c.isUpperCase()) cont++
        }

        return if (pregunta == "¿Como estás?") "En la flor de la vida, pero me empieza a doler la espalda"
        else if (cont == pregunta.length) "No me levantes la voz mequetrefe"
        else if (cont == pregunta.length + 2) "Estoy buscando la mejor solución"
        else if (pregunta.equals(nombre)) "¿Necesitas algo?"
        else "No sé de qué me estás hablando"
    }

    override fun test(): String {
        return "adsdasdasd"
    }

    override fun respuestasJoven(pregunta: String): String {
        var cont = 0
        for (c in pregunta.toCharArray()) {
            if (c == '¿' || c == '?') cont += 2
            else if (c.isUpperCase()) cont++
        }

        return if (pregunta == "¿Como estás?") "De lujo"
        else if (cont == pregunta.length) "Eh relájate"
        else if (cont == pregunta.length + 2) "Tranqui se lo que hago"
        else if (pregunta.equals(nombre)) "¿Qué pasa?"
        else "Yo que se"
    }
}

class Elfo(nombre: String) : Persona(nombre) {
    override fun idioma(respuesta: String) {
        println(respuesta.rot13cifrado(13))
    }
}

class Goblin(nombre: String) : Persona(nombre) {
    override fun idioma(respuesta: String) {
        println(respuesta.rot13cifrado(13))
    }
}

class Enano(nombre: String) : Persona(nombre) {
    override fun idioma(respuesta: String) {
        println(respuesta.uppercase(Locale.getDefault()))
    }
}

class Humano(nombre: String) : Persona(nombre) {
    override fun idioma(respuesta: String) {
        println(respuesta)
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

enum class Clase {
    GUERRERO, MAGO, LADRON, BERSERKER
}

enum class Raza {
    ELFO, HUMANO, ENANO, GOBLIN
}


interface RespuestasRazas {
    fun idioma(respuesta: String)
    fun pregunta(pregunta: String)
}

interface Anciano : RespuestasRazas {
    fun respuestasViejo(pregunta: String): String
    fun test(): String {
        return "Viejo"
    }
}//ayayayaya luego llamo al metodo ese y ya lo puedo reconocer. Si y aun así lo puedes sobreescribir

interface Adulto : RespuestasRazas {
    fun respuestasAdulto(pregunta: String): String
    fun test(): String {
        return "Adulto"
    }
}

interface Joven : RespuestasRazas {
    fun respuestasJoven(pregunta: String): String
    fun test(): String {
        return "Joven"
    }
}