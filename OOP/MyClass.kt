fun main() {
    val car = Car("Green", "LLM")

    car.speed()
    car.drive()

    println("Car color : ${car.color}, model : ${car.model}")

    println("-------------------")

    val truck = Truck("Magenta", "BMS")
    truck.speed(20, 100)
    truck.drive()
    println("truck color : ${truck.color}, model : ${truck.model}")

    println("-------------------")

    val button = Button("Button")
    button.onClick("This is a Button")

    val superMario = Character("Super Mario")
    superMario.onClick("This is an actor")

    println("-------------------")

    println("Hello Jenny".append("O0O"))
    println("Hello Jenny".removeFirstAndLastChars())

    println("-------------------")

    val person = Person(
        name = "Joe",
        lastName = "Ball",
        age = 23
    )
    val aroni = Person(
        name = "Ruti",
        lastName = "Machava",
        age = 54
    )
    val Ruti = Person(
        name = "Ruti",
        lastName = "Machava",
        age = 27
    )
    val listOfPeople = listOf(person, aroni, Ruti)
    listOfPeople.forEach { item ->
        println(item.age)
    }
}

class Truck(color: String, model: String) : Car(color, model) {
    override fun speed(minSpeed: Int, maxSpeed: Int) {
        println("Truck Min speed is ${(minSpeed * 0.6).toInt()} and Max speed is ${(maxSpeed * 0.6).toInt()}")
    }

    override fun drive() {
        println("Vrooooommmmmm... Like a truck")
    }
}

open class Car(var color: String = "Blue", var model: String = "XMD") {
    init {
        if (color == "Green") {
            println("Sold OUT X")
        } else {
            println("Here we are")
        }
    }

    open fun speed(minSpeed: Int = 100, maxSpeed: Int = 190) {
        println("Min speed is $minSpeed and Max speed is $maxSpeed")
    }

    open fun drive() {
        println("Drive..brung..brung..")
    }
}

class Button(val label: String) : ClickEvent {
    override fun onClick(message: String) {
        println("Clicked by $label and here's a message $message")
    }

}

class Character(val name: String) : ClickEvent {
    override fun onClick(message: String) {
        println("Clicked by $name and here's a message $message")
    }
}

interface ClickEvent {
    fun onClick(message: String)
}


fun String.append(toAppend: String): String = this.plus(toAppend)

fun String.removeFirstAndLastChars(): String = this.substring(1, this.lastIndex - 1)

data class Person(val name: String, val lastName: String, val age: Int)

