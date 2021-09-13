//nested class
class Behaviour{
    companion object {
        enum class Status{
            NORMAL,GOOD,BAD
        }
        fun editStatus(person: Personal){
            println("Edit his mistake status:")
            println("1. ${Status.NORMAL}")
            println("2. ${Status.GOOD}")
            println("3. ${Status.BAD}")
            print("Your action : ")
            when(readLine()?.toInt()){
                1 -> person.status = Status.NORMAL.toString()
                2 -> person.status = Status.GOOD.toString()
                3 -> person.status = Status.BAD.toString()
            }
        }
        fun call(personName: String,message: String){
            println("Hei $personName, $message")
        }
        fun praise(personName: String,message: String){
            println("Good job $personName, $message")
        }
        fun scold(personName: String, message: String){
            println("Hei you little rat, you $personName have better to $message")
        }
    }
}

//main func
fun main() {
    //the data
    val mother = Personal("Sarah Endah",47, "female","mother")
    val father = Personal("Petruk Sudarsono", 50,"male","father")
    val son = Personal("Endi Liansyah",18,"male","son")
    //array to store all of them
    val family = arrayListOf(mother,father,son)
    //play the menu
    while (true){
        menu(family)
    }
}

//to show the menu's choices
fun menu(family: ArrayList<Personal>){
    println("Welcome to the Stream Family")
    println("===================================")
    println("Login as :")
    var index = 1
    family.forEach {
        println("$index. ${it.role}")
        index++
    }
    print("Your Choice : ")
    when(val choice = readLine()?.toInt()){
        in 1..2 -> parentMenu(family,choice)
        3 -> childrenMenu(family,choice)
    }
}

//display the parental control
fun parentMenu(family: ArrayList<Personal>, choice: Int?) {
    val index = choice?.minus(1)
    var param = 0
    println()
    println("===================================")
    println("Welcome ${family[index!!].name}")
    println("===================================")
    println("Check your son status")
    family.forEach { _ ->
        if(family[param].role == "son"){
            val son = family[param].copy()
            println(family[param].name)
            println("His mistake status : ${family[param].status}")
            println("Your Action")
            println("1. Edit his status")
            println("2. What should you do")
            println("3. Exit")
            print("Your choice : ")
            when(readLine()?.toInt()){
                1 -> {
                    Behaviour.editStatus(family[param])
                    println()
                    parentMenu(family, choice)
                }
                2 -> {
                    when (son.status) {
                        Behaviour.Companion.Status.BAD.toString() -> {
                            println("Scold him")
                            print("Your message : ")
                            Behaviour.scold(son.name, readLine().toString())
                            println()
                            parentMenu(family, choice)
                        }
                        Behaviour.Companion.Status.GOOD.toString() -> {
                            println("Praise him")
                            print("Your message: ")
                            Behaviour.praise(son.name,readLine().toString())
                            println()
                            parentMenu(family, choice)
                        }
                        Behaviour.Companion.Status.NORMAL.toString() -> {
                            println("Call him")
                            print("Your message : ")
                            Behaviour.call(son.name, readLine().toString())
                            println()
                            parentMenu(family, choice)
                        }
                    }
                }
                3 -> menu(family)
            }
        }
        param++
    }
}

//display the children status report
fun childrenMenu(family: ArrayList<Personal>, choice: Int) {
    val index = choice-1
    val status = family[index].status
    println("Hai ${family[index].name}")
    println("There is your status right now")
    println("Status = ${family[index].status}")
    if(status.equals("normal",true)){
        println("Kamu dalam keadaan aman dari amukan")
        println()
        println("===================================")
    }
    else if(status.equals("good",true)){
        println("Selamat, orang tuamu bangga dan bahagia padamu")
        println()
        println("===================================")
    }
    else if(status.equals("bad",true)){
        println("Kamu sudah berbuat buruk")
        println("Persiapkan mental dan telingamu sesegera mungkin")
        println()
        println("===================================")
    }
}

data class Personal(
    val name : String, val age : Int, val gender : String,
    val role: String, var status: String = "NORMAL"
)