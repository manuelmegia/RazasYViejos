import java.util.*

fun main(args: Array<String>) {
    var lenin: Anciano = Elfo("Stalin", Raza.ENANO)
    lenin.pregunta("¿AAAAAAAAA?")
}

open class Persona(var nombre: String, var raza: Raza) : Anciano, Adulto, Joven {
    override fun habla(respuesta: String) {
        if (raza == Raza.ELFO) println(respuesta.rot13cifrado(13))
        if (raza == Raza.GOBLIN) println(respuesta.rot13cifrado(13))
        if (raza == Raza.ENANO) println(respuesta.uppercase(Locale.getDefault()))
        if (raza == Raza.HUMANO) println(respuesta)
    }

    override fun pregunta(pregunta: String) {
        var respuesta = ""
        if ()
        respuesta = respuestasViejo(pregunta)
        habla(respuesta)
    }

    override fun respuestasViejo(pregunta: String): String {
        var cont = 0
        for (c in pregunta.toCharArray()) {
            if (c.isUpperCase()) cont++
            else if (c == '¿' || c== '?') cont + 2
        }

        return if (pregunta == "¿Como estás?") "No me puedo mover"
        else if (cont == pregunta.length) "Háblame más alto que no te escucho"
        else if (cont == pregunta.length + 2) "Que no te escucho!"
        else if (pregunta.equals(nombre)) "Las 5 de la tarde"
        else "En mis tiempos esto no pasaba"
    }

    override fun respuestasAdulto(pregunta: String): String {
        var cont = 0
        for (c in pregunta.toCharArray()) {
            if (c.isUpperCase()) cont++
            else if (c == '¿' || c== '?') cont + 2
        }

        return if (pregunta == "¿Como estás?") "No me puedo mover"
        else if (cont == pregunta.length) "Háblame más alto que no te escucho"
        else if (cont == pregunta.length + 2) "Que no te escucho!"
        else if (pregunta.equals(nombre)) "Las 5 de la tarde"
        else "En mis tiempos esto no pasaba"
    }

    override fun respuestasJoven(pregunta: String): String {
        var cont = 0
        for (c in pregunta.toCharArray()) {
            if (c.isUpperCase()) cont++
            else if (c == '¿' || c== '?') cont + 2
        }

        return if (pregunta == "¿Como estás?") "No me puedo mover"
        else if (cont == pregunta.length) "Háblame más alto que no te escucho"
        else if (cont == pregunta.length + 2) "Que no te escucho!"
        else if (pregunta.equals(nombre)) "Las 5 de la tarde"
        else "En mis tiempos esto no pasaba"
    }

}

class Elfo(nombre: String, raza: Raza) : Persona(nombre, raza) {
    var martilloElfico = "Martillo Elfico"
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
    fun habla(respuesta: String)
    fun pregunta(pregunta: String)
}

interface Anciano : RespuestasRazas {
    fun respuestasViejo(pregunta: String): String
}

interface Adulto : RespuestasRazas {
    fun respuestasAdulto(pregunta: String): String
}

interface Joven : RespuestasRazas {
    fun respuestasJoven(pregunta: String): String
}